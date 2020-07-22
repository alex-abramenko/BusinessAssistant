package ru.sibsutis.productm.ServerHelper.Wrapper.Essence;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class User {
    public int id;
    public String login;
    public String phone;
    public String name;
    public String typeAcc;

    public User() {}
}
