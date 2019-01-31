package id.milzamhb.finance.reqap.db

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import id.milzamhb.finance.reqap.db.dao.TransactionDao
public abstract class Databases : RoomDatabase(){
        abstract fun transDao() : TransactionDao

    companion object {
        @Volatile
        private var INSTANCE:Databases?=null
        fun getDatabase(context: Context) : Databases{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    Databases::class.java,
                    "reqapDB"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}