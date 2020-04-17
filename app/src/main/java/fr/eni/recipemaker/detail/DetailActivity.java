package fr.eni.recipemaker.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fr.eni.recipemaker.AppActivity;
import fr.eni.recipemaker.R;
import fr.eni.recipemaker.favorites.FavoritesActivity;
import fr.eni.recipemaker.models.Ingredient;
import fr.eni.recipemaker.models.Recipe;
import fr.eni.recipemaker.search.IngredientAdapter;
import fr.eni.recipemaker.ui.listing.RecipeAdapter;

public class DetailActivity extends AppActivity {

    private ImageView imageView;
    private TextView labelView;
    private LinearLayout ingredientLinearLayout;
    private FlexboxLayout tagFlexboxLayout;
    private Button buttonFavorite;
    private List<Recipe> savedRecipes;

    private String urlRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        labelView = findViewById(R.id.labelView);
        buttonFavorite = findViewById(R.id.buttonFavorite);

        ingredientLinearLayout = findViewById(R.id.ingredientLinearLayout);

        /**
         * flexboxlayout pour retour a la ligne pour les tags
         */
        tagFlexboxLayout = findViewById(R.id.tagFlexboxLayout);
        tagFlexboxLayout.setFlexDirection(FlexDirection.ROW);
        tagFlexboxLayout.setFlexWrap(FlexWrap.WRAP);

        if(getIntent().getExtras() != null) {
            Recipe item = (Recipe)getIntent().getExtras().get("object");

            Picasso.get().load(item.getImage()).into(imageView);
            labelView.setText(item.getLabel());
            urlRedirect = item.getUrl();

            for(Ingredient ingredient : item.getIngredients()) {
                ingredientLinearLayout.addView(getIngredient(DetailActivity.this, ingredient.getText()));
            }

            for(String tag : item.getHealthLabels()) {
                tagFlexboxLayout.addView(getTag(DetailActivity.this, tag));
            }

        } else {
            labelView.setText("Erreur, pas de recette trouvée");
        }

        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe item = (Recipe)getIntent().getExtras().get("object");
                Gson gson = new Gson();
                SharedPreferences sp = getSharedPreferences("PREF_MODE", MODE_PRIVATE);
                String jsonGet = sp.getString("recipes", "");

                //si la liste de recette n'existe pas on en créé une
                if(jsonGet == ""){
                    savedRecipes = new ArrayList<>();
                }

                //si elle existe on la récupère et on ajoute l'item courant
                Type type = new TypeToken<List<Recipe>>() {}.getType();
                savedRecipes = gson.fromJson(jsonGet, type);
                savedRecipes.add(item);
                SharedPreferences.Editor editor = sp.edit();

                //on enregistre et affiche la confirmation à l'utilisateur
                String jsonSave = gson.toJson(savedRecipes);
                editor.putString("recipes", jsonSave);
                editor.apply();
                Toast.makeText(DetailActivity.this,
                        "Recipe added to favorites !",
                        Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * Création d'une view avec ingrédients pour avoir une liste avec hauteur dynamique
     * @param context
     * @param value
     * @return
     */
    private View getIngredient(Context context, String value) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ingredient_recipe, null);

        TextView ingredientName = view.findViewById(R.id.ingredientName);
        ingredientName.setText(value);

        return view;
    }

    /**
     * Création d'une view avec tags pour avoir une liste avec hauteur dynamique
     * @param context
     * @param value
     * @return
     */
    private View getTag(Context context, String value) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tag, null);

        TextView tagName = view.findViewById(R.id.tagName);
        tagName.setText(value);

        return view;
    }

    public void redirectToRecipe(View view) {
        Intent intentWebsite = new Intent(Intent.ACTION_VIEW);
        intentWebsite.setData(Uri.parse(urlRedirect));
        startActivity(intentWebsite);
    }
}
