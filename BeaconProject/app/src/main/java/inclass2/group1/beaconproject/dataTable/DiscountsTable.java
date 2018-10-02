/*
package inclass2.group1.beaconproject.dataTable;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DiscountsTable {
    static final String TABLENAME = "discounts";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_PRODUCT_NAME = "productName";
    static final String COLUMN_DISCOUNT = "discount";
    static final String COLUMN_PRICE = "price";
    static final String COLUMN_REGION = "region";
    static final String COLUMN_IMAGE = "image";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + "(");
        sb.append(COLUMN_ID + " integer primary key autoincrement, ");
        sb.append(COLUMN_DISCOUNT + " text not null");
        sb.append(COLUMN_IMAGE + " text");
        sb.append(COLUMN_REGION + " text not null");
        sb.append(COLUMN_PRODUCT_NAME + " text not null, ");
        sb.append(COLUMN_PRICE + " text not null");

        try{
            db.execSQL(sb.toString());
        }catch (SQLException sql){
            sql.printStackTrace();
        }

    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        DiscountsTable.onCreate(db);
    }
}
*/
