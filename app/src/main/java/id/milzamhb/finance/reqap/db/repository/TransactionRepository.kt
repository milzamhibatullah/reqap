package id.milzamhb.finance.reqap.db.repository

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.support.annotation.WorkerThread
import id.milzamhb.finance.reqap.db.dao.TransactionDao
import id.milzamhb.finance.reqap.model.Transaction
import id.milzamhb.finance.reqap.model.pojo.SumAvg

class TransactionRepository(private val transDao : TransactionDao){
    val selectAll: LiveData<List<Transaction>> =  transDao.getData()
    val totalExpense : LiveData<List<Transaction>> = transDao.getTotalExpense()
    @WorkerThread
    fun insert(transaction: Transaction){
        insertAsynctask(transDao).execute(transaction)
    }
    fun groupByDate(mont : String,year : String) : LiveData<List<SumAvg>>{
        return transDao.groupByDate(mont,year)
    }
    fun getByDate(date : String) : LiveData<List<Transaction>>{
        return transDao.getByDate(date)
    }
    //inside class
    private class insertAsynctask internal constructor(private val mDao : TransactionDao)
        :AsyncTask<Transaction,Void,Void>(){
        override fun doInBackground(vararg params: Transaction): Void? {
            mDao.insert(params[0])
            return null
        }

    }
}