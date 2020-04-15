package fr.eni.recipemaker.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.eni.recipemaker.R;
import fr.eni.recipemaker.models.Ingredient;
import fr.eni.recipemaker.models.Recipe;
import fr.eni.recipemaker.search.IngredientAdapter;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView labelView;
    private ListView ingredientListView;
    private ListView nutrimentView;
    private TextView urlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        labelView = findViewById(R.id.labelView);
        ingredientListView = findViewById(R.id.ingredientListView);
        nutrimentView = findViewById(R.id.nutrimentView);
        urlView = findViewById(R.id.urlView);

        if(getIntent().getExtras() != null) {
//            final List<Ingredient> ingredientList = new ArrayList<>();
//            ingredientList.add(new Ingredient("Crème", 100.0 ));
//            ingredientList.add(new Ingredient("Pâtes", 500.0 ));
            Recipe item = (Recipe)getIntent().getExtras().get("object");

            Picasso.get().load(item.getImage()).into(imageView);
            labelView.setText(item.getLabel());
            urlView.setText(item.getUrl());
//            ingredientView.setAdapter(new ArrayAdapter<Ingredient>(DetailActivity.this, R.layout.item_ingredient, ingredientList));

        }
        final List<Ingredient> ingredientList = new ArrayList<>();
        ingredientListView.setAdapter(new IngredientAdapter(DetailActivity.this, R.layout.item_ingredient, ingredientList));
        ingredientList.add(new Ingredient("100g de crème"));
        ingredientList.add(new Ingredient("500g de Pâtes"));
    }
}
