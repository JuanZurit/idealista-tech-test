package com.juanzurita.framework.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities =[AdItemEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class IdealistaTechTestDatabase : RoomDatabase() {
    abstract fun adDao(): AdsDao

    companion object {
        @Volatile
        private var instance: IdealistaTechTestDatabase? = null

        fun getInstance(context: Context): IdealistaTechTestDatabase? {
            if (instance == null) {
                synchronized(IdealistaTechTestDatabase::class) {
                    instance = Room.databaseBuilder(
                        context,
                        IdealistaTechTestDatabase::class.java,
                        "ads.db"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build()
                }
            }

            return instance
        }
    }



}