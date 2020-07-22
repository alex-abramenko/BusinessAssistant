package ru.sibsutis.productm.ServerHelper.Wrapper.Command;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CommAddCost {
    public int ID_Shop;
    public String Tittle;
    public float Price;
    public long Date;

    public CommAddCost() {}
}
