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
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import net.cachapa.expandablelayout.ExpandableLayout;

public class SelectableViewHolder extends RecyclerView.ViewHolder {

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

    public void bindItem() {

        GroupAdapter adapter = new GroupAdapter();
        rvSubMenu.setAdapter(adapter);
        rvSubMenu.setLayoutManager(new LinearLayoutManager(ctx));
        rvSubMenu.addItemDecoration(new DividerItemDecoration(ctx, LinearLayoutManager.VERTICAL));

        if (mItem.getMenuChildren().size() > 0) {
            btnExpand.setVisibility(View.VISIBLE);
            expandedMenu.setVisibility(View.VISIBLE);

            for (String data : mItem.getMenuChildren()) {
                adapter.add(new SubMenuItem(data));
            }
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
            } else {
                expandedMenu.setVisibility(View.VISIBLE);
                Log.d("tag", "expandable expand");
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

    private class SubMenuItem extends Item {

        String title;
        TextView tvSubtitle;

        public SubMenuItem(String title) {
            this.title = title;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            tvSubtitle = viewHolder.itemView.findViewById(R.id.tvSubmenuTitle);
            tvSubtitle.setText(title);
        }

        @Override
        public int getLayout() {
            return R.layout.layout_simple_text;
        }
    }
}
