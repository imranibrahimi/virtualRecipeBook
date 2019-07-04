/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualrecipebookapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Imran
 */
public class Recipe {
      
    private int recipeID;
    private String recipeName;
    private String recipeImagePath;
    private String recipeDescription;
    private int cookingTime;
    private String season;
    private String region;
    private Boolean meatFree;
    private Boolean glutenFree;
    private String recipeGuide;
   private ArrayList<Ingredient> myRecipeIngredients = new ArrayList();
    private ArrayList<Utensil> myRecipeEquipment = new ArrayList();
    Ingredient myIngredients = new Ingredient();
    Utensil myEquipment = new Utensil();
    
    
    public Recipe() {
        this.recipeID = 0;
        this.recipeName = "";
        this.recipeImagePath = "";
        this.recipeDescription = "";
        this.cookingTime = 0;
        this.season ="";
        this.region ="";
        this.meatFree = false;
        this.glutenFree = false;
        this.recipeGuide = "";
    }

    public Recipe(int recipeID, String recipeName, String recipeImagePath, String recipeDescription, int cookingTime, String season, String region, Boolean meatFree, Boolean glutenFree, String recipeGuide) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeImagePath = recipeImagePath;
        this.recipeDescription = recipeDescription;
        this.cookingTime = cookingTime;
        this.season = season;
        this.region = region;
        this.meatFree = meatFree;
        this.glutenFree = glutenFree;
        this.recipeGuide = recipeGuide;
    }


    

    public void addIngredients(Ingredient myIngredient)
    {
        myRecipeIngredients.add(myIngredient);
    }
    
       public void recipeTableGet(JList jtheRecipeList)
    {
        DefaultListModel model = (DefaultListModel)
        jtheRecipeList.getModel();      
        model.addElement(recipeName);
      
      
    }
    public void display(JTextArea myRecipeTextArea, JLabel imageLabel)
    {
        myRecipeTextArea.append("Recipe ID: " + recipeID + "\n");
        myRecipeTextArea.append("Name: " +recipeName + "\n");
        myRecipeTextArea.append("An image of the recipe will be added on update:" + "\n");
        myRecipeTextArea.append("Description: "  +recipeDescription + "\n");
        myRecipeTextArea.append("Cooking Time: " +cookingTime + "\n");
        myRecipeTextArea.append("Season: " +season + "\n");
        myRecipeTextArea.append("Region: " +region + "\n");
        myRecipeTextArea.append("Meat-free?  " +meatFree + "\n");
        myRecipeTextArea.append("Gluten-free? "+glutenFree + "\n");
        myRecipeTextArea.append("Guide: " + recipeGuide + "\n");
        myRecipeTextArea.append("\n");
        for (int i = 0; i < myRecipeIngredients.size(); i++) 
        {
            myRecipeIngredients.get(i).display(myRecipeTextArea);
        }
        myRecipeTextArea.append("\n");
        for (int i = 0; i < myRecipeIngredients.size(); i++) 
        {
            myRecipeEquipment.get(i).display(myRecipeTextArea);
        }
    }
   
    public void searchRecipeByName(JList ListRecipe, String data)
    {
    if(data.contentEquals(recipeName))
    {
    DefaultListModel model = (DefaultListModel)
    ListRecipe.getModel();
    model.addElement(recipeName);

    }
    
    }
    public void searchRecipeByRegion(JList ListRecipe, String data)
    {
    if(data.contentEquals(region))
    {
   DefaultListModel model = (DefaultListModel)
    ListRecipe.getModel();
    model.addElement(recipeName);
    }
    }
    
    public void searchRecipeBySeason(JList ListRecipe, String data)
    {
    if(data.contentEquals(season))
    {
   DefaultListModel model = (DefaultListModel)
    ListRecipe.getModel();
    model.addElement(recipeName);
    }
    }
    public void searchRecipeByCookingTime(JList ListRecipe, String data)
    {
    if(cookingTime<=Integer.parseInt(data))
    {
   DefaultListModel model = (DefaultListModel)
    ListRecipe.getModel();
   model.addElement(recipeName);
    }
    }
    public void searchRecipeByMeatFree(JList ListRecipe, boolean data)
    {
    if(meatFree==data)
    {
   DefaultListModel model = (DefaultListModel)
    ListRecipe.getModel();
     model.addElement(recipeName);
    }
    }
    public void searchRecipeByGluten(JList ListRecipe, boolean data)
    {
    if(glutenFree==data)
    {
    DefaultListModel model = (DefaultListModel)
    ListRecipe.getModel();
    model.addElement(recipeName);
    }
    }
    
   
     public void searchRecipeAvailable(JList ListRecipe,ArrayList<Item> theIngredients)
    {
        boolean check = false;
        int ingredientCount = 0;
        for (int i = 0; i < myRecipeIngredients.size(); i++) 
        {
       check = myRecipeIngredients.get(i).ItemCheck(theIngredients); 
            if(check==true)
            {
                ingredientCount++;
            }
        }
        if(ingredientCount==myRecipeIngredients.size())
        {
             DefaultListModel model = (DefaultListModel)
             ListRecipe.getModel();
             model.addElement(recipeName);
        }
    }
     

     
       public Recipe recipeAdd(JList ListRecipe,String recipeName)
    {
        if(recipeName.equals(this.recipeName))
        {
            return this;
        }
        else return null;
    }
   public Recipe loadFromFile(BufferedReader bin)
    {        
        Recipe theRecipe = null;
        try
        {
            theRecipe = new Recipe(Integer.parseInt(bin.readLine()),
                        bin.readLine(),bin.readLine(),bin.readLine(),
                        Integer.parseInt(bin.readLine()),bin.readLine(),
                        bin.readLine(),Boolean.valueOf(bin.readLine()),
                        Boolean.valueOf(bin.readLine()),bin.readLine());
            String record;
            while (!(record = bin.readLine()).equals("##End Of Ingredients##"))
            {
                theRecipe.myRecipeIngredients.add(myIngredients.loadFromFile(bin));
            }
            while (!(record = bin.readLine()).equals("##End Of Utensils##"))
            {
                theRecipe.myRecipeEquipment.add((Utensil) myEquipment.loadFromFile(bin)); //
            }
        }
        catch (IOException ice)
        {
            System.out.println("i have error in ingredients");
        }
        return theRecipe;
    }

   /*   public int getID() {
   return recipeID;
   }*/
    public String getRecipeName() {
        return recipeName;
    }

    public ArrayList<Ingredient> getMyRecipeIngredients() {
        return myRecipeIngredients;
    }
    
}
