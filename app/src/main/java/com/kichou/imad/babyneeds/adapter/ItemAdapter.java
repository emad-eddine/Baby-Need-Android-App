package com.kichou.imad.babyneeds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kichou.imad.babyneeds.R;
import com.kichou.imad.babyneeds.data.DatabaseHandler;
import com.kichou.imad.babyneeds.listeners.ChangeItemListener;
import com.kichou.imad.babyneeds.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>  {

    private List<ItemModel> itemsList;
    private Context context;
    public ItemAdapter(Context context , List<ItemModel> items)
    {
        this.context = context;
        this.itemsList = items;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        ItemViewHolder iVH = new ItemViewHolder(v,context);
        return iVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {


         ItemModel item = itemsList.get(position);
         holder.itemName.setText(item.getItemName());
         holder.itemQte.setText(String.valueOf(item.getItemQte()));
         holder.itemColor.setText(item.getItemColor());
         holder.itemSize.setText(String.valueOf(item.getItemSize()));
         holder.createDate.setText("created on " + item.getCreatedDate());

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }



    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TextView itemName,itemQte,itemColor,itemSize,createDate;
        private Button deleteBtn , updateBtn;
        private Context context;
        private ChangeItemListener listener;
        public ItemViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            listener = (ChangeItemListener) context;
            itemName = itemView.findViewById(R.id.item_name);
            itemQte = itemView.findViewById(R.id.item_qte);
            itemColor = itemView.findViewById(R.id.item_color);
            itemSize = itemView.findViewById(R.id.item_size);
            createDate = itemView.findViewById(R.id.create_date);
            deleteBtn = itemView.findViewById(R.id.delete_item_btn);
            updateBtn = itemView.findViewById(R.id.update_item_btn);

            deleteBtn.setOnClickListener(this);
            updateBtn.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            DatabaseHandler db = new DatabaseHandler(context);
            List<ItemModel> items = new ArrayList<>();
            items = db.getAllItem();
            if( v.getId() == R.id.delete_item_btn)
            {
                int pos = getAdapterPosition();
                ItemModel item = items.get(pos);
                db.deleteItem(item);
                listener.dataChanged("delete",pos);

            }
            else if(v.getId() == R.id.update_item_btn)
            {
                int pos = getAdapterPosition();
                listener.dataChanged("update",pos);

              //  db.upgradeItem(item);



            }


        }


    }


}
