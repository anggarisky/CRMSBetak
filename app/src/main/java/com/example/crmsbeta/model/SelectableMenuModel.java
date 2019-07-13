package com.example.crmsbeta.model;

public class SelectableMenuModel extends MenuModel {

    private boolean isSelected = false;
    private boolean isExpanded = false;

    public SelectableMenuModel(MenuModel menuModel, boolean isSelected, boolean isExpanded) {
        super(menuModel.getMenuTitle(), menuModel.getMenuChildren(), menuModel.getSelectedIcon(), menuModel.getNormalIcon());
        this.isSelected = isSelected;
        this.isExpanded = isExpanded;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
