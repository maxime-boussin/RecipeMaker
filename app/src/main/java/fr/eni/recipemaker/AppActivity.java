package fr.eni.recipemaker;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.eni.recipemaker.favorites.FavoritesActivity;
import fr.eni.recipemaker.search.SearchActivity;

public class AppActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pour splashscreen
        if(getSupportActionBar() != null) {
            if(!(this instanceof MainActivity || this instanceof SearchActivity)) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            getSupportActionBar().setLogo(R.drawable.ic_launcher_round);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }
    }

    public void toggleFavorites(MenuItem item) {
        Intent intentFavorites = new Intent(this, FavoritesActivity.class);
        startActivity(intentFavorites);
    }
}
