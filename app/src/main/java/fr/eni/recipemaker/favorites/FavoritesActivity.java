package fr.eni.recipemaker.favorites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    // Adapter
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        //récupération de la liste de recettes favories
        listViewData = findViewById(R.id.listViewData);
        SharedPreferences sp = getSharedPreferences("PREF_MODE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("recipes", "");
        if(json == "") {
        } else {
            Type type = new TypeToken<List<Recipe>>() {}.getType();
            List<Recipe> recipeList = gson.fromJson(json, type);
            for (Recipe r : recipeList) {
                System.out.println(r);
            }

            adapter = new RecipeAdapter(
                    FavoritesActivity.this,
                    R.layout.item_favorite,
                    recipeList
            );

            listViewData.setAdapter(adapter);

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

            /**
             * Suppression d'un favori
             */
            listViewData.setOnItemLongClickListener((parent, view, position, id) -> {
                SharedPreferences.Editor editor = sp.edit();
                //on supprime de la vue
                recipeList.remove(position);
                //on supprime des favoris et on informe l'utilisateur de la suppression
                String jsonSave = gson.toJson(recipeList);
                editor.putString("recipes", jsonSave);
                editor.apply();
                // demande de rafrachissement
                adapter.notifyDataSetChanged();
                Toast.makeText(FavoritesActivity.this,
                        "Recipe removed from favorites !",
                        Toast.LENGTH_LONG).show();

                return false;
            });
        }

    }
}
