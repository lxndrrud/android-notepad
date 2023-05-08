package com.lxndrrud.lab1.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.lxndrrud.lab1.domain.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ItemRepo extends SQLiteOpenHelper {
    public ItemRepo(Context context) {
        super(context, "lab1database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "CREATE TABLE IF NOT EXISTS items  ( " +
                "id INTEGER PRIMARY KEY, " +
                "title VARCHAR(30) NOT NULL, " +
                "text TEXT NOT NULL," +
                "createdAt INTEGER NOT NULL " +
                ");";
        db.execSQL(statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String statement = "DROP TABLE IF EXISTS items";
        db.execSQL(statement);
    }
    public List<Item> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        String statement ="SELECT id, title, text, createdAt FROM items ORDER BY createdAt DESC ";
        Cursor cursorItems = db.rawQuery(statement, null);
        ArrayList<Item> itemList = new ArrayList<>();

        if (cursorItems.moveToFirst()) {
            do {
                itemList.add(new Item(
                    cursorItems.getInt(0),
                    cursorItems.getString(1),
                    cursorItems.getString(2),
                    new Date(cursorItems.getLong(3))
                ));
            }
            while(cursorItems.moveToNext());
        }
        cursorItems.close();
        db.close();
        return itemList;
    }

    public void AddItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        java.sql.Date dateDB =  new java.sql.Date(item.getCreatedAt().getTime());

        String sql = "INSERT INTO items (title, text, createdAt) VALUES (?, ?, ?); ";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, item.getTitle());
        stmt.bindString(2, item.getText());
        stmt.bindLong(3, dateDB.getTime());
        stmt.execute();
        stmt.clearBindings();
        db.close();
    }

    public void DeleteItem(long itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM items WHERE id = ? ";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindLong(1, itemId);
        stmt.execute();
        stmt.clearBindings();
        db.close();
    }

    public Item GetItemById(long itemId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String statement ="SELECT id, title, text, createdAt FROM items WHERE id = " + itemId;
        Cursor cursorItems = db.rawQuery(statement, null);
        Item resultItem = null;

        if (cursorItems.moveToFirst()) {
            resultItem = new Item(
                        cursorItems.getInt(0),
                        cursorItems.getString(1),
                        cursorItems.getString(2),
                        new Date(cursorItems.getLong(3))
            );
        }
        cursorItems.close();
        db.close();
        return resultItem;
    }

    public void UpdateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        java.sql.Date dateDB =  new java.sql.Date(item.getCreatedAt().getTime());

        String sql = "UPDATE items SET title = ?, text = ?, createdAt = ? WHERE id = ? ;";

        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, item.getTitle());
        stmt.bindString(2, item.getText());
        stmt.bindLong(3, dateDB.getTime());
        stmt.bindLong(4, item.getId());
        stmt.execute();
        stmt.clearBindings();
        db.close();
    }
}
