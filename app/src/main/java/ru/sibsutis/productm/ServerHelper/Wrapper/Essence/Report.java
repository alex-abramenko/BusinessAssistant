package ru.sibsutis.productm.ServerHelper.Wrapper.Essence;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Report {
    public int[] idProducts;
    public String[] tittleProducts;
    public String[] detailProducts;
    public int[] quanProducts;
    public float[] priceProducts;
    public float costs;
    public float revenue;
    public float profit;

    public Report() {}
}
