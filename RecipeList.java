/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualrecipebookapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Imran
 */
public class RecipeList {
       String filename;
    ArrayList <Recipe> myRecipe;
    
    public RecipeList()
    {
        filename = "recipe.txt";
        myRecipe = new ArrayList();
        LoadFromFile();
    }
    //match getter with input if same then we have i
    public Integer find(String recipename)
    {
        Integer arrayPosition = null;
        for (int i = 0; i < myRecipe.size(); i++)
        {
            if(myRecipe.get(i).getRecipeName().equals(recipename))
            {
                arrayPosition = i;             
            }
        }
        return arrayPosition;
    }   
    public void add(Recipe src)
    {
        myRecipe.add(src);
    }

      public void getRecipeList(JList jRecipeList)
    {
    for (int i = 0; i < myRecipe.size(); i++)
    {
    myRecipe.get(i).recipeTableGet(jRecipeList);
    }
    }
      //after we have obtained i can we proceed to display
    public void SearchProduct(JTextArea jRecipeTextArea, JLabel imageLabel, String recipename)
    {
    Integer ArrayPosition = find(recipename);
    myRecipe.get(ArrayPosition).display(jRecipeTextArea,imageLabel);
    }
   
     public void searchRecipeByName(JList jListRecipe, String recipeName)
    {
    for (int i = 0; i < myRecipe.size(); i++)
    {
    myRecipe.get(i).searchRecipeByName(jListRecipe,recipeName);
    }
    }
    
    public void searchRecipeBySeason(JList jListRecipe, String recipeName)
    {
    for (int i = 0; i < myRecipe.size(); i++)
    {
    myRecipe.get(i).searchRecipeBySeason(jListRecipe,recipeName);
    }
    }
    public void searchRecipeByRegion(JList jListRecipe, String recipeName)
    {
    for (int i = 0; i < myRecipe.size(); i++)
    {
    myRecipe.get(i).searchRecipeByRegion(jListRecipe, recipeName);
    }
    }
    public void searchRecipeByCookingTime(JList jListRecipe, String recipeName)
    {
    for (int i = 0; i < myRecipe.size(); i++)
    {
    myRecipe.get(i).searchRecipeByCookingTime(jListRecipe,recipeName);
    }
    }
    public void searchRecipeByMeatFree(JList jListRecipe, boolean recipeName)
    {
    for (int i = 0; i < myRecipe.size(); i++)
    {
    myRecipe.get(i).searchRecipeByMeatFree(jListRecipe,recipeName);
    }
    }
    public void searchRecipeByGluten(JList jListRecipe, boolean recipeName)
    {
    for (int i = 0; i < myRecipe.size(); i++)
    {
    myRecipe.get(i).searchRecipeByGluten(jListRecipe,recipeName);
    }
    }
    public void searchRecipeAvailable(JList jListRecipe, ArrayList<Item> theIngredients)
    {
    for (int i = 0; i < myRecipe.size(); i++)
    {
    myRecipe.get(i).searchRecipeAvailable(jListRecipe,theIngredients);
    }
    }
    
    
    public Recipe recipeAdd(JList jListRecipe,String recipeName)
    {
    Recipe theRecipe = null;
    for (int i = 0; i < myRecipe.size(); i++)
    {
    theRecipe = myRecipe.get(i).recipeAdd(jListRecipe,recipeName);
    if(theRecipe!=null)
    {
    return theRecipe;
    }
    }
    return theRecipe;
    }
    
    public void LoadFromFile()
    {        
        String record;
        Recipe theRecipe = new Recipe();
        FileReader reader;
        try
        {
            reader = new FileReader(filename);
            BufferedReader bin = new BufferedReader(reader);
            record = new String();
            while ((record = bin.readLine()) !=null)
            {
                theRecipe = theRecipe.loadFromFile(bin);
                add(theRecipe);
            }
            bin.close();
            bin = null;            
        }
        catch (IOException ice)
        {
        }        
    }
}
