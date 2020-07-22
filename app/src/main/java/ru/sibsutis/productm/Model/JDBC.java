package ru.sibsutis.productm.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.User;
import ru.sibsutis.productm.ui.Basic.Products.Basket.Basket;


public class JDBC extends SQLiteOpenHelper {
    private static final String TAG = "myLogs";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Cache";
    private static final String TABLE_FOR_CUR_USER = "CurrentUser";
    private static final String TABLE_FOR_AUTH_DATA = "AuthData";
    private static final String TABLE_FOR_PRODUCTS = "Products";
    private static final String TABLE_FOR_BASKET = "Basket";

    public JDBC(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_FOR_CUR_USER + "(" +
                "'ID' INT, " +
                "'Login' STRING, " +
                "'Phone' STRING, " +
                "'Name' STRING, " +
                "'TypeAccount' STRING);";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_FOR_AUTH_DATA + "(" +
                "'Login' STRING, " +
                "'Password' STRING);";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_FOR_PRODUCTS + "(" +
                "'ID_Product' INT, " +
                "'Tittle' STRING, " +
                "'Detail' STRING, " +
                "'Price' DOUBLE, " +
                "'Quantity' INT);";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_FOR_BASKET + "(" +
                "'ID_operation' INTEGER PRIMARY KEY ON CONFLICT FAIL AUTOINCREMENT UNIQUE ON CONFLICT FAIL, " +
                "'ID_Product' INT, " +
                "'Tittle_Product' STRING, " +
                "'Quantity' INT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_CUR_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_AUTH_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_BASKET);
        onCreate(db);
    }



    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ID_Product", product.ID_Product);
        values.put("Tittle", product.Tittle);
        values.put("Detail", product.Detail);
        values.put("Price", product.Price);
        values.put("Quantity", product.Quantity);

        db.insert(TABLE_FOR_PRODUCTS, null, values);
        db.close();
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> products = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_FOR_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.ID_Product = Integer.parseInt(cursor.getString(0));
                product.Tittle = cursor.getString(1);
                product.Detail = cursor.getString(2);
                product.Price = Float.parseFloat(cursor.getString(3));
                product.Quantity = Integer.parseInt(cursor.getString(4));
                products.add(product);
            } while (cursor.moveToNext());
        }

        return products;
    }

    public Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOR_PRODUCTS,
                new String[] { "ID_Product", "Tittle", "Detail", "Price", "Quantity"},
                "ID_Product=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);


        if (cursor != null){
            cursor.moveToFirst();
        }

        Product product = new Product();
        product.ID_Product = Integer.parseInt(cursor.getString(0));
        product.Tittle = cursor.getString(1);
        product.Detail = cursor.getString(2);
        product.Price = Float.parseFloat(cursor.getString(3));
        product.Quantity = Integer.parseInt(cursor.getString(4));

        return product;
    }

    public Product getProduct(String detail) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOR_PRODUCTS,
                new String[] { "ID_Product", "Tittle", "Detail", "Price", "Quantity"},
                "Detail=?",
                new String[] { detail },
                null, null, null, null);


        if (cursor != null){
            cursor.moveToFirst();
        }

        Product product = new Product();
        product.ID_Product = Integer.parseInt(cursor.getString(0));
        product.Tittle = cursor.getString(1);
        product.Detail = cursor.getString(2);
        product.Price = Float.parseFloat(cursor.getString(3));
        product.Quantity = Integer.parseInt(cursor.getString(4));

        return product;
    }

    public void deleteAllProduct() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOR_PRODUCTS, null, null);
        db.close();
    }




    public void addCurrentUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOR_CUR_USER, null, null);

        ContentValues values = new ContentValues();
        values.put("ID", user.id);
        values.put("Login", user.login);
        values.put("Phone", user.phone);
        values.put("Name", user.name);
        values.put("TypeAccount", user.typeAcc);

        db.insert(TABLE_FOR_CUR_USER, null, values);
        db.close();
    }

    public User getCurrentUser() {
        String selectQuery = "SELECT * FROM " + TABLE_FOR_CUR_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            User user = new User();
            user.id = Integer.parseInt(cursor.getString(0));
            user.login = cursor.getString(1);
            user.phone = cursor.getString(2);
            user.name = cursor.getString(3);
            user.typeAcc = cursor.getString(4);

            return user;
        }
        return null;
    }




    public void addAuthData(String login, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOR_AUTH_DATA, null, null);

        ContentValues values = new ContentValues();
        values.put("Login", login);
        values.put("Password", pass);

        db.insert(TABLE_FOR_AUTH_DATA, null, values);
        db.close();
    }

    public String[] getAuthData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_FOR_AUTH_DATA;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            String[] str = new String[2];
            str[0] = cursor.getString(0);
            str[1] = cursor.getString(1);
            return str;
        }
        return null;
    }

    public void deleteAuthData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOR_AUTH_DATA, null, null);
        db.close();
    }



    public void addInBasket(int id_product, String tittle_product, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ID_Product", id_product);
        values.put("Tittle_Product", tittle_product);
        values.put("Quantity", quantity);

        db.insert(TABLE_FOR_BASKET, null, values);
        db.close();
    }

    public ArrayList<Basket> getAllBasket() {
        ArrayList<Basket> baskets = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_FOR_BASKET;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Basket product = new Basket();
                product.ID = Integer.parseInt(cursor.getString(0));
                product.ID_Product= Integer.parseInt(cursor.getString(1));
                product.Tittle_Product = cursor.getString(2);
                product.Quantity = Integer.parseInt(cursor.getString(3));
                baskets.add(product);
            } while (cursor.moveToNext());
        }

        return baskets;
    }

    public void deleteAllBasket() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOR_BASKET, null, null);
        db.execSQL(String.format("update sqlite_sequence set seq=0 WHERE Name='%s'", TABLE_FOR_BASKET));
        db.close();
    }

//    public int updateContact(Contact contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, contact.getName());
//        values.put(KEY_PH_NO, contact.getPhoneNumber());
//
//        return db.update(TABLE_FOR_VAC, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(contact.getID()) });
//    }

//    public void deleteContact(Contact contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_FOR_VAC, KEY_ID + " = ?", new String[] { String.valueOf(contact.getID()) });
//        db.close();
//    }
}
