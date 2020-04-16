package fr.eni.recipemaker;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
            case R.id.favorites:
                //TODO toggle favorites menu
                //TODO display favorites list
                //TODO on favorites menu, redirect to detailActivity onClick
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pour splashscreen
        if(getSupportActionBar() != null) {
            if(!(this instanceof MainActivity)) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

    }

    public void toggleFavorites(MenuItem item) {
        //Toast.makeText(this, "You have selected Bookmark Menu", Toast.LENGTH_SHORT).show();
    }
}
