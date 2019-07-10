package com.example.crmsbeta.viewholder;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsbeta.R;
import com.example.crmsbeta.model.SelectableMenuModel;

import net.cachapa.expandablelayout.ExpandableLayout;

public class SelectableViewHolder extends RecyclerView.ViewHolder {

    public SelectableMenuModel mItem;
    private OnItemSelectedListener listener;
    private View lineSelected;
    private ImageView btnExpand;
    private ExpandableLayout expandedMenu;
    private LinearLayout containerMenu;
    private TextView tvTitleMenu;
    private ImageView imgIcon;

    public SelectableViewHolder(@NonNull View itemView, OnItemSelectedListener listener) {
        super(itemView);
        this.listener = listener;

        containerMenu = itemView.findViewById(R.id.containerMenuItem);
        lineSelected = itemView.findViewById(R.id.lineSelected);
        imgIcon = itemView.findViewById(R.id.imgIconMenu);
        tvTitleMenu = itemView.findViewById(R.id.tvTitleMenu);
        btnExpand = itemView.findViewById(R.id.btnExpand);
        expandedMenu = itemView.findViewById(R.id.expandableMenu);

    }

    public void bindItem() {
        if (mItem.getMenuChildren().size() > 0) {
            btnExpand.setVisibility(View.VISIBLE);
            expandedMenu.setVisibility(View.VISIBLE);
        } else {
            btnExpand.setVisibility(View.GONE);
            expandedMenu.setVisibility(View.GONE);
        }

        containerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItem.isSelected()) {
                    // deselect
                    setChecked(false);
                } else {
                    // select
                    setChecked(true);
                }
                handleOpenExpand();
                listener.onItemSelected(mItem);
            }
        });


        tvTitleMenu.setText(mItem.getMenuTitle());
    }

    private void handleOpenExpand() {
        if (mItem.getMenuChildren().size() > 0) {
            if (expandedMenu.isExpanded()) {
                expandedMenu.collapse();
            } else {
                expandedMenu.expand();
            }
        }
    }


    public void setChecked(boolean isChecked) {
        Log.d("tag", "set check " + isChecked);
        if (isChecked) {
            lineSelected.setVisibility(View.VISIBLE);
            imgIcon.setImageResource(mItem.getSelectedIcon());
            containerMenu.setBackgroundColor(Color.parseColor("#4d5b65"));
        } else {
            lineSelected.setVisibility(View.GONE);
            imgIcon.setImageResource(mItem.getNormalIcon());
            containerMenu.setBackgroundColor(Color.parseColor("#637989"));
        }
        mItem.setSelected(isChecked);
    }


    public interface OnItemSelectedListener {
        void onItemSelected(SelectableMenuModel item);
    }
}
