/*
package inclass2.group1.beaconproject.dataTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import inclass2.group1.beaconproject.Discounts;

public class DatabaseDataManager {

    private Context mContext;
    private DatabaseOpenHelper databaseOpenHelper;
    private SQLiteDatabase db;
    private DiscountDAO discountDAO;

    public DatabaseDataManager(Context mContext) {
        this.mContext = mContext;
        databaseOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = databaseOpenHelper.getWritableDatabase();
        discountDAO = new DiscountDAO(db);
    }

    public void close(){
        if(db!=null){
            db.close();
        }
    }

    public DiscountDAO getNoteDAO(){
        return this.discountDAO;
    }

    public long save(Discounts discounts){
        return this.discountDAO.save(discounts);
    }

    public boolean update(Discounts discounts){
        return this.discountDAO.update(discounts);
    }

    public boolean delete(Discounts discounts){
        return  this.discountDAO.delete(discounts);
    }

    public Discounts get(long id){
        return this.discountDAO.get(id);
    }

    public List<Discounts> getAll(){
        return this.discountDAO.getAll();
    }

}
*/
