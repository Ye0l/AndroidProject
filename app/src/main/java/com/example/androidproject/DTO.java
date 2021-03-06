package com.example.androidproject;

public class DTO {
    String id = null;
    String nick = null;
    String pwd = null;
    String name = null;
    String age = null;
    String phone = null;
    String intro = "";

    public DTO() {
    }

    public DTO(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public DTO(String id, String pwd, String nick) {
        this.id = id;
        this.pwd = pwd;
        this.nick = nick;
    }

    public DTO(String id, String pwd, String nick, String name, String age, String phone) {
        this.id = id;
        this.nick = nick;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public DTO(String id, String pwd, String nick, String name, String age, String phone, String intro) {
        this.id = id;
        this.nick = nick;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "id='" + id + '\'' +
                ", nick='" + nick + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                ", intro='" + intro + '\'' +
                '}';
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
