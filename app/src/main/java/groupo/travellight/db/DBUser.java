package groupo.travellight.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBUser {
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_JOINDATE = "joindate";
    public static final String KEY_CITY = "city";
    public static final String KEY_STATE = "state";
    public static final String KEY_COUNTRY = "country";
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "TravelLight";
    private static final String DATABASE_TABLE = "User";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
            "create table if not exists User (email VARCHAR not null, "
                    + "password VARCHAR not null, joindate date, city VARCHAR, state VARCHAR, country VARCHAR );";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBUser(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {

            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBUser open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a record into the database---
    public long insertRecord(String email, String password, String joindate, String city, String state, String country)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_PASSWORD, password);
        initialValues.put(KEY_JOINDATE, joindate);
        initialValues.put(KEY_CITY, city);
        initialValues.put(KEY_STATE, state);
        initialValues.put(KEY_COUNTRY, country);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_EMAIL + "=" + rowId, null) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_EMAIL,
                KEY_PASSWORD, KEY_JOINDATE, KEY_CITY, KEY_STATE, KEY_COUNTRY}, null, null, null, null, null);
    }

    //---retrieves a particular record---
    public Cursor getRecord(String email) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                        KEY_EMAIL, KEY_PASSWORD, KEY_JOINDATE, KEY_CITY, KEY_STATE, KEY_COUNTRY},
                        KEY_EMAIL + "=?", new String[] {email}, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }
//changes
    //more changes
    //---updates a record---
    public boolean updateRecord(String email, String password, String joindate, String city, String state, String country)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_EMAIL, email);
        args.put(KEY_PASSWORD, password);
        args.put(KEY_JOINDATE, joindate);
        args.put(KEY_CITY, city);
        args.put(KEY_STATE, state);
        args.put(KEY_COUNTRY, country);
        return db.update(DATABASE_TABLE, args, KEY_EMAIL + "=" + email, null) > 0;
    }
}
