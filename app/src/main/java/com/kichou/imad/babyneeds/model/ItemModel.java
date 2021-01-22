package com.kichou.imad.babyneeds.model;

public class ItemModel {

    private int itemId;
    private String itemName;
    private int itemQte;
    private String itemColor;
    private int itemSize;


    public ItemModel()
    {

    }

    public ItemModel( String itemName, int itemQte, String itemColor, int itemSize) {

        this.itemName = itemName;
        this.itemQte = itemQte;
        this.itemColor = itemColor;
        this.itemSize = itemSize;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQte() {
        return itemQte;
    }

    public void setItemQte(int itemQte) {
        this.itemQte = itemQte;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public int getItemSize() {
        return itemSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
