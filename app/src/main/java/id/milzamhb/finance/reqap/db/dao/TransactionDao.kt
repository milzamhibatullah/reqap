package id.milzamhb.finance.reqap.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import id.milzamhb.finance.reqap.model.Transaction
import id.milzamhb.finance.reqap.model.pojo.SumAvg

@Dao
interface TransactionDao{
    @Query("SELECT * FROM transaction_table ORDER BY id desc")
    fun getData() : LiveData<List<Transaction>>
    @Query("SELECT * FROM transaction_table WHERE type_category=1")
    fun getTotalExpense() : LiveData<List<Transaction>>
    @Insert
    fun insert(transaction: Transaction)
    @Query("SELECT SUM(amount) as total,AVG(amount) as avg, date from transaction_table group by date")
    fun  groupByDate() : LiveData<List<SumAvg>>
    @Query("select * from transaction_table where date =:date order by id desc")
    fun getByDate(date : String) : LiveData<List<Transaction>>
    @Query("DELETE FROM transaction_table")
    fun deleteAll()

}