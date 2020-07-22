package ru.sibsutis.productm.ServerHelper.Wrapper.Command;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CommGetAllProducts {
    public int ID_Shop;
    public String[] jsonProducts;

    public CommGetAllProducts() {}
}
