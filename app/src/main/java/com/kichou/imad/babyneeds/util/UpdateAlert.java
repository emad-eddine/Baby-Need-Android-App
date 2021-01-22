package com.kichou.imad.babyneeds.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.kichou.imad.babyneeds.R;
import com.kichou.imad.babyneeds.listeners.AlertDialogInputListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateAlert  extends AppCompatDialogFragment {

    private EditText itemName;
    private EditText itemQte;
    private  EditText itemColor;
    private  EditText itemSize;
    private Button submitUpdateBtn;
    private AlertDialogInputListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.update_item_popup,null);

        build.setView(view);

        itemName = view.findViewById(R.id.item_name_input);
        itemQte = view.findViewById(R.id.item_qte_field);
        itemColor = view.findViewById(R.id.item_color_input);
        itemSize = view.findViewById(R.id.item_size_field);
        submitUpdateBtn = view.findViewById(R.id.add_item_btn);

        submitUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemNameInput = itemName.getText().toString();
                String itemQteInput = itemQte.getText().toString();
                String itemColorInput = itemColor.getText().toString();
                String itemSizeInput = itemSize.getText().toString();
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ", Locale.getDefault()).format(new Date());
                listener.updateInputsField(itemNameInput,itemQteInput,itemColorInput,itemSizeInput,date);
            }
        });

        return build.create();


    }





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AlertDialogInputListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString()
                    + "must implement AlertDialogInputListener");
        }
    }
}
