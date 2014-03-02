package groupo.travellight.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBTrip{
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATE = "date";

    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "TravelLight";
    private static final String DATABASE_TABLE = "Trip";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
            "create table if not exists Trip (id INT not null, "
                    + "name VARCHAR not null, date date);";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBTrip(Context ctx)
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
    public DBTrip open() throws SQLException
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
    public long insertRecord(int id, String name, String date)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, id);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_DATE, date);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteContact(int id)
    {
        return db.delete(DATABASE_TABLE, KEY_ID + "=" + id, null) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ID,
                KEY_NAME, KEY_DATE}, null, null, null, null, null);
    }

    //---retrieves a particular record---
    public Cursor getRecord(int id) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                        KEY_ID, KEY_NAME, KEY_DATE},
                        KEY_ID + "=" + id, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }
    //changes
    //more changes
    //---updates a record---
    public boolean updateRecord(int id, String name, String date)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_ID, id);
        args.put(KEY_NAME, name);
        args.put(KEY_DATE, date);
        return db.update(DATABASE_TABLE, args, KEY_ID + "=" + id, null) > 0;
    }
}