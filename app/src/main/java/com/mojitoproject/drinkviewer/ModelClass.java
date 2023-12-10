package com.mojitoproject.drinkviewer;

public class ModelClass {
    private int id, Percentage;
    private String Name, Description, Ingredients;

    public ModelClass(int id, int percentage, String name, String description, String ingredients) {
        this.id = id;
        Percentage = percentage;
        Name = name;
        Description = description;
        Ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPercentage() {
        return Percentage;
    }

    public void setPercentage(int percentage) {
        Percentage = percentage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }
}
