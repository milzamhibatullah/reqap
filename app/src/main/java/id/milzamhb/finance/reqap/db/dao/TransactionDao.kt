package id.milzamhb.finance.reqap.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import id.milzamhb.finance.reqap.model.Transaction

@Dao
interface TransactionDao{
    @Query("SELECT * FROM transaction_table ORDER BY id desc")
    fun getData() : LiveData<List<Transaction>>

    @Insert
    fun insert(transaction: Transaction)

    @Query("DELETE FROM transaction_table")
    fun deleteAll()

}