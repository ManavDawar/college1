package com.example.manav.log;

public class ComplainModal {
    private String Email,
    image,
    complainText,
    complainType;

    public ComplainModal(String email, String image, String complainText, String complainType) {
        Email = email;
        this.image = image;
        this.complainText = complainText;
        this.complainType = complainType;
    }

    public ComplainModal() {

        Email = "";
        this.image = "";
        this.complainText = "";
        this.complainType = "";

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComplainText() {
        return complainText;
    }

    public void setComplainText(String complainText) {
        this.complainText = complainText;
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType;
    }
}
