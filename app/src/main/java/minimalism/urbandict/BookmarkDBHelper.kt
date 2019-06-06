package minimalism.urbandict

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BookmarkDBHelper: SQLiteOpenHelper {

    companion object {
        val database_name = "bookmark.db"
        val table_name = "bookmark_tb"
        val column_name_word = "word"
    }

    constructor(ctx: Context?) : super(ctx, database_name, null, 1) {

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val cmd = "create table $table_name(_id integer primary key, $column_name_word text)"
        db?.execSQL(cmd)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val cmd = "drop table if exists $table_name"
        db?.execSQL(cmd)

        onCreate(db)
    }

    fun addItem(word: String) {
        var values = ContentValues()
        values.put(column_name_word, word)

        writableDatabase.insert(table_name, null, values);
    }

    fun removeItem(word: String) {
        val selection = "$column_name_word like ?"
        val selectionArg = arrayOf(word)

        writableDatabase.delete(table_name, selection, selectionArg)
    }

    fun getAllItem(): ArrayList<String> {
        val projection = arrayOf(column_name_word)
        var cursor = readableDatabase.query(table_name, projection, null, null, null, null, null)

        var items = ArrayList<String>()
        while (cursor.moveToNext()) {
            val columIndex = cursor.getColumnIndex(column_name_word)
            val word = cursor.getString(columIndex)
            items.add(word)
        }

        return items
    }
}