package com.example.roomretrofithilt.db

import android.content.Context
import androidx.room.*
import com.example.roomretrofithilt.model.RepositoryData
import com.example.roomretrofithilt.model.TypeConventerOwner

@Database(entities = [RepositoryData::class], version = 1, exportSchema = false)
@TypeConverters(TypeConventerOwner::class)
abstract class AppDatabase :RoomDatabase(){

    abstract fun getAppDao() : AppDao
    companion object{
        private var DB_INSTANCE: AppDatabase? = null
        fun getAppDBInstance(context: Context) : AppDatabase{
            if(DB_INSTANCE==null){
                DB_INSTANCE= Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"APP_DB")
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }

}