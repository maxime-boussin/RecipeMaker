package fr.eni.recipemaker.search;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import fr.eni.recipemaker.AppActivity;
import fr.eni.recipemaker.R;
import fr.eni.recipemaker.models.Hits;
import fr.eni.recipemaker.models.InfoResponse;
import fr.eni.recipemaker.models.Ingredient;
import fr.eni.recipemaker.models.Recipe;
import fr.eni.recipemaker.ui.listing.ListingActivity;
import fr.eni.recipemaker.utils.Constant;
import fr.eni.recipemaker.utils.FastDialog;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SearchActivity extends AppActivity {

    private List<Recipe> recipies = new ArrayList<>();
    private List<Ingredient> ingredients = new ArrayList<>();
    private IngredientAdapter adapter;
    private ListView listIngredients;
    private EditText editIngredient;
    private Ingredient ingredient;
    private Button buttonSubmit;
    private CheckBox chkNoGluten;
    private CheckBox chkNoLactose;
    private CheckBox chkNoAlcool;
    private CheckBox chkNoPeanuts;
    private Button buttonReccord;
    private static final String PREFS = "PREFS";
    private static final String PREFS_INGREDIENTS = "PREFS_INGREDIENTS";
    private static final String PREFS_EXCLUDED = "PREFS_EXCLUDED";
    SharedPreferences sharedPreferences;
    StringJoiner excluded = new StringJoiner(",",Constant.STR_EXCLUDED,"");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editIngredient = findViewById(R.id.editIngredient);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        chkNoGluten = (CheckBox) findViewById(R.id.chkNoGluten);
        chkNoLactose = (CheckBox) findViewById(R.id.chkNoLactose);
        chkNoAlcool = (CheckBox) findViewById(R.id.chkNoAlcool);
        chkNoPeanuts = (CheckBox) findViewById(R.id.chkNoPeanuts);
        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        buttonReccord = (Button) findViewById(R.id.buttonReccord);
        /**
         * affiche la liste vide par defaut
         */
        listIngredients = findViewById(R.id.listViewIngredients);
        adapter = new IngredientAdapter(SearchActivity.this,
                R.layout.item_ingredient,
                ingredients
        );
        listIngredients.setAdapter(adapter);

        buttonReccord.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(ingredients.isEmpty()){
                    FastDialog.showDialog(
                            SearchActivity.this,
                            FastDialog.SIMPLE_DIALOG,
                            getString(R.string.MSG_INGREDIENTS)
                    );
                    return;
                }else{
                   String sbIng = ingredients.stream().map(Ingredient::getText).collect(Collectors.joining(",")).toString();

                sharedPreferences
                        .edit()
                        .putString(PREFS_INGREDIENTS, sbIng.toString())
                        .putString(PREFS_EXCLUDED,excluded.toString())
                        .apply();
            }}
        });

        /**
         * envoi de la requette
         */
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            String listeDesIngredients;

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if (ingredients.isEmpty()) {
                    FastDialog.showDialog(
                            SearchActivity.this,
                            FastDialog.SIMPLE_DIALOG,
                            getString(R.string.MSG_INGREDIENTS)
                    );
                    return;
                }
//

                RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
                /**
                 * construction de la liste d'ingredient
                 * ajoute la liste des ingredients puis
                 * on verifie si les cases free sont cochés et si oui
                 * ajoute &health={liste des exclus}
                 */

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    listeDesIngredients = ingredients.stream().map(Ingredient::getText).collect(Collectors.joining(","));
                }
                //String excluded = Constant.STR_EXCLUDED;

                if(chkNoAlcool.isChecked()){
                    excluded.add(Constant.ALCOHOL_FREE);
                }
                if(chkNoGluten.isChecked()){
                    excluded.add(Constant.GLUTEN_FREE);
                }
                if(chkNoLactose.isChecked()){
                    excluded.add(Constant.DAIRY_FREE);
                }
                if(chkNoPeanuts.isChecked()){
                    excluded.add(Constant.PEANUTS_FREE);
                }
                System.out.println("liste des exclus : " + excluded);
                /**
                 * construction de la request
                 */
                String stringRequest = String.format(Constant.get_recipe, listeDesIngredients, Constant.id_Edamam, Constant.key_Edamam).concat(excluded.toString());

                System.out.println("la request : " + stringRequest);
                /**
                 * c'est parti mon kiki
                 */
                StringRequest request = new StringRequest(Request.Method.GET, stringRequest,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println(response);
                                getData(response);
                            }
                        },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String json = new String(error.networkResponse.data);
                        getData(json);
                    }
                });
                queue.add(request);
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null) {
                            System.out.println("Error Response code : " + error.networkResponse.statusCode);
                        }
                    }
                };
            }


            private void getData(String json) {
                ArrayList<Recipe> listRecipe = new ArrayList<>();
                InfoResponse infoResponse = new Gson().fromJson(json, InfoResponse.class);

                for (Hits hit : infoResponse.getHits()
                ) {
                    System.out.println(hit);
                    Recipe recipe = new Recipe();
                    recipe.setLabel(hit.getRecipe().getLabel());
                    recipe.setCalories(hit.getRecipe().getCalories());
                    recipe.setHealthLabels(hit.getRecipe().getHealthLabels());
                    recipe.setImage(hit.getRecipe().getImage());
                    recipe.setIngredients(hit.getRecipe().getIngredients());
                    recipe.setUrl(hit.getRecipe().getUrl());

                    listRecipe.add(recipe);
                }

                Intent intent = new Intent(SearchActivity.this, ListingActivity.class);
                intent.putExtra("recipeList", listRecipe);
                startActivity(intent);

            }
        });

    }


    public void addIngredient(View view) {
        /**
         * Ajoute un ingredient dans la liste
         * et raz edittext
         */
        if (editIngredient != null) {
            ingredient = new Ingredient(editIngredient.getText().toString());
            ingredients.add(ingredient);
        }
        listIngredients = findViewById(R.id.listViewIngredients);
        adapter = new IngredientAdapter(SearchActivity.this,
                R.layout.item_ingredient,
                ingredients
        );
        listIngredients.setAdapter(adapter);
        editIngredient.setText("");
    }
}
