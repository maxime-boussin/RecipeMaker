package fr.eni.recipemaker.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.List;

public class Preference {
    private static final String PREF_RECIPE = "recipe";
    private static final String PREF_LIST_RECIPE = "listRecipe";

    private static SharedPreferences getPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setRecipe(Context context, String recipe) {
        getPreference(context)
                .edit()
                .putString(PREF_RECIPE, recipe)
                .apply();
    }

    public static String getRecipe(Context context) {
        return getPreference(context).getString(PREF_RECIPE, null);
    }

    public static void setListRecipe(Context context, List<Hits> hits) {
        Gson gson = new Gson();

        String json = gson.toJson(hits);

        getPreference(context)
                .edit()
                .putString(PREF_LIST_RECIPE, json)
                .apply();
    }

}
