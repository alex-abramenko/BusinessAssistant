package ru.sibsutis.productm.ServerHelper.Wrapper.Command;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect
public class Command {
    @JsonIgnore
    public static final String COMM_REG     =   "REGISTRATION";
    @JsonIgnore
    public static final String ERROR_REG    =   "ERROR_REG";
    @JsonIgnore
    public static final String OK_REG       =   "OK_REG";

    @JsonIgnore
    public static final String COMM_AUTH    =   "AUTH";
    @JsonIgnore
    public static final String ERROR_AUTH   =   "ERROR_AUTH";
    @JsonIgnore
    public static final String OK_AUTH      =   "OK_AUTH";

    @JsonIgnore
    public static final String COMM_GETUSERINFO     =   "COMM_GETUSERINFO";
    @JsonIgnore
    public static final String OK_GETUSERINFO       =   "OK_GETUSERINFO";
    @JsonIgnore
    public static final String ERROR_GETUSERINFO    =   "ERROR_GETUSERINFO";

    @JsonIgnore
    public static final String COMM_GETALLCAT       =   "COMM_GETALLCAT";
    @JsonIgnore
    public static final String OK_GETALLCAT         =   "OK_GETALLCAT";
    @JsonIgnore
    public static final String ERROR_GETALLCAT      =   "ERROR_GETALLCAT";

    @JsonIgnore
    public static final String COMM_ADDSHOP         =   "COMM_ADDSHOP";
    @JsonIgnore
    public static final String OK_ADDSHOP           =   "OK_ADDSHOP";
    @JsonIgnore
    public static final String ERROR_ADDSHOP        =   "ERROR_ADDSHOP";

    @JsonIgnore
    public static final String COMM_GETSHOP         =   "COMM_GETSHOP";
    @JsonIgnore
    public static final String OK_GETSHOP           =   "OK_GETSHOP";
    @JsonIgnore
    public static final String ERROR_GETSHOP        =   "ERROR_GETSHOP";

    @JsonIgnore
    public static final String COMM_ADDPRODUCT      =   "COMM_ADDPRODUCT";
    @JsonIgnore
    public static final String OK_ADDPRODUCT        =   "OK_ADDPRODUCT";
    @JsonIgnore
    public static final String ERROR_ADDPRODUCT     =   "ERROR_ADDPRODUCT";

    @JsonIgnore
    public static final String COMM_ADDPURCHP       =   "COMM_ADDPURCHP";
    @JsonIgnore
    public static final String OK_ADDPURCHP         =   "OK_ADDPURCHP";
    @JsonIgnore
    public static final String ERROR_ADDPURCHP      =   "ERROR_ADDPURCHP";

    @JsonIgnore
    public static final String COMM_GETALLPRODUCT   =   "COMM_GETALLPRODUCT";
    @JsonIgnore
    public static final String OK_GETALLPRODUCT        =   "OK_GETALLPRODUCT";
    @JsonIgnore
    public static final String ERROR_GETALLPRODUCT      =   "ERROR_GETALLPRODUCT";

    @JsonIgnore
    public static final String COMM_GETPURCHPR      =   "COMM_GETPURCHPR";
    @JsonIgnore
    public static final String OK_GETPURCHPR        =   "OK_GETPURCHPR";
    @JsonIgnore
    public static final String ERROR_GETPURCHPR     =   "ERROR_GETPURCHPR";

    @JsonIgnore
    public static final String COMM_ADDCOST         =   "COMM_ADDCOST";
    @JsonIgnore
    public static final String OK_ADDCOST           =   "OK_ADDCOST";
    @JsonIgnore
    public static final String ERROR_ADDCOST        =   "ERROR_ADDCOST";

    @JsonIgnore
    public static final String COMM_GETCOST         =   "COMM_GETCOST";
    @JsonIgnore
    public static final String OK_GETCOST           =   "OK_GETCOST";
    @JsonIgnore
    public static final String ERROR_GETCOST        =   "ERROR_GETCOST";

    @JsonIgnore
    public static final String COMM_ADDTRADE        =   "COMM_ADDTRADE";
    @JsonIgnore
    public static final String OK_ADDTRADE          =   "OK_ADDTRADE";
    @JsonIgnore
    public static final String ERROR_ADDTRADE       =   "ERROR_ADDTRADE";

    @JsonIgnore
    public static final String COMM_GETTRADE        =   "COMM_GETTRADE";
    @JsonIgnore
    public static final String OK_GETTRADE          =   "OK_GETTRADE";
    @JsonIgnore
    public static final String ERROR_GETTRADE       =   "ERROR_GETTRADE";

    @JsonIgnore
    public static final String COMM_GETREPORT       =   "COMM_GETREPORT";
    @JsonIgnore
    public static final String OK_GETREPORT         =   "OK_GETREPORT";
    @JsonIgnore
    public static final String ERROR_GETREPORT      =   "ERROR_GETREPORT";

    @JsonIgnore
    public static final String ERROR_UNKNOWN_COMMAND    =   "ERROR_UNKNOWN_COMMAND";
    @JsonIgnore
    public static final String ERROR_WRONG_FORMAT_MSG   =   "ERROR_WRONG_FORMAT_MSG";


    public String nameCommand;
    public String jsonParams;

    public Command() {}
}