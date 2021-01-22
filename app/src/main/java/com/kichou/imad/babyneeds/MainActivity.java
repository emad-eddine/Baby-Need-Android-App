package com.kichou.imad.babyneeds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.kichou.imad.babyneeds.data.DatabaseHandler;
import com.kichou.imad.babyneeds.listeners.AlertDialogInputListener;
import com.kichou.imad.babyneeds.model.ItemModel;
import com.kichou.imad.babyneeds.util.Alert;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AlertDialogInputListener {

   private ImageButton addBtn ;
   private Button submitAddBtn;
   private EditText itemName;
   private EditText itemQte;
   private  EditText itemColor;
   private  EditText itemSize;
   private  DatabaseHandler db = new DatabaseHandler(this);
   private Alert alertDialog = new Alert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.add_btn);




        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openDialog();

            }
        });


    }

    private void openDialog()
    {


        alertDialog.show(getSupportFragmentManager(),"input dialog");

    }
    private void closeDialog()
    {
        alertDialog.dismiss();
    }

    @Override
    public void applyInputsField(String itemName, String itemQte, String itemColor, String itemSize) {
        ItemModel itemModel = new ItemModel(itemName,Integer.parseInt(itemQte),
                itemColor,Integer.parseInt(itemSize));

        db.addItemToDb(itemModel);
        closeDialog();
        nextActivity();

    }

    private void nextActivity()
    {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }


}