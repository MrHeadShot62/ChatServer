package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by novak on 04.01.2017.
 */
public class User implements Serializable{
    private String login, fname, lname, nick, country, city, profilePhoto, lastOnline, email, friends;
    private int age, countPhoto, id, rating, online;

    public User(String login, String fname, String lname, String nick, String country, String city, int countPhoto, String profilePhoto, String lastOnline, String email, String friends, String session, int age, int id, int rating, int online) {
        this.login = login;
        this.fname = fname;
        this.lname = lname;
        this.nick = nick;
        this.country = country;
        this.city = city;
        this.countPhoto = countPhoto;
        this.profilePhoto = profilePhoto;
        this.lastOnline = lastOnline;
        this.email = email;
        this.friends = friends;
        this.age = age;
        this.id = id;
        this.rating = rating;
        this.online = online;
    }

    public String getLogin() {
        return login;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getNick() {
        return nick;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getLastOnline() {
        return lastOnline;
    }

    public String getEmail() {
        return email;
    }

    public String getFriends() {
        return friends;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public int getCountPhoto() {
        return countPhoto;
    }

    public int getRating() {
        return rating;
    }

    public int getOnline() {
        return online;
    }
}
