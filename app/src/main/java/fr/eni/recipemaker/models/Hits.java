package fr.eni.recipemaker.models;

import java.io.Serializable;
import java.util.List;

public class Hits implements Serializable {
    private Recipe recipe;

    public Hits(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Hits{");
        sb.append("recipe=").append(recipe);
        sb.append('}');
        return sb.toString();
    }
}
