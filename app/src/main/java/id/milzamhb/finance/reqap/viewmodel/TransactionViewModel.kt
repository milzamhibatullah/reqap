package id.milzamhb.finance.reqap.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import id.milzamhb.finance.reqap.db.Databases
import id.milzamhb.finance.reqap.db.repository.TransactionRepository
import id.milzamhb.finance.reqap.model.Transaction
import id.milzamhb.finance.reqap.model.pojo.SumAvg

class TransactionViewModel(application: Application) : AndroidViewModel(application){
        private val repository : TransactionRepository
        val selectAll : LiveData<List<Transaction>>
        val totalExpense : LiveData<List<Transaction>>

        init {
            val  transDao= Databases.getDatabase(application).transDao()
            repository= TransactionRepository(transDao)
            selectAll=repository.selectAll
            totalExpense=repository.totalExpense
        }

    fun insert(transaction: Transaction){
        repository.insert(transaction)
    }
    fun groupByDate(month : String,year : String) : LiveData<List<SumAvg>>{
        return repository.groupByDate(month,year)
    }
    fun getByDate(date : String ) : LiveData<List<Transaction>>{
        return repository.getByDate(date)
    }
}