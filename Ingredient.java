/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualrecipebookapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Imran
 */
public class Ingredient extends Item{
    private double amount;
    private String amountType;

    //empty initialization
    public Ingredient() {
        super();
            this.amount = 0.0;
            this.amountType = "";
  
    }


//known ID
    public Ingredient( int itemID, String itemName, String itemImagePath, String itemDescription, double amount, String amountType) {
        super(itemID, itemName, itemImagePath, itemDescription);
   
     this.amount = amount;
     this.amountType = amountType;
    }

    @Override
    public boolean isIngredient() {
        return true;
    }

    @Override
    public boolean isUtensil() {
        return false;
    }

  
    @Override
    public void SaveToFile(FileWriter writer) 
    {
        try
        {                     
            writer.write(super.itemID+System.getProperty("line.separator"));
            writer.write(super.itemName+System.getProperty("line.separator"));
            writer.write(super.itemImagePath+System.getProperty("line.separator"));
            writer.write(super.itemDescription+System.getProperty("line.seperator"));
            writer.write(this.amount+System.getProperty("line.separator"));
            writer.write(this.amountType+System.getProperty("line.separator"));
        }
        catch (IOException ioe)
        {
        }
    }
               
    @Override  
               public Ingredient loadFromFile(BufferedReader bin)
    {        
        Ingredient theIngredients = null;
        try
        {
            theIngredients = new Ingredient(Integer.parseInt(bin.readLine()),bin.readLine(),bin.readLine(),bin.readLine(),Double.parseDouble(bin.readLine()),bin.readLine());//(bin.readLine(),bin.readLine(),Double.parseDouble(bin.readLine()),bin.readLine()) //akash help me
        }
        catch (IOException ice)
        {
            System.out.println("i have error in ingredients");
        }
        return theIngredients;
    }
               
   @Override
   public boolean ItemCheck(ArrayList<Item> myItems)
    {
        boolean haveIngredient = false;
        for (int i = 0; i < myItems.size(); i++) 
        {
            Ingredient theItem = (Ingredient) myItems.get(i);
            if(myItems.get(i).itemName.contentEquals(itemName))
            {
                
                if(theItem.amount>=amount)
                {
                    
                   haveIngredient = true;
               }
            }
        }
        return haveIngredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }
   @Override
      public void display(JTextArea jRecipeTextArea)
    {
        jRecipeTextArea.append("Item ID: " +super.itemID + System.getProperty("line.separator"));
        jRecipeTextArea.append("Name: " +super.itemName + System.getProperty("line.separator"));
        jRecipeTextArea.append("An image of the item will be uploaded on update  " +super.itemImagePath + System.getProperty("line.separator"));
        jRecipeTextArea.append("Description: " +super.itemDescription + System.getProperty("line.separator"));
         jRecipeTextArea.append("Amount: " +this.amount + System.getProperty("line.separator"));
          jRecipeTextArea.append("AmountType: "+this.amountType + System.getProperty("line.separator"));
    }

}
               
       
