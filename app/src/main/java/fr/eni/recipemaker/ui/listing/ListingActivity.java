
package fr.eni.recipemaker.ui.listing;

import fr.eni.recipemaker.R;
import fr.eni.recipemaker.detail.DetailActivity;
import fr.eni.recipemaker.models.Ingredient;
import fr.eni.recipemaker.models.Recipe;
import fr.eni.recipemaker.ui.listing.RecipeAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListingActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private ListView listViewData;
    List<Recipe> recipeList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        textViewTitle = findViewById(R.id.textViewTitle);
        listViewData = findViewById(R.id.listViewData);
        textViewTitle.setText(R.string.list_title);

//        List<String> healthLabels = new ArrayList<>();
//        List<Ingredient> ingredients = new ArrayList<>();
//        healthLabels.add("Sugar-Conscious");
//        healthLabels.add("Peanut-Free");
//        healthLabels.add("Vegetarian");
//        healthLabels.add("Tree-Nut-Free");
//        healthLabels.add("Alcohol-Free");
//        ingredients.add(new Ingredient("2 cups leftover pasta", 210.0));
//        ingredients.add(new Ingredient("4 eggs beaten", 172.0));
//        ingredients.add(new Ingredient("2 tablespoons butter", 28.4));
//        ingredients.add(new Ingredient("1/2 cup whichever cheese the pasta called for", 52.5));
//        recipeList.add(new Recipe(
//                "Pasta Frittata Recipe",
//                "https://www.edamam.com/web-img/5a5/5a5220b7a65c911a1480502ed0532b5c.jpg",
//                "https://www.foodrepublic.com/recipes/pasta-frittata-recipe/",
//                healthLabels,
//                ingredients,
//                1423.463)
//        );
//        recipeList.add(new Recipe(
//                "Kimchi Pasta",
//                "https://www.edamam.com/web-img/2d1/2d1770d49a37ccc618c0780c2abcf2b9.jpg",
//                "http://norecipes.com/blog/2010/02/02/kimchi-pasta-recipe/",
//                healthLabels,
//                ingredients,
//                725.3600481744218)
//        );
        //endregion
    if(getIntent().getExtras() != null){

        recipeList = (List<Recipe>) getIntent().getExtras().get("recipeList");
//        for (Recipe item : recipeList
//             ) {
//            System.out.println("apres : " + item);
//        }
    }

        listViewData.setAdapter(new RecipeAdapter(
                ListingActivity.this,
                R.layout.item_recipe,
                recipeList
        ));

        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe item = recipeList.get(position);

                Intent intent = new Intent(ListingActivity.this, DetailActivity.class);

                // TODO : passage de l'objet Recipe
                intent.putExtra("object", item);

                startActivity(intent);
            }
        });
    }
}
