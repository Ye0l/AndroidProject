package com.example.androidproject;

public class DTO {
    String id = null;
    String nick = null;
    String pwd = null;
    String name = null;
    String age = null;
    String phone = null;

    public DTO() {
    }

    public DTO(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public DTO(String id, String nick, String pwd) {
        this.id = id;
        this.nick = nick;
        this.pwd = pwd;
    }

    public DTO(String id, String pwd, String nick, String name, String age, String phone) {
        this.id = id;
        this.nick = nick;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
