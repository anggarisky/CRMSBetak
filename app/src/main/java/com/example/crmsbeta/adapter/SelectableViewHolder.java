package com.example.crmsbeta.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsbeta.R;
import com.example.crmsbeta.model.SelectableMenuModel;
import com.example.crmsbeta.model.SelectableSubMenuModel;
import com.example.crmsbeta.model.SubMenuModel;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

public class SelectableViewHolder extends RecyclerView.ViewHolder implements SelectableSubmenuViewHolder.OnSubmenuItemSelectedListener {

    SelectableMenuModel mItem;
    private OnItemSelectedListener listener;
    private View lineSelected;
    private ImageView btnExpand;
    private ExpandableLayout expandedMenu;
    private LinearLayout containerMenu;
    private TextView tvTitleMenu;
    private ImageView imgIcon;
    private RecyclerView rvSubMenu;
    private Context ctx;

    public SelectableViewHolder(@NonNull View itemView, OnItemSelectedListener listener) {
        super(itemView);

        this.listener = listener;
        ctx = itemView.getContext();

        containerMenu = itemView.findViewById(R.id.containerMenuItem);
        lineSelected = itemView.findViewById(R.id.lineSelected);
        imgIcon = itemView.findViewById(R.id.imgIconMenu);
        tvTitleMenu = itemView.findViewById(R.id.tvTitleMenu);
        btnExpand = itemView.findViewById(R.id.btnExpand);
        expandedMenu = itemView.findViewById(R.id.expandableMenu);
        rvSubMenu = itemView.findViewById(R.id.rvSubMenu);

    }

    @Override
    public void onSubMenuItemSelected(SelectableSubMenuModel item) {
        listener.onSubMenuSelected(item);
    }

    public void bindItem() {
        if (mItem.getMenuChildren().size() > 0) {
            btnExpand.setVisibility(View.VISIBLE);
            expandedMenu.setVisibility(View.VISIBLE);
            List<SubMenuModel> subMenuModels = new ArrayList<>();
            for (String data : mItem.getMenuChildren()) {
                subMenuModels.add(new SubMenuModel(data));
            }
            SubmenuNavAdapter submenuNavAdapter = new SubmenuNavAdapter(subMenuModels, this);
            rvSubMenu.setAdapter(submenuNavAdapter);
            rvSubMenu.setLayoutManager(new LinearLayoutManager(ctx));
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
        Log.d("tag", "expandable is expanded ? " + expandedMenu.isExpanded());

        if (mItem.getMenuChildren().size() > 0) {
            if (expandedMenu.isExpanded()) {
                Log.d("tag", "expandable collapse");
                expandedMenu.collapse();
                btnExpand.setImageResource(R.drawable.ic_keyboard_arrow_down_white_12dp);
            } else {
                expandedMenu.setVisibility(View.VISIBLE);
                Log.d("tag", "expandable expand");
                expandedMenu.expand();
                btnExpand.setImageResource(R.drawable.ic_keyboard_arrow_up_white_12dp);
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

        void onSubMenuSelected(SelectableSubMenuModel item);
    }
}
