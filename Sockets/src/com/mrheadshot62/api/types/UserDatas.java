package com.mrheadshot62.api.types;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by novak on 04.01.2017.
 */
public class UserDatas implements Serializable{
    private String fname, lname, friends, login, nickname, photos, profilePhotos, email;
    private int id, permissionLvl, age, contry, city, rating, isUpdated, counPhoto, balance;
    private byte online;
    private Timestamp lastoOnline, registration, lastAuth;


    public UserDatas(int id, String login, String fname, String lname, String friends, String nickname, String photos, String profilePhotos, String email, int permissionLvl, int age, int contry, int city, int rating, int isUpdated, int counPhoto, int balance, byte online, Timestamp lastoOnline, Timestamp registration, Timestamp lastAuth) {
        this.fname = fname;
        this.lname = lname;
        this.friends = friends;
        this.login = login;
        this.nickname = nickname;
        this.photos = photos;
        this.profilePhotos = profilePhotos;
        this.email = email;
        this.id = id;
        this.permissionLvl = permissionLvl;
        this.age = age;
        this.contry = contry;
        this.city = city;
        this.rating = rating;
        this.isUpdated = isUpdated;
        this.counPhoto = counPhoto;
        this.balance = balance;
        this.online = online;
        this.lastoOnline = lastoOnline;
        this.registration = registration;
        this.lastAuth = lastAuth;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getFriends() {
        return friends;
    }

    public String getLogin() {
        return login;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhotos() {
        return photos;
    }

    public String getProfilePhotos() {
        return profilePhotos;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public int getPermissionLvl() {
        return permissionLvl;
    }

    public int getAge() {
        return age;
    }

    public int getContry() {
        return contry;
    }

    public int getCity() {
        return city;
    }

    public int getRating() {
        return rating;
    }

    public int getIsUpdated() {
        return isUpdated;
    }

    public int getCounPhoto() {
        return counPhoto;
    }

    public int getBalance() {
        return balance;
    }

    public byte getOnline() {
        return online;
    }

    public Timestamp getLastoOnline() {
        return lastoOnline;
    }

    public Timestamp getRegistration() {
        return registration;
    }

    public Timestamp getLastAuth() {
        return lastAuth;
    }


}
