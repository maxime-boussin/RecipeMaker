package fr.eni.recipemaker.models;

public class Ingredient {
    private String text;
    private Double weight;

    public Ingredient(String text, Double weight) {
        this.text = text;
        this.weight = weight;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
