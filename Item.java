/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualrecipebookapp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Imran
 */
public abstract class Item {
       
    protected int itemID;
    protected String itemName; 
    protected String itemImagePath;
    protected String itemDescription;
    protected String fileName = "Items.txt";
    protected final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    //For empty instantiation
    public Item() {

        this.itemID = 0;
        this.itemName = "";
        this.itemImagePath = "";
        this.itemDescription = "";
 
    }

    //If ID is known

    public Item(int itemID, String itemName, String itemImagePath, String itemDescription) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemImagePath = itemImagePath;
        this.itemDescription = itemDescription;
  
    }
    
   abstract public void SaveToFile(FileWriter writer);

    abstract public void display(JTextArea textArea);

    abstract public boolean isIngredient();

    abstract public boolean isUtensil();

   abstract public Item loadFromFile(BufferedReader bin);
  
   abstract public boolean ItemCheck(ArrayList<Item> myItems);

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImagePath() {
        return itemImagePath;
    }

    public void setItemImagePath(String itemImagePath) {
        this.itemImagePath = itemImagePath;
    }


    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

  

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    

}
