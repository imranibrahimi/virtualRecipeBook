/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualrecipebookapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
public class Kitchen {
    private String filenameIngredients;
    private String filenameUtensils;
    public RecipeList myRecipeList;
    ArrayList<Recipe> myRecipes = new ArrayList();
    ArrayList<Item> myOrder = new ArrayList();    
    private ArrayList<Item> myIngredients;
    private ArrayList<Item> myUtensils;

    public Kitchen() {
        myIngredients = new ArrayList();
        myUtensils = new ArrayList();
        filenameIngredients = "myIngredients.txt";
        filenameUtensils = "myEquipment.txt";
        myRecipeList = new RecipeList();
        LoadIngredientsFromFile();
        LoadEquipmentFromFile();
    }

    public RecipeList getMyRecipeList() {
        return myRecipeList;
    }
    
    public void addIngredients(Ingredient myItem)
    {
        myIngredients.add(myItem);
    }
    public void addUtensils(Utensil myItem)
    {
        myUtensils.add(myItem);
    }
 
       public void getRecipeList(JList jRecipeList)
    {
        myRecipeList.getRecipeList(jRecipeList);
    }
    public void SearchProduct(JTextArea jRecipeTextArea, JLabel imageLabel, String recipename)
    {
        myRecipeList.SearchProduct(jRecipeTextArea, imageLabel, recipename);
    }
     
         public void searchRecipeByName(JList ListRecipe, String Name)
    {
    myRecipeList.searchRecipeByName(ListRecipe, Name);
    }
     
    public void searchRecipeAvailable(JList ListRecipe)
    {
    myRecipeList.searchRecipeAvailable(ListRecipe, myIngredients);
    }
    
    public void LoadIngredientsFromFile()
    {        
        String record;
        FileReader reader;
        try
        {
            reader = new FileReader(filenameIngredients);
            BufferedReader bin = new BufferedReader(reader);
            record = new String();
            while ((record = bin.readLine()) !=null)
            {
                Ingredient theIngredient = new Ingredient();
                theIngredient = theIngredient.loadFromFile(bin);
                addIngredients(theIngredient);
            }
            bin.close();
            bin = null;            
        }
        catch (IOException ice)
        {
        }        
    }
    public void LoadEquipmentFromFile()
    {        
        String record;
        FileReader reader;
        try
        {
            reader = new FileReader(filenameUtensils);
            BufferedReader bin = new BufferedReader(reader);
            record = new String();
            while ((record = bin.readLine()) !=null)
            {
                Item theUtensil = new Utensil();//change to item
                theUtensil = theUtensil.loadFromFile(bin);
                addUtensils((Utensil) theUtensil);
            }
            bin.close();
            bin = null;            
        }
        catch (IOException ice)
        {
        }        
    }
    public void SaveIngredientsToFile()
    {     
        FileWriter writer;        
        try
        {
            writer = new FileWriter(filenameIngredients);
            writer.write("");
            for (int i = 0; i < myIngredients.size(); i++) 
            {
                writer.write("Ingredient:"+System.getProperty("line.separator"));
                myIngredients.get(i).SaveToFile(writer);
            }            
            writer.flush();
            writer.close();
            writer = null;
        }
        catch (IOException ioe)
        {
        }
    }
    public void SaveUtensilToFile()
    {     
        FileWriter writer;        
        try
        {
            writer = new FileWriter(filenameUtensils);
            writer.write("");
            for (int i = 0; i < myUtensils.size(); i++) 
            {
                writer.write("Equipment:"+System.getProperty("line.separator"));
                myUtensils.get(i).SaveToFile(writer);
            }            
            writer.flush();
            writer.close();
            writer = null;
        }
        catch (IOException ioe)
        {
        }
    }
     
         public void add(JList ListRecipe, Recipe theRecipe)
    {
        boolean checkID = false;
        for (int i = 0; i < myRecipes.size(); i++) 
        {
            if(myRecipes.get(i).getRecipeName().equals(theRecipe.getRecipeName()))
            {
                checkID = true;
            }
        }
        if(!checkID)
        {
            myRecipes.add(theRecipe);
        display(ListRecipe);
        }        
    }
    public void remove(JList ListRecipe, String recipeName)
    {
        for (int i = 0; i < myRecipes.size(); i++) 
        {
            if(myRecipes.get(i).getRecipeName().equals(recipeName))
            {
                myRecipes.remove(myRecipes.get(i));
            }
        }
        display(ListRecipe);
    }
    
