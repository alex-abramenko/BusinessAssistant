package ru.sibsutis.productm.ServerHelper.Wrapper.Command;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CommAddShop {
    public String Tittle;
    public int ID_Creator;
    public String TypeShop;
    public String Town;
    public String Address;
    public int[] isCategory = new int[16];

    public CommAddShop() {

    }
}
