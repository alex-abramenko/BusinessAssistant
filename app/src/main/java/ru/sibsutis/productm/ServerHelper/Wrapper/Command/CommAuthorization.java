package ru.sibsutis.productm.ServerHelper.Wrapper.Command;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CommAuthorization {
    public String Login;
    public String Password;

    public CommAuthorization() {}
}
