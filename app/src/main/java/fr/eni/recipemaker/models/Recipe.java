package fr.eni.recipemaker.models;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private String label;
    private String image;
    private String url;
    private List<String> healthLabels;
    private List<Ingredient> ingredients;
    private Double calories;

    public Recipe(String label, String image, String url, List<String> healthLabels, List<Ingredient> ingredients, Double calories) {
        this.label = label;
        this.image = image;
        this.url = url;
        this.healthLabels = healthLabels;
        this.ingredients = ingredients;
        this.calories = calories;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(List<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Recipe{");
        sb.append("label='").append(label).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", healthLabels=").append(healthLabels);
        sb.append(", ingredients=").append(ingredients);
        sb.append(", calories=").append(calories);
        sb.append('}');
        return sb.toString();
    }
}
