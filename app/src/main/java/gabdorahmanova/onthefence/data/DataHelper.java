package gabdorahmanova.onthefence.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.Theatre;

/**
 * Created by admin on 11.02.2018.
 */

public class DataHelper extends SQLiteOpenHelper {
    String DB_PATH = null;
    private static String DB_NAME = "onthefencedata";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";

    }

    public void update(ContentValues cv,int id){
        this.openDataBase();
        myDataBase.update("theatres",cv,"_id=?",new String[]{String.valueOf(id)});
        myDataBase.close();
    }
    public void delete(int id){
        try {
            this.openDataBase();
            myDataBase.delete("favourites", "id=" + id, null);

            myDataBase.close();
        }
        catch (Exception e){
            Log.e("delete",e.getMessage());
        }
    }
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }
    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    public void insert(ContentValues cv){
        this.openDataBase();
        myDataBase.insert("favourites",null,cv);
        myDataBase.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor query(String table, String columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        this.openDataBase();
        return myDataBase.query(table, null, null, null, null, null, null);
    }
}
