package com.example.crmsbeta.model;

import java.util.List;

public class MenuModel {
    private String menuTitle;
    private int selectedIcon;
    private int normalIcon;
    private List<String> menuChildren;

    public MenuModel(String menuTitle, List<String> menuChildren, int selectedIcon, int normalIcon) {
        this.menuTitle = menuTitle;
        this.menuChildren = menuChildren;
        this.selectedIcon = selectedIcon;
        this.normalIcon = normalIcon;
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

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public List<String> getMenuChildren() {
        return menuChildren;
    }

    public void setMenuChildren(List<String> menuChildren) {
        this.menuChildren = menuChildren;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        MenuModel itemCompare = (MenuModel) obj;
        if (itemCompare.getMenuTitle().equals(this.getMenuTitle()))
            return true;

        return false;
    }
}
