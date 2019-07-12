package com.example.crmsbeta;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.crmsbeta.adapter.MenuNavAdapter;
import com.example.crmsbeta.fragment.CaseFragment;
import com.example.crmsbeta.fragment.CaseParticularsFragment;
import com.example.crmsbeta.fragment.EmptyFragment;
import com.example.crmsbeta.model.CaseFragmentModel;
import com.example.crmsbeta.model.MenuModel;
import com.example.crmsbeta.model.SelectableMenuModel;
import com.example.crmsbeta.adapter.SelectableViewHolder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaseHandlingActivity extends AppCompatActivity implements SelectableViewHolder.OnItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private CaseHandlingAdapter caseHandlingAdapter;
    private ViewPager viewPager;
    private ImageView btnArrowLeft, btnArrowRight;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private ActionBarDrawerToggle toggle;
    private RecyclerView rvMenu;
    private MenuNavAdapter rvMenuAdapter;

    private static final int TYPE_MENU_NORMAL = 0;
    private static final int TYPE_MENU_EXPANDABLE = 1;
    private List<MenuNavItem> menuNavItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_handling);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        btnArrowLeft = findViewById(R.id.btnArrowLeft);
        btnArrowRight = findViewById(R.id.btnArrowRight);
        rvMenu = findViewById(R.id.rvMenu);

        setSupportActionBar(toolbar);

        setupNavRv();

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Case Handling");
        setupTabLayout();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.case_handling_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupTabLayout() {
        List<CaseFragmentModel> listFragment = new ArrayList<>();

        listFragment.add(
                new CaseFragmentModel("Case Particulars", CaseParticularsFragment.newInstance())
        );

        listFragment.add(new CaseFragmentModel("View Attachments/Comments", CaseFragment.newInstance()));

        listFragment.add(
                new CaseFragmentModel("Letter Preparations", EmptyFragment.newInstance())
        );
        listFragment.add(
                new CaseFragmentModel("Case History", EmptyFragment.newInstance())
        );

        listFragment.add(
                new CaseFragmentModel("Flow History", EmptyFragment.newInstance())
        );
        listFragment.add(
                new CaseFragmentModel("Request EU1823 Detail", EmptyFragment.newInstance())
        );

        btnArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPos = viewPager.getCurrentItem();
                currentPos--;
                viewPager.setCurrentItem(currentPos);
            }
        });

        btnArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPos = viewPager.getCurrentItem();
                currentPos++;
                viewPager.setCurrentItem(currentPos);
            }
        });

        caseHandlingAdapter = new CaseHandlingAdapter(this, getSupportFragmentManager(), listFragment);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(caseHandlingAdapter);

        // custom view tablayout,
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(caseHandlingAdapter.getTabView(i));
        }

        caseHandlingAdapter.setOnSelectView(tabLayout, 0, true);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                caseHandlingAdapter.setOnSelectView(tabLayout, pos, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                caseHandlingAdapter.setOnSelectView(tabLayout, pos, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupNavRv() {
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        List<String> emptyList = Collections.emptyList();

        List<MenuModel> menuModels = new ArrayList<>();

        List<String> menuChildrenCaseSearch = new ArrayList<>();
        menuChildrenCaseSearch.add("Simple Search");
        menuChildrenCaseSearch.add("Advanced Search");
        menuChildrenCaseSearch.add("Search Results");

        List<String> menuChildrenOfficeAdmin = new ArrayList<>();
        menuChildrenOfficeAdmin.add("Maintain Office Code");
        menuChildrenOfficeAdmin.add("Maintain Office Code");

        // fixme : ganti icon dengan yang sesuai
        menuModels.add(new SelectableMenuModel(new MenuModel("CRMS", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false));
        menuModels.add(new SelectableMenuModel(new MenuModel("Case Handling", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_case_handling), false));
        menuModels.add(new SelectableMenuModel(new MenuModel("Case Search", menuChildrenCaseSearch,
                R.drawable.icon_statistic_input, R.drawable.icon_case_search), false));
        menuModels.add(new SelectableMenuModel(new MenuModel("Statistic Input", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false));
        menuModels.add(new SelectableMenuModel(new MenuModel("Office Administration", menuChildrenOfficeAdmin,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false));

//        menuModels.add(new SelectableMenuModel(new MenuModel("User Management", emptyList,
//                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false));
//        menuModels.add(new SelectableMenuModel(new MenuModel("Business Parameter \n" +
//                "Maintenance", emptyList,
//                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false));
//        menuModels.add(new SelectableMenuModel(new MenuModel("System \n" +
//                "Administration", emptyList,
//                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false));
//        menuModels.add(new SelectableMenuModel(new MenuModel("Handling Guidelines", emptyList,
//                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false));
//        menuModels.add(new SelectableMenuModel(new MenuModel("Report Data \n" +
//                "Generation", emptyList,
//                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false));
//        menuModels.add(new SelectableMenuModel(new MenuModel("Feedback Corner", emptyList,
//                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false));

        rvMenuAdapter = new MenuNavAdapter(menuModels, this);
        rvMenu.setAdapter(rvMenuAdapter);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemSelected(SelectableMenuModel item) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class CaseHandlingAdapter extends FragmentStatePagerAdapter {

        private List<CaseFragmentModel> listFragments;
        private Context ctx;

        public CaseHandlingAdapter(Context ctx, FragmentManager fm, List<CaseFragmentModel> listFragments) {
            super(fm);
            this.ctx = ctx;
            this.listFragments = listFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return listFragments.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return listFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return listFragments.get(position).getTitle();
        }

        // handle custom view each tab
        public View getTabView(int pos) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.layout_each_tab, null);
            TextView title = view.findViewById(R.id.tabTitle);

            title.setText(listFragments.get(pos).getTitle());
            return view;
        }

        public void setOnSelectView(TabLayout tabLayout, int pos, boolean isSelected) {
            TabLayout.Tab tab = tabLayout.getTabAt(pos);
            View viewSelected = tab.getCustomView();
            View container = viewSelected.findViewById(R.id.containerTab);
            View line = viewSelected.findViewById(R.id.line);

            if (isSelected) {
                container.setBackgroundResource(R.drawable.bg_each_tab_selected);
                line.setVisibility(View.VISIBLE);
            } else {
                line.setVisibility(View.GONE);
                container.setBackgroundResource(R.drawable.bg_each_tab);
            }

        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class MenuNavItem {
        boolean isExpand;
        String title;
        int selectedIcon;
        int normalIcon;

        public MenuNavItem(boolean isExpand, String title, int selectedIcon, int normalIcon) {
            this.isExpand = isExpand;
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.normalIcon = normalIcon;
        }

        public boolean isExpand() {
            return isExpand;
        }

        public void setExpand(boolean expand) {
            isExpand = expand;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSelectedIcon() {
            return selectedIcon;
        }

        public void setSelectedIcon(int selectedIcon) {
            this.selectedIcon = selectedIcon;
        }

        public int getNormalIcon() {
            return normalIcon;
        }

        public void setNormalIcon(int normalIcon) {
            this.normalIcon = normalIcon;
        }
    }
}
