package groupo.travellight.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Gabriel on 3/3/14.
 */
public class DBEdit {
    public static final String KEY_EMAIL = "email";
    public static final String KEY_TRIP_ID = "trip_id";
    public static final String KEY_PACKING = "packing_list";
    public static final String KEY_EVENTS = "events_bag";

    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "TravelLight";
    private static final String DATABASE_TABLE = "Edit";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
            "create table if not exists Trip (email VARCHAR not null, "
                    + "trip_id int not null, packing_list VARCHAR, events_bag VARCHAR);";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBEdit(Context ctx)
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
    public DBEdit open() throws SQLException
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
    public long insertRecord(String email, int id, String packing, String events)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_TRIP_ID, id);
        initialValues.put(KEY_PACKING, packing);
        initialValues.put(KEY_EVENTS, events);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteContact(String email)
    {
        return db.delete(DATABASE_TABLE, KEY_EMAIL + "=" + email, null) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_EMAIL,
                KEY_TRIP_ID, KEY_PACKING, KEY_EVENTS}, null, null, null, null, null);
    }

    //---retrieves a particular record---
    public Cursor getRecord(String email) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                        KEY_EMAIL, KEY_TRIP_ID, KEY_PACKING, KEY_EVENTS},
                        KEY_EMAIL + "=" + email, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }
    //changes
    //more changes
    //---updates a record---
    public boolean updateRecord(String email, int id, String packing, String events)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_EMAIL, email);
        args.put(KEY_TRIP_ID, id);
        args.put(KEY_PACKING, packing);
        args.put(KEY_EVENTS, events);
        return db.update(DATABASE_TABLE, args, KEY_EMAIL + "=" + email, null) > 0;
    }
}