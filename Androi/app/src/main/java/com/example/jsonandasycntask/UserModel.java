package com.example.jsonandasycntask;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String avatarDetail;
    private String usernameDetail;
    private String nameDetail;
    private String emailDetail;
    private String addressDetail;
    private String phoneDetail;
    private String companyDetail;

    public UserModel(String avatarDetail, String usernameDetail, String nameDetail, String emailDetail, String addressDetail, String phoneDetail, String companyDetail) {
        this.avatarDetail = avatarDetail;
        this.usernameDetail = usernameDetail;
        this.nameDetail = nameDetail;
        this.emailDetail = emailDetail;
        this.addressDetail = addressDetail;
        this.phoneDetail = phoneDetail;
        this.companyDetail = companyDetail;
    }

    public void setAvatarDetail(String avatarDetail) {
        this.avatarDetail = avatarDetail;
    }

    public void setUsernameDetail(String usernameDetail) {
        this.usernameDetail = usernameDetail;
    }

    public void setNameDetail(String nameDetail) {
        this.nameDetail = nameDetail;
    }

    public void setEmailDetail(String emailDetail) {
        this.emailDetail = emailDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public void setPhoneDetail(String phoneDetail) {
        this.phoneDetail = phoneDetail;
    }

    public void setCompanyDetail(String companyDetail) {
        this.companyDetail = companyDetail;
    }

    public String getAvatarDetail() {
        return avatarDetail;
    }

    public String getUsernameDetail() {
        return usernameDetail;
    }

    public String getNameDetail() {
        return nameDetail;
    }

    public String getEmailDetail() {
        return emailDetail;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public String getPhoneDetail() {
        return phoneDetail;
    }

    public String getCompanyDetail() {
        return companyDetail;
    }
}
