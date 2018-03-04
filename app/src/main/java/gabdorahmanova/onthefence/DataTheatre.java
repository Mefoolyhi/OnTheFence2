package gabdorahmanova.onthefence;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.io.IOException;
import java.util.ArrayList;

import gabdorahmanova.onthefence.data.DataHelper;

/**
 * Created by admin on 04.03.2018.
 */

public class DataTheatre {
    ArrayList<Theatre> data;

    DataTheatre(Context context) {
        Cursor c;
        data = new ArrayList<>();
        DataHelper helper = new DataHelper(context);

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
                Theatre th = new Theatre(c.getString(1), c.getString(5), c.getString(3), c.getString(2), c.getString(4));
                data.add(th);
            } while (c.moveToNext());
        }
    }

    public ArrayList<Theatre> getData() {
        return data;
    }
}
