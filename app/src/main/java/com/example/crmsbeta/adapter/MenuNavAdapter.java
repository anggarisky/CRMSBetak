package com.example.crmsbeta.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsbeta.R;
import com.example.crmsbeta.model.MenuModel;
import com.example.crmsbeta.model.SelectableMenuModel;
import com.example.crmsbeta.viewholder.SelectableViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MenuNavAdapter extends RecyclerView.Adapter implements SelectableViewHolder.OnItemSelectedListener {

    private final List<SelectableMenuModel> mValues;
    SelectableViewHolder.OnItemSelectedListener listener;

    public MenuNavAdapter(List<MenuModel> menuModels, SelectableViewHolder.OnItemSelectedListener listener) {
        this.listener = listener;

        mValues = new ArrayList<>();

        for (MenuModel data : menuModels) {
            mValues.add(new SelectableMenuModel(data, false));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_item, parent, false);
        return new SelectableViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SelectableViewHolder holder = (SelectableViewHolder) viewHolder;
        SelectableMenuModel selectableMenuModel = mValues.get(position);

        holder.mItem = selectableMenuModel;
        holder.bindItem();
        holder.setChecked(holder.mItem.isSelected());
    }

    @Override
    public void onItemSelected(SelectableMenuModel item) {
        Log.d("tag", "menu nav adapter on item selected");
        for (SelectableMenuModel selectableMenuModel : mValues) {
            Log.d("tag", "menu nav adapter find item " + item.getMenuTitle());

            if (!selectableMenuModel.equals(item) && selectableMenuModel.isSelected()) {
                // jika dia bukan item yang dicari. tetapi status is selected, set data tersebut ke false
                selectableMenuModel.setSelected(false);
            } else if (selectableMenuModel.equals(item) && item.isSelected()) {
                selectableMenuModel.setSelected(true);
            }
        }
        notifyDataSetChanged();
        listener.onItemSelected(item);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}

