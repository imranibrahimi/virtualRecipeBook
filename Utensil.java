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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Imran
 */
public class Utensil extends Item{
        private int guarantee; //in days

    //empty initialization
    public Utensil() {
        super();
        this.guarantee = 0;
    }
//generate ID

    public Utensil(int itemID, String itemName, String itemImagePath, String itemDescription, int guarantee) {
        super(itemID, itemName, itemImagePath, itemDescription);
        this.guarantee = guarantee;
    }
   
   

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    @Override
    public boolean isIngredient() {
        return false;
    }

    @Override
    public boolean isUtensil() {
        return true;
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
            writer.write(this.getGuarantee()+System.getProperty("line.seperator"));
        }
        catch (IOException ioe)
        {
        }
    }
    @Override
        public Item loadFromFile(BufferedReader bin)
    {        
        Utensil theEquipment = null;
        try
        {
            theEquipment = new Utensil(Integer.parseInt(bin.readLine()),bin.readLine(), bin.readLine(), bin.readLine(),Integer.parseInt(bin.readLine())); //akash help
        }
        catch (IOException ice)
        {
            System.out.println("i have error in Equipment");
        }
        return theEquipment;
    }
        
           @Override
   public boolean ItemCheck(ArrayList<Item> myItems)
    {
        boolean haveUtensil = false;
        for (int i = 0; i < myItems.size(); i++) 
        {
            if(myItems.get(i).itemName.contentEquals(itemName))
            {
                  haveUtensil = true;
            }
        }
        return haveUtensil;
    }
      @Override
      public void display(JTextArea textArea)
    {
        textArea.append("Item ID: " +super.itemID + System.getProperty("line.separator"));
        textArea.append("Name: " +super.itemName + System.getProperty("line.separator"));
        textArea.append("An image of the item will be uploaded on update " +super.itemImagePath + System.getProperty("line.separator"));
        textArea.append("Description: "+super.itemDescription + System.getProperty("line.separator"));
         textArea.append("Guarantee (in days) :" +this.guarantee + System.getProperty("line.separator"));
    }
      

}
    

