package ru.sibsutis.productm.ServerHelper.Wrapper.Essence;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Product {
    public int ID_Product;
    public String Tittle;
    public String Detail;
    public float Price;
    public int Quantity;

    public Product() { }
}
