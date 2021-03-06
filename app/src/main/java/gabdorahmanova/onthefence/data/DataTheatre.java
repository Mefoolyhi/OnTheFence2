package gabdorahmanova.onthefence.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import gabdorahmanova.onthefence.Units.Theatre;

/**
 * Created by admin on 04.03.2018.
 */

public class DataTheatre {


    DataHelper helper;

    public DataTheatre(Context context) {


        helper = new DataHelper(context);

    }

    public Theatre getTheatre(int id) {
        Cursor c = helper.query("theatres", null, null, null, null, null, null);
        c.moveToPosition(id);
        Theatre theatre = new Theatre(c.getInt(0), c.getString(1), c.getString(6), c.getString(3), c.getString(2),c.getString(4),c.getString(5),c.getString(7));

        helper.close();
        return theatre;


    }
    public void addToFavourites(Theatre theatre,int position){
        ContentValues cv = new ContentValues();

        cv.put("name",theatre.getName());
        cv.put("id_in_array",--position);
        cv.put("type","theatre");
        cv.put("id",theatre.getName());

        helper.insert(cv);

    }
    public void deleteFromFavourites(String id){
        int j = this.getFavourites().size();
        helper.delete(id);
        int y = this.getFavourites().size();
        if (y == j){
            Log.e("Delete","Удалить не удалось");
        }






    }
    public void updateStatus(Theatre th, String id){
        ContentValues cv = new ContentValues();
        cv.put("name",th.getName());
        cv.put("picture",th.getPiclink());
        cv.put("info",th.getInfo());
        cv.put("link",th.getLink());
        cv.put("fav",id);
        helper.update(cv,th.getId());


    }
    public Theatre getFromFavourites(int pos){
        Cursor c = helper.query("favourites", null, null, null, null, null, null);
        c.moveToPosition(pos);
        int ret = c.getInt(3);
        Theatre th = this.getTheatre(ret);
        return th;

    }
    public ArrayList<Theatre> getFavourites(){
        Cursor c;
        ArrayList<Theatre> data = new ArrayList<>();
        try {
            helper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            helper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c = helper.query("favourites", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                data.add(this.getTheatre(c.getInt(3)));
            } while (c.moveToNext());
        }
        helper.close();

        Log.e("DT", String.valueOf(data.size()));
        return data;
    }

    public ArrayList<Theatre> getData() {
        Cursor c;
        ArrayList<Theatre> data = new ArrayList<>();
        try {
            helper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            helper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c = helper.query("theatres", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                Theatre th = new Theatre(c.getInt(0), c.getString(1), c.getString(6), c.getString(3), c.getString(2), c.getString(4),c.getString(5),c.getString(7));

                data.add(th);
            } while (c.moveToNext());
        }
        helper.close();
        return data;
    }
}
