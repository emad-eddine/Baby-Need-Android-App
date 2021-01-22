package com.kichou.imad.babyneeds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.kichou.imad.babyneeds.adapter.ItemAdapter;
import com.kichou.imad.babyneeds.data.DatabaseHandler;
import com.kichou.imad.babyneeds.listeners.AlertDialogInputListener;
import com.kichou.imad.babyneeds.listeners.ChangeItemListener;
import com.kichou.imad.babyneeds.model.ItemModel;
import com.kichou.imad.babyneeds.util.Alert;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements AlertDialogInputListener , ChangeItemListener {


    private DatabaseHandler db = new DatabaseHandler(this);
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ImageButton addBtn;
    private Alert alertDialog = new Alert();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         List<ItemModel> items = new ArrayList<>();
         items = db.getAllItem();

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(this,items);
        recyclerView.setAdapter(adapter);

        // set listener to add btn

        addBtn = findViewById(R.id.add_btn_main);

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
        // db.addItemToDb(itemModel);
         db.upgradeItem(itemModel);

        closeDialog();
        recreate();
    }

    @Override
    public void dataChanged(String op , int postiton) {
        if(op.equals("delete"))
        {
            adapter.notifyItemRemoved(postiton);
        }
        else{
            openDialog();

        }
    }
}