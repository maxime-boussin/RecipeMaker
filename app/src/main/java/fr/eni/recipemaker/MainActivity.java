package fr.eni.recipemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Timer;
import java.util.TimerTask;

import fr.eni.recipemaker.detail.DetailActivity;
import fr.eni.recipemaker.search.IngredientAdapter;
import fr.eni.recipemaker.search.SearchActivity;

public class MainActivity extends AppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intentHome = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intentHome);
            }
        },3000);
    }

    public void supprIngredient(View view) {

    }
}
