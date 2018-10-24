package com.example.manav.log;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FormModal  {

    private String Name,
            Address,
            Phone,
            Email,
            Dob,
            Age,
            Language,
            Gender,
            passportnum,
            doi;

    public FormModal(String name, String address, String phone, String email, String dob, String age, String language, String gender, String passportnum, String doi) {
        Name = name;
        Address = address;
        Phone = phone;
        Email = email;
        Dob = dob;
        Age = age;
        Language = language;
        Gender = gender;
        this.passportnum = passportnum;
        this.doi = doi;
    }

    public String getPassportnum() {
        return passportnum;
    }

    public void setPassportnum(String passportnum) {
        this.passportnum = passportnum;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }




}
