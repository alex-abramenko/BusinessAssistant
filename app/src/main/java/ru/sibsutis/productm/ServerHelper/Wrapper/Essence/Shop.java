package ru.sibsutis.productm.ServerHelper.Wrapper.Essence;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Shop {
    public int ID_Shop;
    public String Tittle;
    public int ID_Creator;
    public String TypeShop;
    public String Town;
    public String Address;
    public int[] isCategory = new int[16];
}
