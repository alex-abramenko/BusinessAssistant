package ru.sibsutis.productm.ServerHelper.Wrapper.Command;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CommRegistration {
    public String Login;
    public String Password;
    public String Phone;
    public String Name;
    public String TypeAccount;

    public CommRegistration() {}
}
