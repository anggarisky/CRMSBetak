package com.example.crmsbeta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.example.crmsbeta.adapter.SelectableSubmenuViewHolder;
import com.example.crmsbeta.adapter.SubmenuNavAdapter;
import com.example.crmsbeta.fragment.CaseFragment;
import com.example.crmsbeta.fragment.CaseParticularsFragment;
import com.example.crmsbeta.fragment.EmptyFragment;
import com.example.crmsbeta.model.CaseFragmentModel;
import com.example.crmsbeta.model.MenuModel;
import com.example.crmsbeta.model.SelectableMenuModel;
import com.example.crmsbeta.adapter.SelectableViewHolder;
import com.example.crmsbeta.model.SelectableSubMenuModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaseHandlingActivity extends AppCompatActivity implements SelectableViewHolder.OnItemSelectedListener, SelectableSubmenuViewHolder.OnSubmenuItemSelectedListener {

    private static final int MENU_CRMS = 1;
    private static final int MENU_CASE_HANDLING = 2;
    private static final int MENU_CASE_SEARCH = 3;
    private static final int MENU_STATISTIC_INPUT = 4;
    private static final int MENU_OFFICE_ADMIN = 5;
    private static final int MENU_USER_MANAGEMENT = 6;
    private static final int MENU_BUSSINESS_PARAMETER = 7;
    private static final int MENU_SYSTEM_ADMIN = 8;
    private static final int MENU_HANDLING_GUIDE = 9;
    private static final int MENU_REPORT_DATA = 10;
    private static final int MENU_FEEDBACK_CORNER = 11;

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
    private int selectedMenu;

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
        menuChildrenOfficeAdmin.add("Maintain Estate");
        menuChildrenOfficeAdmin.add("Maintain Block");

        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_CRMS, "CRMS", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false, false));
        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_CASE_HANDLING, "Case Handling", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_case_handling), false, false));

        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_CASE_SEARCH, "Case Search", menuChildrenCaseSearch,
                R.drawable.icon_statistic_input, R.drawable.icon_case_search), false, false));

        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_STATISTIC_INPUT, "Statistic Input", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false, false));
        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_OFFICE_ADMIN, "Office Administration", menuChildrenOfficeAdmin,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false, false));

        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_USER_MANAGEMENT, "User Management", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false, false));

        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_BUSSINESS_PARAMETER, "Business Parameter \n" +
                "Maintenance", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false, false));

        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_SYSTEM_ADMIN, "System \n" +
                "Administration", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false, false));
        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_HANDLING_GUIDE, "Handling Guidelines", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false, false));
        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_REPORT_DATA, "Report Data \n" +
                "Generation", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false, false));
        menuModels.add(new SelectableMenuModel(new MenuModel(MENU_FEEDBACK_CORNER, "Feedback Corner", emptyList,
                R.drawable.icon_statistic_input, R.drawable.icon_statistic_input), false, false));

        rvMenuAdapter = new MenuNavAdapter(menuModels, this);
        rvMenu.setAdapter(rvMenuAdapter);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemSelected(SelectableMenuModel item, int pos) {
        Log.d("tag", "menu " + item.getMenuTitle() + "id " + item.getId());
        selectedMenu = item.getId();
        switch (item.getId()) {
            case MENU_CRMS:
                break;
            case MENU_CASE_HANDLING:
                startActivity(new Intent(this, EmptyActivity.class));
                break;
        }
    }

    @Override
    public void onSubMenuItemSelected(SelectableSubMenuModel item) {
        // todo: gak kepake nih

    }

    @Override
    public void onSubMenuSelected(SelectableSubMenuModel item) {
        Log.d("tag", "menu submenu selected " + item.getTitle() + " id " + item.getId());

        if (selectedMenu == MENU_CASE_SEARCH) {
            switch (item.getId()) {
                case 0:
                    Log.d("tag", "sub menu simple search");
                    break;
                case 1:
                    Log.d("tag", "sub menu adv search");
                    break;
                case 2:
                    Log.d("tag", "sub menu search result");
                    break;
            }
        } else if (selectedMenu == MENU_OFFICE_ADMIN) {
            switch (item.getId()) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
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
}
