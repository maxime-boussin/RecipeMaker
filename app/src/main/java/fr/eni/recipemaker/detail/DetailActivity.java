package fr.eni.recipemaker.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.eni.recipemaker.AppActivity;
import fr.eni.recipemaker.R;
import fr.eni.recipemaker.models.Ingredient;
import fr.eni.recipemaker.models.Recipe;
import fr.eni.recipemaker.search.IngredientAdapter;

public class DetailActivity extends AppActivity {

    private ImageView imageView;
    private TextView labelView;
    private LinearLayout ingredientLinearLayout;
    private FlexboxLayout tagFlexboxLayout;
    private TextView urlView;

    private String urlRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        labelView = findViewById(R.id.labelView);
        urlView = findViewById(R.id.urlView);

        ingredientLinearLayout = findViewById(R.id.ingredientLinearLayout);

        /**
         * flexboxlayout pour retour a la ligne pour les tags
         */
        tagFlexboxLayout = findViewById(R.id.tagFlexboxLayout);
        tagFlexboxLayout.setFlexDirection(FlexDirection.ROW);
        tagFlexboxLayout.setFlexWrap(FlexWrap.WRAP);

        final List<Ingredient> ingredientList = new ArrayList<>();
        final List<String> tagList = new ArrayList<>();

        if(getIntent().getExtras() != null) {
            Recipe item = (Recipe)getIntent().getExtras().get("object");

            Picasso.get().load(item.getImage()).into(imageView);
            labelView.setText(item.getLabel());
            urlRedirect = item.getUrl();
            //urlView.setText(item.getUrl());

            for(Ingredient ingredient : item.getIngredients()) {
                ingredientLinearLayout.addView(getIngredient(DetailActivity.this, ingredient.getText()));
            }

            for(String tag : item.getHealthLabels()) {
                tagFlexboxLayout.addView(getTag(DetailActivity.this, tag));
            }

        } else {
            /**
             * Données en dur pour test
             */
//            Picasso.get().load(R.drawable.carbo).into(imageView);
//            labelView.setText("Pâtes carbonara");
//            //        urlView.setText("https://www.lesbonnnespatescarbo.fr");
//            urlRedirect = "https://www.lesbonnnespatescarbo.fr";
//
//            ingredientList.add(new Ingredient("100g de crème"));
//            ingredientList.add(new Ingredient("500g de Pâtes"));
//
//            for (Ingredient ingredient : ingredientList) {
//                ingredientLinearLayout.addView(getIngredient(DetailActivity.this, ingredient.getText()));
//            }
//
//            tagList.add("Peanut-Free");
//            tagList.add("Alcohol-Free");
//            tagList.add("Peanut-Free");
//            tagList.add("Alcohol-Free");
//
//            for (String tag : tagList) {
//                tagFlexboxLayout.addView(getTag(DetailActivity.this, tag));
//            }
            labelView.setText("Erreur, pas de recette trouvée");
        }
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

    public void back(View view) {
        finish();
    }
}
