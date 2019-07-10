package com.example.crmsbeta.model;

public class SelectableMenuModel extends MenuModel {

    private boolean isSelected = false;

    public SelectableMenuModel(MenuModel menuModel, boolean isSelected) {
        super(menuModel.getMenuTitle(), menuModel.getMenuChildren(), menuModel.getSelectedIcon(), menuModel.getNormalIcon());
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
