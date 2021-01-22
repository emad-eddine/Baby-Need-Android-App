package com.kichou.imad.babyneeds.listeners;

public interface AlertDialogInputListener {
    public void applyInputsField(String itemName , String itemQte , String itemColor , String itemSize, String date);
    public void updateInputsField(String itemName , String itemQte , String itemColor , String itemSize, String date);
}
