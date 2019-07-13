package com.example.crmsbeta.model;

public class SelectableSubMenuModel extends SubMenuModel {

    private Boolean isSelected;

    public SelectableSubMenuModel(SubMenuModel subMenuModel, boolean isSelected) {
        super(subMenuModel.getId(), subMenuModel.getTitle());
        this.isSelected = isSelected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
