package fr.eni.recipemaker.favorites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import fr.eni.recipemaker.AppActivity;
import fr.eni.recipemaker.R;

public class FavoritesActivity extends AppActivity {
    private TextView testId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        SharedPreferences sp = getSharedPreferences("PREF_MODE", MODE_PRIVATE);

        testId = (TextView) findViewById(R.id.testId);
        testId.setText(sp.getString("title", "default"));

        //TODO display favorites list
    }
}
