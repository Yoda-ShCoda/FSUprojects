package com.danit.healthprofiler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by youda on 4/28/2018.
 */

public class HealthProfileDB extends SQLiteOpenHelper {

    public HealthProfileDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Test.db", factory, version);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DIETER_STATS(ID INTEGER PRIMARY KEY AUTOINCREMENT, AGE INT, HEIGHT INTEGER, " +
                "WEIGHT INTEGER, BMI INTEGER, MIN_TARGET_HEART_RATE INTEGER, MAX_TARGET_HEART_RATE INTEGER, MAX_HEART_RATE INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVrsion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DIETER_STATS;");
        onCreate(db);
    }

    public long insert_stats(Dieter dieter) {
        ContentValues cv = new ContentValues();
        cv.put("AGE", dieter.getAge());
        cv.put("HEIGHT", dieter.getHeight());
        cv.put("WEIGHT", dieter.getWeight());
        cv.put("BMI",dieter.getBMI());
        cv.put("MIN_TARGET_HEART_RATE", dieter.getMinTargetHeartRate());
        cv.put("MAX_TARGET_HEART_RATE", dieter.getMaxTargetHeartRate());
        cv.put("MAX_HEART_RATE", dieter.getMaxHeartRate());

        long id = this.getWritableDatabase().insertOrThrow("DIETER_STATS", null, cv);

        return id;
    }

    /*
    public void delete_student(String firstname) {
        this.getWritableDatabase().delete("STUDENTS", "FIRSTNAME='" + firstname+"'", null);
    }

    public void update_student(String old_firstname, String new_firstname) {
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='" + new_firstname+"' WHERE FIRSTNAME='"+old_firstname+"'");
    }

    public void displayStats(TextView textView) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM DIETER_STATS", null);
        textView.setText("");
        while (cursor.moveToNext()) {
            textView.append("ID: " + cursor.getInt(0) + "; ");
            textView.append("AGE: " + cursor.getInt(1) + "; ");
            textView.append("HEIGHT: " + cursor.getInt(2) + "; ");
            textView.append("WEIGHT: " + cursor.getInt(3) + "; ");
            textView.append("BMI: " + cursor.getInt(4) + "; ");
            textView.append("MIN TARGET: " + cursor.getInt(5) + "; ");
            textView.append("MAX TARGET: " + cursor.getInt(6) + "; ");
            textView.append("MAX RATE: " + cursor.getInt(7) + "\n");
        }
    }*/
}
