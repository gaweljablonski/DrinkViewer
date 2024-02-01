package com.mojitoproject.drinkviewer;

public class ModelClass {
    private int id;
    private String Name, Description, Ingredients, Vibe;

    public ModelClass(int id, String vibe, String name, String description, String ingredients) {
        this.id = id;
        Vibe = vibe;
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

    public String getVibe() {
        return Vibe;
    }

    public void setPercentage(String vibe) {
        Vibe = vibe;
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
