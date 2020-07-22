package ru.sibsutis.productm.ServerHelper.Wrapper.Essence;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Category {
    public int id;
    public String tittle;

    public Category() {}
}
