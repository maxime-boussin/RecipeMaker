package fr.eni.recipemaker.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
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

import fr.eni.recipemaker.R;
import fr.eni.recipemaker.models.Ingredient;
import fr.eni.recipemaker.models.Recipe;
import fr.eni.recipemaker.search.IngredientAdapter;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView labelView;
    private LinearLayout ingredientLinearLayout;
    private FlexboxLayout tagLinearLayout;
    private TextView urlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        labelView = findViewById(R.id.labelView);
        ingredientLinearLayout = findViewById(R.id.ingredientLinearLayout);
        tagLinearLayout = findViewById(R.id.tagLinearLayout);
        tagLinearLayout.setFlexDirection(FlexDirection.ROW);
        tagLinearLayout.setFlexWrap(FlexWrap.WRAP);
        urlView = findViewById(R.id.urlView);

        final List<Ingredient> ingredientList = new ArrayList<>();
        final List<String> tagList = new ArrayList<>();

        if(getIntent().getExtras() != null) {
            Recipe item = (Recipe)getIntent().getExtras().get("object");

            Picasso.get().load(item.getImage()).into(imageView);
            labelView.setText(item.getLabel());
            urlView.setText(item.getUrl());

            for(Ingredient ingredient : item.getIngredients()) {
                ingredientLinearLayout.addView(getIngredient(DetailActivity.this, ingredient.getText()));
            }

            for(String tag : item.getHealthLabels()) {
                tagLinearLayout.addView(getTag(DetailActivity.this, tag));
            }

        }
        Picasso.get().load(R.drawable.carbo).into(imageView);
        labelView.setText("Pâtes carbonara");
        urlView.setText("https://www.lesbonnnespatescarbo.fr");

        ingredientList.add(new Ingredient("100g de crème"));
        ingredientList.add(new Ingredient("500g de Pâtes"));

        for(Ingredient ingredient : ingredientList) {
            ingredientLinearLayout.addView(getIngredient(DetailActivity.this, ingredient.getText()));
        }

        tagList.add("Peanut-Free");
        tagList.add("Alcohol-Free");
        tagList.add("Peanut-Free");
        tagList.add("Alcohol-Free");

        for(String tag : tagList) {
            tagLinearLayout.addView(getTag(DetailActivity.this, tag));
        }
    }

    private View getIngredient(Context context, String value) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ingredient, null);

        TextView ingredientName = view.findViewById(R.id.ingredientName);
        ingredientName.setText(value);

        return view;
    }

    private View getTag(Context context, String value) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tag, null);

        TextView tagName = view.findViewById(R.id.tagName);
        tagName.setText(value);

        return view;
    }
}
