package com.ktspace.miniHomeIoT.domain;

public class Resource {
    private ResourceGroup resourceGroup;
    private String korName;
    private Model model;

    private void resetKorName() {
        this.korName = model.getKorName() + "." + resourceGroup.getKorName();
    }

    //region getter and setter
    public ResourceGroup getResourceGroup() {
        return resourceGroup;
    }

    public void setResourceGroup(ResourceGroup resourceGroup) {
        this.resourceGroup = resourceGroup;
        resetKorName();
    }

    public String getKorName() {
        return korName;
    }


    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        resetKorName();
    }
    //endregion
}