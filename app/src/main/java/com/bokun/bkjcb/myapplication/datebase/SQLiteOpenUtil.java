package com.bokun.bkjcb.myapplication.datebase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.bokun.bkjcb.myapplication.Constants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by DengShuai on 2017/3/20.
 * Description : 数据库打开工具类
 */

public class SQLiteOpenUtil extends SQLiteOpenHelper {
    private static String DB_PATH = Constants.DATABASE_PATH;

    private static String DB_NAME = "ds.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    private static boolean mainTmpDirSet = false;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public SQLiteOpenUtil(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            copyDataBase();

        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database does't exist yet.
            return false;

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() {
        try {
            //Open your local db as the input stream
            InputStream myInput = myContext.getAssets().open(DB_NAME);
            // Path to the just created empty db
            String outFileName = DB_PATH + DB_NAME;

            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*@Override
    public SQLiteDatabase getReadableDatabase() {
        if (!mainTmpDirSet) {
            boolean rs = new File("/data/data/com.bokun.bkjcb.infomationmanage/databases/main").mkdir();
            L.i(rs + "");
            super.getReadableDatabase().execSQL("PRAGMA temp_store_directory='/data/data/com.bokun.bkjcb.infomationmanage/databases/main'");
            mainTmpDirSet = true;
            return super.getReadableDatabase();
        }
        return super.getReadableDatabase();
    }*/

    public SQLiteDatabase openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return myDataBase;
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }


    /*public SQLiteOpenUtil(Context context) {
        super(context, "ds.db", null, 6);
    }

    public SQLiteOpenUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
       /* db.execSQL(Constants.table_author);
        db.execSQL(Constants.table_shi);
        db.execSQL(Constants.table_sx);
        db.execSQL(Constants.table_fy);
        db.execSQL(Constants.table_dynasty);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
