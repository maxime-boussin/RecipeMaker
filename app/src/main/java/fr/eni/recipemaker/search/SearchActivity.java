package fr.eni.recipemaker.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eni.recipemaker.R;
import fr.eni.recipemaker.models.Ingredient;

public class SearchActivity extends AppCompatActivity {

    private List<Ingredient> ingredients = new ArrayList<>();
    private IngredientAdapter adapter;
    private ListView listIngredients;
    private EditText editIngredient;
    private Ingredient ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editIngredient = findViewById(R.id.editIngredient);

        /**
         * affiche la liste vide par defaut
         */
        listIngredients = findViewById(R.id.listViewIngredients);
        adapter = new IngredientAdapter(SearchActivity.this,
                R.layout.item_ingredient,
                ingredients
        );
        listIngredients.setAdapter(adapter);

    }

    public void submit(View view) {


    }

    public void addIngredient(View view) {
        /**
         * Ajoute un ingredient dans la liste
         * et raz edittext
         */
       if(editIngredient != null ){
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
