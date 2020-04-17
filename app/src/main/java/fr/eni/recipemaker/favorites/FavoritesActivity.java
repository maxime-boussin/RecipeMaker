package fr.eni.recipemaker.favorites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import fr.eni.recipemaker.AppActivity;
import fr.eni.recipemaker.R;
import fr.eni.recipemaker.detail.DetailActivity;
import fr.eni.recipemaker.models.Recipe;
import fr.eni.recipemaker.ui.listing.RecipeAdapter;

public class FavoritesActivity extends AppActivity {
    private ListView listViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        listViewData = findViewById(R.id.listViewData);
        SharedPreferences sp = getSharedPreferences("PREF_MODE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("recipes", "");
        Type type = new TypeToken<List<Recipe>>() {}.getType();
        List<Recipe> recipeList = gson.fromJson(json, type);
        for (Recipe r : recipeList) {
            System.out.println(r);
        }

        listViewData.setAdapter(new RecipeAdapter(
                FavoritesActivity.this,
                R.layout.item_favorite,
                recipeList
        ));

        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe item = recipeList.get(position);

                Intent intent = new Intent(FavoritesActivity.this, DetailActivity.class);

                // TODO : passage de l'objet Recipe
                intent.putExtra("object", item);

                startActivity(intent);
            }
        });

        //TODO display favorites list
    }
}
