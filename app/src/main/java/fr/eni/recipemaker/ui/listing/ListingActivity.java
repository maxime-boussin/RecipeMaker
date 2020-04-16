
package fr.eni.recipemaker.ui.listing;

import fr.eni.recipemaker.R;
import fr.eni.recipemaker.detail.DetailActivity;
import fr.eni.recipemaker.models.Ingredient;
import fr.eni.recipemaker.models.Recipe;
import fr.eni.recipemaker.ui.listing.RecipeAdapter;
import fr.eni.recipemaker.detail.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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


        //endregion
    if(getIntent().getExtras() != null){

        recipeList = (List<Recipe>) getIntent().getExtras().get("recipeList");
        for (Recipe item : recipeList
             ) {
            System.out.println("apres : " + item);
        }
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
