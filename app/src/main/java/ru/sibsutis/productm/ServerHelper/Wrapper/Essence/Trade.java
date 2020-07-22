package ru.sibsutis.productm.ServerHelper.Wrapper.Essence;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Trade {
    public int ID_Trade;
    public int ID_Product;
    public String Tittle_Product;
    public String Detail_Product;
    public int Quantity;
    public float Price;
    public long Date;

    public Trade() {}
}
