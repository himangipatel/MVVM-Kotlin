package com.structure.kotlin.db

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import android.content.Context
import com.structure.kotlin.model.User


/**
 * Created by Himangi on 8/1/19
 */
@Database(entities = [User::class], version = 1)
abstract class DatabaseHandler : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                        "PRIMARY KEY(`id`))")
            }
        }

        val MIGRATION_2_3: Migration = object : Migration(2,3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'user' ADD COLUMN 'address' TEXT")
            }
        }


        val MIGRATION_3_4: Migration = object : Migration(3,4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'user' ADD COLUMN 'photo' TEXT /*DEFAULT '123' not null*/")
            }
        }


        @Volatile
        private var INSTANCE: DatabaseHandler? = null

        fun getDatabase(context: Context): DatabaseHandler {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHandler::class.java,
                    "sampledatabase"
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3,MIGRATION_3_4)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}