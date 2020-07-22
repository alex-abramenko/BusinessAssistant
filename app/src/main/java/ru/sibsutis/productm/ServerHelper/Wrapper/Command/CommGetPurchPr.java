package ru.sibsutis.productm.ServerHelper.Wrapper.Command;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CommGetPurchPr {
    public long startDate;
    public long endDate;
    public int ID_Shop;
    public String[] jsonPurchPr;

    public CommGetPurchPr(){}
}
