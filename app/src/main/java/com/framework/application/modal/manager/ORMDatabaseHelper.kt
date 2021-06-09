package com.framework.application.modal.manager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import java.util.concurrent.atomic.AtomicInteger

class ORMDatabaseHelper(context: Context) :
    OrmLiteSqliteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "app-database"
        private val DATABASE_VERSION = 1
        private val usageCounter = AtomicInteger(0)
        var _ORMDatabaseHelper: ORMDatabaseHelper? = null

        @Synchronized
        fun getHelper(context: Context): ORMDatabaseHelper? {
            if (_ORMDatabaseHelper == null) {
                _ORMDatabaseHelper = ORMDatabaseHelper(context)
            }
            usageCounter.incrementAndGet()
            return _ORMDatabaseHelper
        }
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase?, connectionSource: ConnectionSource?) {

    }

    override fun onUpgrade(
        sqLiteDatabase: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        i: Int,
        i1: Int
    ) {

    }

}
