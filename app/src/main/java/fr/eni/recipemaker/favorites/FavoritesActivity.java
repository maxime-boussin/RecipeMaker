package fr.eni.recipemaker.favorites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import fr.eni.recipemaker.AppActivity;
import fr.eni.recipemaker.R;
import fr.eni.recipemaker.models.Recipe;

public class FavoritesActivity extends AppActivity {
    private TextView testId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        SharedPreferences sp = getSharedPreferences("PREF_MODE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("recipes", "");
        Type type = new TypeToken<List<Recipe>>() {}.getType();
        List<Recipe> recipes = gson.fromJson(json, type);

        testId = (TextView) findViewById(R.id.testId);
        testId.setText(sp.getString("title", "default"));

        //TODO display favorites list
    }
}
