package ru.sibsutis.productm.ServerHelper.Wrapper.Command;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CommAddPurchP {
    public int ID_Shop;
    public int ID_Product;
    public int Quantity;
    public float Price;
    public long Date;

    public CommAddPurchP() {}
}
