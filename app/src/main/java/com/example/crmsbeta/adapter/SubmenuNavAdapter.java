package com.example.crmsbeta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsbeta.R;
import com.example.crmsbeta.model.SelectableSubMenuModel;
import com.example.crmsbeta.model.SubMenuModel;

import java.util.ArrayList;
import java.util.List;

public class SubmenuNavAdapter extends RecyclerView.Adapter implements SelectableSubmenuViewHolder.OnSubmenuItemSelectedListener {

    private final List<SelectableSubMenuModel> mValues;
    SelectableSubmenuViewHolder.OnSubmenuItemSelectedListener listener;

    public SubmenuNavAdapter(List<SubMenuModel> subMenuModels, SelectableSubmenuViewHolder.OnSubmenuItemSelectedListener listener) {
        this.listener = listener;
        mValues = new ArrayList<>();
        for (SubMenuModel data : subMenuModels) {
            mValues.add(new SelectableSubMenuModel(data, false));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_simple_text, parent, false);
        return new SelectableSubmenuViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SelectableSubmenuViewHolder holder = (SelectableSubmenuViewHolder) viewHolder;
        SelectableSubMenuModel selectableSubMenuModel = mValues.get(position);

        holder.bindItem(selectableSubMenuModel);
        holder.setChecked(holder.mItem.getSelected());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void onSubMenuItemSelected(SelectableSubMenuModel item) {
        for (SelectableSubMenuModel subMenuModel : mValues) {
            if (!subMenuModel.equals(item) && item.getSelected()) {
                subMenuModel.setSelected(false);
            } else if (subMenuModel.equals(item) && item.getSelected()) {
                subMenuModel.setSelected(true);
            }
        }
        notifyDataSetChanged();
        listener.onSubMenuItemSelected(item);
    }
}
