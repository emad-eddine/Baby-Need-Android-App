package com.kichou.imad.babyneeds;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kichou.imad.babyneeds.adapter.ItemAdapter;
import com.kichou.imad.babyneeds.data.DatabaseHandler;
import com.kichou.imad.babyneeds.listeners.AlertDialogInputListener;
import com.kichou.imad.babyneeds.listeners.ChangeItemListener;
import com.kichou.imad.babyneeds.model.ItemModel;
import com.kichou.imad.babyneeds.util.Alert;
import com.kichou.imad.babyneeds.util.UpdateAlert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AlertDialogInputListener, ChangeItemListener {

    private DatabaseHandler db = new DatabaseHandler(this);
    // alert
    private Alert alertDialog = new Alert();
    private UpdateAlert updateAlert = new UpdateAlert();
    // recycle view
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    private ItemModel itemModelToCHange;
    private List<ItemModel> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        items = db.getAllItem();

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(this,items);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.add_btn_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openDialog();
            }
        });
    }

    @Override
    public void applyInputsField(String itemName, String itemQte, String itemColor, String itemSize , String date) {
        ItemModel itemModel = new ItemModel(itemName,Integer.parseInt(itemQte),
                itemColor,Integer.parseInt(itemSize),date);
        db.addItemToDb(itemModel);
        closeDialog();
        recreate();
    }

    @Override
    public void updateInputsField(String itemName, String itemQte, String itemColor, String itemSize, String date) {
        itemModelToCHange.setItemName(itemName);
        itemModelToCHange.setItemQte(Integer.parseInt(itemQte));
        itemModelToCHange.setItemColor(itemColor);
        itemModelToCHange.setItemSize(Integer.parseInt(itemSize));
        itemModelToCHange.setCreatedDate(date);
    }

    @Override
    public void dataChanged(String op, int postiton) {
        if(op.equals("delete"))
        {
            adapter.notifyItemRemoved(postiton);
        }
        else{
             itemModelToCHange = items.get(postiton);
             openUpdateDialog();


        }
    }


    // open and close dialog
    private void openDialog()
    {
        alertDialog.show(getSupportFragmentManager(),"input dialog");
    }
    private void closeDialog()
    {
        alertDialog.dismiss();
    }

    // update item

    public void openUpdateDialog()
    {
        updateAlert.show(getSupportFragmentManager(),"update dialog");
    }
    public void closeUpdateDialog()
    {
        updateAlert.dismiss();
    }
}