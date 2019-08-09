package com.bikloo.c0752984_mad3125_midterm.core.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import com.bikloo.c0752984_mad3125_midterm.core.User
import com.bikloo.c0752984_mad3125_midterm.core.datastore.DataStore

class DBHelper (val context: Context) : SQLiteOpenHelper(context, DataStore.DATABASE_NAME, null, 1 )
{
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = "CREATE TABLE ${DataStore.TABLE_NAME} ( ${DataStore.ID_COLUMN} VARCHAR(25), ${DataStore.PASSWORD_COLUMN} VARCHAR(25) )"
        p0!!.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertUser(user: User)
    {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(DataStore.ID_COLUMN,user.userEmail)
        cv.put(DataStore.PASSWORD_COLUMN,user.userPassword)
        val result = db.insert(DataStore.TABLE_NAME, null,cv)
        db.close()

    }
    var users:MutableList<User> = ArrayList()
    fun readUsers()
    {
        val query = "SELECT * FROM ${DataStore.TABLE_NAME}"
        val db = this.readableDatabase
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
        {
            do {
                val user = User(result.getString(0), result.getString(1))
                users.add(user)
                println(user)
            }while(result.moveToNext())
        }
        db.close()
    }
    fun validate( userID:String,  password:String):Boolean
    {
        for(user in users)
        {
            if (user.userEmail == userID && user.userPassword == password)
            {
                return true
            }

        }
        return false
    }
}