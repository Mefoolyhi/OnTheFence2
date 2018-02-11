package gabdorahmanova.onthefence.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 11.02.2018.
 */

public class DataHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = DataHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "onthefencedata.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
