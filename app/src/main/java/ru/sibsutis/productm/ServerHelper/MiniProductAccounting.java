package ru.sibsutis.productm.ServerHelper;



import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ru.sibsutis.productm.ServerHelper.Wrapper.Command.*;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.*;

public class MiniProductAccounting {
    private static int currentUsersId   =   -1;
    public static int currentShopId     =   -1;

    public boolean checkServerStatus() {
        String ans = new ServerAccess().request("CHECK STATUS");
        if(ans == null) return false;
        return true;
    }

    public boolean registration(String login, String password, String phone, String name, String typeAcc) throws IOException {
        Command command = new Command();
        command.nameCommand = Command.COMM_REG;

        CommRegistration params = new CommRegistration();
        params.Login = login;
        params.Password = password;
        params.Phone = phone;
        params.Name = name;
        params.TypeAccount = typeAcc;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return false;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.OK_REG)) {
            User user = getUserInfo(login);
            currentUsersId = user.id;
            return true;
        } else
            return false;
    }

    public boolean authorization(String login, String password) throws IOException {
        Command command = new Command();
        command.nameCommand = Command.COMM_AUTH;

        CommAuthorization params = new CommAuthorization();
        params.Login = login;
        params.Password = password;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return false;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.OK_AUTH)) {
            User user = getUserInfo(login);
            currentUsersId = user.id;
            Shop shop = getShop();
            if (shop != null)
                currentShopId = shop.ID_Shop;
            return true;
        } else
            return false;
    }

    public User getUserInfo(String login) throws IOException {
        Command command = new Command();
        command.nameCommand = Command.COMM_GETUSERINFO;

        CommGetUserInfo params = new CommGetUserInfo();
        params.login = login;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return null;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.ERROR_GETUSERINFO))
            return null;

        reader = new StringReader(answer.jsonParams);
        User u = new ObjectMapper().readValue(reader, User.class);

        return u;
    }

    public User getUserInfo(int id) throws IOException {
        Command command = new Command();
        command.nameCommand = Command.COMM_GETUSERINFO;

        CommGetUserInfo params = new CommGetUserInfo();
        params.id = id;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return null;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.ERROR_GETUSERINFO))
            return null;

        reader = new StringReader(answer.jsonParams);
        User u = new ObjectMapper().readValue(reader, User.class);

        return u;
    }

    public ArrayList<Category> getAllCategories() throws IOException {
        Command command = new Command();
        command.nameCommand = Command.COMM_GETALLCAT;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return null;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.ERROR_GETALLCAT))
            return null;

        reader = new StringReader(answer.jsonParams);
        CommGetCategories jsonCategories = new ObjectMapper().readValue(reader, CommGetCategories.class);

        ArrayList<Category> list = new ArrayList<>();
        for (int i = 0; i < jsonCategories.jsonCategories.length; i++) {
            reader = new StringReader(jsonCategories.jsonCategories[i]);
            list.add(new ObjectMapper().readValue(reader, Category.class));
        }

        return list;
    }

    public boolean addShop(String tittle, String typeShop, String town, String address, int[] cat) throws IOException {
        if(currentUsersId == -1)
            return false;

        Command command = new Command();
        command.nameCommand = Command.COMM_ADDSHOP;

        CommAddShop params = new CommAddShop();
        params.Tittle = tittle;
        params.ID_Creator = currentUsersId;
        params.TypeShop = typeShop;
        params.Town = town;
        params.Address = address;
        params.isCategory = cat;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return false;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.OK_ADDSHOP)) {
            Shop shop = getShop();
            currentShopId = shop.ID_Shop;
            return true;
        } else
            return false;
    }

    public Shop getShop() throws IOException {
        if(currentUsersId == -1)
            return null;

        Command command = new Command();
        command.nameCommand = Command.COMM_GETSHOP;

        CommGetShop params = new CommGetShop();
        params.ID_Creator = currentUsersId;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return null;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.ERROR_GETSHOP))
            return null;

        reader = new StringReader(answer.jsonParams);
        Shop s = new ObjectMapper().readValue(reader, Shop.class);

        return s;
    }

    public boolean addProduct(String tittle, String detail, float price) throws IOException {
        if(currentUsersId == -1 || currentShopId == -1)
            return false;

        Command command = new Command();
        command.nameCommand = Command.COMM_ADDPRODUCT;

        CommAddProduct params = new CommAddProduct();
        params.Tittle = tittle;
        params.ID_Shop = currentShopId;
        params.Detail = detail;
        params.Price = price;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return false;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        return answer.nameCommand.equals(Command.OK_ADDPRODUCT);
    }

    public boolean addPurchP(int id_product, int Quantity, float Price, long Date) throws IOException {
        if(currentUsersId == -1 || currentShopId == -1)
            return false;

        Command command = new Command();
        command.nameCommand = Command.COMM_ADDPURCHP;

        CommAddPurchP params = new CommAddPurchP();
        params.ID_Product = id_product;
        params.ID_Shop = currentShopId;
        params.Quantity = Quantity;
        params.Price = Price;
        params.Date = Date;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return false;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        return answer.nameCommand.equals(Command.OK_ADDPURCHP);
    }

    public ArrayList<Product> getAllProducts() throws IOException {
        if(currentUsersId == -1 || currentShopId == -1)
            return null;

        Command command = new Command();
        command.nameCommand = Command.COMM_GETALLPRODUCT;

        CommGetAllProducts params = new CommGetAllProducts();
        params.ID_Shop = currentShopId;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return null;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.ERROR_GETALLPRODUCT))
            return null;

        reader = new StringReader(answer.jsonParams);
        CommGetAllProducts jsonProducts = new ObjectMapper().readValue(reader, CommGetAllProducts.class);

        ArrayList<Product> list = new ArrayList<>();
        for (int i = 0; i < jsonProducts.jsonProducts.length; i++) {
            reader = new StringReader(jsonProducts.jsonProducts[i]);
            list.add(new ObjectMapper().readValue(reader, Product.class));
        }

        return list;
    }

    public ArrayList<PurchProducts> getPurchPr(long startDate, long endDate) throws IOException {
        if(currentUsersId == -1 || currentShopId == -1)
            return null;

        Command command = new Command();
        command.nameCommand = Command.COMM_GETPURCHPR;

        CommGetPurchPr params = new CommGetPurchPr();
        params.ID_Shop = currentShopId;
        params.startDate = startDate;
        params.endDate = endDate;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return null;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.ERROR_GETPURCHPR))
            return null;

        reader = new StringReader(answer.jsonParams);
        CommGetPurchPr jsonProducts = new ObjectMapper().readValue(reader, CommGetPurchPr.class);

        ArrayList<PurchProducts> list = new ArrayList<>();
        for (int i = 0; i < jsonProducts.jsonPurchPr.length; i++) {
            reader = new StringReader(jsonProducts.jsonPurchPr[i]);
            list.add(new ObjectMapper().readValue(reader, PurchProducts.class));
        }

        return list;
    }

    public boolean addCost(String Title, float Price, long Date) throws IOException {
        if(currentUsersId == -1 || currentShopId == -1)
            return false;

        Command command = new Command();
        command.nameCommand = Command.COMM_ADDCOST;

        CommAddCost params = new CommAddCost();
        params.Tittle = Title;
        params.ID_Shop = currentShopId;
        params.Price = Price;
        params.Date = Date;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return false;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        return answer.nameCommand.equals(Command.OK_ADDCOST);
    }

    public ArrayList<Cost> getCosts(long startDate, long endDate) throws IOException {
        if(currentUsersId == -1 || currentShopId == -1)
            return null;

        Command command = new Command();
        command.nameCommand = Command.COMM_GETCOST;

        CommGetCosts params = new CommGetCosts();
        params.ID_Shop = currentShopId;
        params.startDate = startDate;
        params.endDate = endDate;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return null;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.ERROR_GETCOST))
            return null;

        reader = new StringReader(answer.jsonParams);
        CommGetCosts jsonProducts = new ObjectMapper().readValue(reader, CommGetCosts.class);

        ArrayList<Cost> list = new ArrayList<>();
        for (int i = 0; i < jsonProducts.jsonCosts.length; i++) {
            reader = new StringReader(jsonProducts.jsonCosts[i]);
            list.add(new ObjectMapper().readValue(reader, Cost.class));
        }

        return list;
    }

    public boolean addTrade(int ID_Product, int Quantity, float Price, long Date) throws IOException {
        if(currentUsersId == -1 || currentShopId == -1)
            return false;

        Command command = new Command();
        command.nameCommand = Command.COMM_ADDTRADE;

        CommAddTrade params = new CommAddTrade();
        params.ID_Product = ID_Product;
        params.ID_Shop = currentShopId;
        params.Quantity = Quantity;
        params.Price = Price;
        params.Date = Date;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return false;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        return answer.nameCommand.equals(Command.OK_ADDTRADE);
    }

    public ArrayList<Trade> getTrades(long startDate, long endDate) throws IOException {
        if(currentUsersId == -1 || currentShopId == -1)
            return null;

        Command command = new Command();
        command.nameCommand = Command.COMM_GETTRADE;

        CommGetTrades params = new CommGetTrades();
        params.ID_Shop = currentShopId;
        params.startDate = startDate;
        params.endDate = endDate;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return null;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.ERROR_GETTRADE))
            return null;

        reader = new StringReader(answer.jsonParams);
        CommGetTrades jsonProducts = new ObjectMapper().readValue(reader, CommGetTrades.class);

        ArrayList<Trade> list = new ArrayList<>();
        for (int i = 0; i < jsonProducts.jsonTrades.length; i++) {
            reader = new StringReader(jsonProducts.jsonTrades[i]);
            list.add(new ObjectMapper().readValue(reader, Trade.class));
        }

        return list;
    }

    public Report getReport(long startDate, long endDate) throws IOException {
        if(currentUsersId == -1 || currentShopId == -1)
            return null;

        Command command = new Command();
        command.nameCommand = Command.COMM_GETREPORT;

        CommGetReport params = new CommGetReport();
        params.ID_Shop = currentShopId;
        params.startDate = startDate;
        params.endDate = endDate;

        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, params);
        command.jsonParams = writer.toString();

        writer = new StringWriter();
        new ObjectMapper().writeValue(writer, command);

        String strAns = new ServerAccess().request(writer.toString());

        if(strAns == null)
            return null;

        StringReader reader = new StringReader(strAns);
        Command answer = new ObjectMapper().readValue(reader, Command.class);

        if(answer.nameCommand.equals(Command.ERROR_GETREPORT))
            return null;

        reader = new StringReader(answer.jsonParams);
//        CommGetReport commGetReport = new ObjectMapper().readValue(reader, CommGetReport.class);
//
//        reader = new StringReader(commGetReport.jsonReport);
        Report report = new ObjectMapper().readValue(reader, Report.class);

        return report;
    }

    public static Calendar parseStrToCal(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        Calendar cal = new GregorianCalendar();
        try {
            cal.setTime(dateFormat.parse(str));
        }
        catch (ParseException e) { e.printStackTrace(); }

        return cal;
    }

    public static String parseLongTimeToStrDate(long d) {
        return new SimpleDateFormat("dd.MM.yyyy, HH:mm").format(d);
    }
}
