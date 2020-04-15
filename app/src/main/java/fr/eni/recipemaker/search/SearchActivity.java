package fr.eni.recipemaker.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.eni.recipemaker.R;
import fr.eni.recipemaker.models.InfoResponse;
import fr.eni.recipemaker.models.Ingredient;
import fr.eni.recipemaker.models.Recipe;
import fr.eni.recipemaker.utils.Constant;
import fr.eni.recipemaker.utils.FastDialog;

public class SearchActivity extends AppCompatActivity {

    private List<Recipe> recipies = new ArrayList<>();
    private List<Ingredient> ingredients = new ArrayList<>();
    private IngredientAdapter adapter;
    private ListView listIngredients;
    private EditText editIngredient;
    private Ingredient ingredient;
    private Button buttonSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editIngredient = findViewById(R.id.editIngredient);
        buttonSubmit = findViewById(R.id.buttonSubmit);


        /**
         * affiche la liste vide par defaut
         */
        listIngredients = findViewById(R.id.listViewIngredients);
        adapter = new IngredientAdapter(SearchActivity.this,
                R.layout.item_ingredient,
                ingredients
        );
        listIngredients.setAdapter(adapter);


        /**
         * Suppression d'un élément dans laliste
         */
        listIngredients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("id de l'élément à supprimer : " + position);
                ingredients.remove(position);
                listIngredients = findViewById(R.id.listViewIngredients);
                adapter = new IngredientAdapter(SearchActivity.this,
                        R.layout.item_ingredient,
                        ingredients
                );
                listIngredients.setAdapter(adapter);
            }
        });

        /**
         * envoi de la requette
         */
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            String listeDesIngredients;
            @Override
            public void onClick(View v) {

                if (ingredients.isEmpty()) {
                    FastDialog.showDialog(
                            SearchActivity.this,
                            FastDialog.SIMPLE_DIALOG,
                            "Veuillez saisir des ingrédients"
                    );
                    return;
                }
//                RequestQueue requestqueue ;
//                Uri.Builder uri = new Uri.Builder();
//                uri.scheme("https");
//                uri.path(Constant.get_recipe);
//

                RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
                /**
                 * construction de la liste d'ingredient
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    listeDesIngredients = ingredients.stream().map(Ingredient::getText).collect(Collectors.joining(","));

                }
                /**
                 * construction de la request
                 */
                String stringRequest = String.format(Constant.get_recipe, listeDesIngredients, Constant.id_Edamam, Constant.key_Edamam);
                System.out.println("la request : " + stringRequest);
                /**
                 * c'est parti mon kiki
                 */
                StringRequest request = new StringRequest(Request.Method.GET, stringRequest,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println(response.toString());
                                getData(response);
                            }
                        }, error -> {
                            String json = new String(error.networkResponse.data);
                            getData(json);
                        });
                queue.add(request);
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error.networkResponse != null){
                            System.out.println("Error Response code : " + error.networkResponse.statusCode);
                        }
                    }
                };
            }

            private void getData(String json) {
                ArrayList<Recipe> listeRecettes = new ArrayList<>();
                InfoResponse infoResponse = new Gson().fromJson(json, InfoResponse.class);
                System.out.println("nb de recettes recues : " + infoResponse.getHits().size());

                listeRecettes.addAll(infoResponse.getHits());

                for (Recipe recipe: listeRecettes
                     ) {
                    System.out.println(recipe);
                }

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