      public void recipeAdd(JList ListRecipe, String recipeName)
    {
        Recipe m = myRecipeList.recipeAdd(ListRecipe, recipeName);
        boolean checkID = false;
        for (int i = 0; i < myRecipes.size(); i++) 
        {
            if(myRecipes.get(i).getRecipeName().equals(recipeName))
            {
                checkID = true;
            }
        }
        if(!checkID)
        {
            myRecipes.add(m);
            displayMenu(ListRecipe);
        }
    }
     
             public void displayMenu(JList ListRecipe)
      {
      DefaultListModel model = (DefaultListModel)
      ListRecipe.getModel();   
      for (int i = model.getSize(); i >= 1; i--)
      {
      model.removeElementAt(i-1);
      }
      for (int i = 0; i < myRecipes.size(); i++)
      {
      model.addElement(myRecipes.get(i).getRecipeName());
 
      }
    }
          
             
  public void recipeRemove(JList ListRecipe,String recipeName)
    
    {
        for (int i = 0; i < myRecipes.size(); i++) 
        {
            if(myRecipes.get(i).getRecipeName().equals(recipeName))
            {
                myRecipes.remove(myRecipes.get(i));
            }
        }
        display(ListRecipe);
    }
    public void recipeOrder(JList ListRecipe)
    {
        for (int i = 0; i < myRecipes.size(); i++) 
        {
            boolean check = false;
            for (int j = 0; j < myRecipes.get(i).getMyRecipeIngredients().size(); j++) 
            {
                check = myRecipes.get(i).getMyRecipeIngredients().get(j).ItemCheck(myOrder);
                if(check==false)
                {
                    myOrder.add(myRecipes.get(i).getMyRecipeIngredients().get(j));
                }
            }
        }
        displayOrder(ListRecipe);
    }
       public void displayOrder(JList ListRecipe)
    {
    DefaultListModel model = (DefaultListModel)
    ListRecipe.getModel();
    for (int i = 0; i < myOrder.size(); i++)
    {
    
    model.addElement(Arrays.toString(new Object[]{myOrder.get(i).getItemName(),((Ingredient) myOrder.get(i)).getAmount(),((Ingredient) myOrder.get(i)).getAmountType()}));
    }
    }
    public void recipeOrderIngredients()
    {
        for (int i = 0; i < myIngredients.size(); i++) 
        {
            for (int j = 0; j < myRecipes.size(); j++) 
            {
                for (int k = 0; k < myRecipes.get(j).getMyRecipeIngredients().size(); k++) 
                {
                    if(myRecipes.get(j).getMyRecipeIngredients().get(k).getItemName().contentEquals(myIngredients.get(i).getItemName()))
                    {
                        double oldQty = ((Ingredient) myIngredients.get(i)).getAmount();
                        ((Ingredient) myIngredients.get(i)).setAmount((myRecipes.get(j).getMyRecipeIngredients().get(k).getAmount())+oldQty);
                        System.out.println(oldQty);
                    }
                }
            }
        }
        //SaveIngredientsToFile(); not working as to update my kitchen ingredients, therefore method has been commented/omitted
    }
   
  public void displayRecipeOrder(JList ListRecipe)
    {
        DefaultListModel model = (DefaultListModel)
        ListRecipe.getModel();
        for (int i = 0; i < myOrder.size(); i++) 
        {
            model.addElement(Arrays.toString(new Object[]{myOrder.get(i).getItemName(),((Ingredient) myOrder.get(i)).getAmount(),((Ingredient) myOrder.get(i)).getAmountType()}));
        }
    }
    public void display(JList ListRecipe)
    {
        DefaultListModel model = (DefaultListModel)
        ListRecipe.getModel();
        for (int i = model.getSize(); i >= 1; i--) 
        {
            model.removeElementAt(i-1);                
        }
        for (int i = 0; i < myRecipes.size(); i++) 
        {
            model.addElement(Arrays.toString(new Object[]{myRecipes.get(i).getRecipeName()}));
        }
    }
    public ArrayList<Item> getMyIngredients() {
        return myIngredients;
    }
    
}
