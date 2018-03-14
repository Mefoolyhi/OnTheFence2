package gabdorahmanova.onthefence.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
        helper.close();
        Theatre theatre = new Theatre(c.getLong(0), c.getString(1), c.getString(5), c.getString(3), c.getString(2), c.getString(4));
        return theatre;


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
                Theatre th = new Theatre(c.getLong(0), c.getString(1), c.getString(5), c.getString(3), c.getString(2), c.getString(4));
                data.add(th);
            } while (c.moveToNext());
        }
        helper.close();
        return data;
    }
}
