package com.demo.model;

public class Compani {
    private String companyName;
    private String companyAddress;
    private String companyInstagramUrl;
    private String companyFacebookUrl;
    private String companyTwitterUrl;
    private String companyImageUrl;

    public Compani(String companyName, String companyAddress, String companyInstagramUrl, String companyFacebookUrl, String companyTwitterUrl,String companyImageUrl) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyInstagramUrl = companyInstagramUrl;
        this.companyFacebookUrl = companyFacebookUrl;
        this.companyTwitterUrl = companyTwitterUrl;
        this.companyImageUrl = companyImageUrl;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyInstagramUrl() {
        return companyInstagramUrl;
    }

    public void setCompanyInstagramUrl(String companyInstagramUrl) {
        this.companyInstagramUrl = companyInstagramUrl;
    }

    public String getCompanyFacebookUrl() {
        return companyFacebookUrl;
    }

    public void setCompanyFacebookUrl(String companyFacebookUrl) {
        this.companyFacebookUrl = companyFacebookUrl;
    }

    public String getCompanyTwitterUrl() {
        return companyTwitterUrl;
    }
    public void setCompanyTwitterUrl(String companyTwitterUrl) {
        this.companyTwitterUrl = companyTwitterUrl;
    }

    public String getCompanyImageUrl() {
        return companyImageUrl;
    }
}
