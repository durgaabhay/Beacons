/*
package inclass2.group1.beaconproject.dataTable;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import inclass2.group1.beaconproject.Discounts;

public class DiscountDAO {

    private SQLiteDatabase db;

    public DiscountDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Discounts discounts){
        ContentValues values = new ContentValues();
        values.put(DiscountsTable.COLUMN_DISCOUNT,discounts.getDiscount());
        values.put(DiscountsTable.COLUMN_PRODUCT_NAME,discounts.getName());
        values.put(DiscountsTable.COLUMN_IMAGE,discounts.getImage());
        values.put(DiscountsTable.COLUMN_PRICE,discounts.getPrice());
        values.put(DiscountsTable.COLUMN_REGION,discounts.getRegion());
        return db.insert(DiscountsTable.TABLENAME,null,values);
    }

    public boolean update(Discounts discounts){
        ContentValues values = new ContentValues();
        values.put(DiscountsTable.COLUMN_ID,discounts.getId());
        values.put(DiscountsTable.COLUMN_DISCOUNT,discounts.getDiscount());
        values.put(DiscountsTable.COLUMN_PRODUCT_NAME,discounts.getName());
        values.put(DiscountsTable.COLUMN_IMAGE,discounts.getImage());
        values.put(DiscountsTable.COLUMN_PRICE,discounts.getPrice());
        values.put(DiscountsTable.COLUMN_REGION,discounts.getRegion());
        return db.update(DiscountsTable.TABLENAME,values,
                DiscountsTable.COLUMN_ID+"=?",new String[]{String.valueOf(discounts.getId())}) > 0;
    }
    public boolean delete(Discounts discounts){
        return db.delete(DiscountsTable.TABLENAME,DiscountsTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(discounts.getId())})>0;
    }

    public Discounts get(long id){
        Discounts discounts = null;
        Cursor c = db.query(true,DiscountsTable.TABLENAME,new String[]{DiscountsTable.COLUMN_ID,DiscountsTable.COLUMN_DISCOUNT,
                        DiscountsTable.COLUMN_IMAGE,DiscountsTable.COLUMN_PRICE,DiscountsTable.COLUMN_PRODUCT_NAME,DiscountsTable.COLUMN_REGION},
                DiscountsTable.COLUMN_ID + "=",new String[]{String.valueOf(id)},null,null,
                null,null,null);
        if(c!=null && c.moveToFirst()){
            discounts = buildNoteFromCursor(c);
            if(c.isClosed()){
                c.close();
            }
        }

        return discounts;
    }

    private Discounts buildNoteFromCursor(Cursor cursor){
        Discounts discounts = null;
        if(cursor!=null){
            discounts = new Discounts();
            discounts.setId(cursor.getLong(0));
            discounts.setName(cursor.getString(1));
            discounts.setDiscount(cursor.getString(2));
            discounts.setPrice(cursor.getString(3));
            discounts.setRegion(cursor.getString(3));
            discounts.setImage(cursor.getString(4));

        }
        return discounts;
    }

    public List<Discounts> getAll(){
        List<Discounts> notes = new ArrayList<Discounts>();
        Cursor c = db.query(DiscountsTable.TABLENAME,new String[]{DiscountsTable.COLUMN_ID,DiscountsTable.COLUMN_PRICE,
                DiscountsTable.COLUMN_REGION,DiscountsTable.COLUMN_PRODUCT_NAME,DiscountsTable.COLUMN_IMAGE,DiscountsTable.COLUMN_DISCOUNT },null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            do{
                Discounts d = buildNoteFromCursor(c);
                if(d!=null){
                    notes.add(d);
                }
            }while(c.moveToNext());
            if(c.isClosed()){
                c.close();
            }
        }

        return notes;
    }

}
*/
