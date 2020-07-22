package ru.sibsutis.productm.ServerHelper.Wrapper.Essence;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Cost {
    public int ID_Cost;
    public String Tittle;
    public float Price;
    public long Date;
}
