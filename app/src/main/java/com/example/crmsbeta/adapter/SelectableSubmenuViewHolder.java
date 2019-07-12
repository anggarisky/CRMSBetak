package com.example.crmsbeta.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsbeta.R;
import com.example.crmsbeta.model.SelectableSubMenuModel;

public class SelectableSubmenuViewHolder extends RecyclerView.ViewHolder {

    SelectableSubMenuModel mItem;
    private OnSubmenuItemSelectedListener listener;
    String title;
    TextView tvSubMenuTitle;
    LinearLayout containerSubmenuItem;

    public interface OnSubmenuItemSelectedListener {
        void onSubMenuItemSelected(SelectableSubMenuModel item);
    }

    public SelectableSubmenuViewHolder(@NonNull View itemView, OnSubmenuItemSelectedListener listener) {
        super(itemView);
        this.listener = listener;

        tvSubMenuTitle = itemView.findViewById(R.id.tvSubmenuTitle);
        containerSubmenuItem = itemView.findViewById(R.id.containerSubMenuItem);
    }

    public void bindItem() {
        containerSubmenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItem.getSelected()) {
                    setChecked(false);
                } else {
                    setChecked(true);
                }
                listener.onSubMenuItemSelected(mItem);
            }
        });

        tvSubMenuTitle.setText(mItem.getTitle());
    }

    public void setChecked(boolean isChecked) {
        if (isChecked) {
            containerSubmenuItem.setBackgroundColor(Color.parseColor("#b8c8d5"));
        } else {
            containerSubmenuItem.setBackgroundColor(Color.parseColor("#748c9d"));
        }
        mItem.setSelected(isChecked);
    }
}
