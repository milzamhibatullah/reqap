package id.milzamhb.finance.reqap.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.FragmentTransactionBinding
import id.milzamhb.finance.reqap.model.Transaction
import id.milzamhb.finance.reqap.viewmodel.TransactionViewModel
import id.milzamhb.finance.reqap.view.adapter.TransactionAdapter
class FragmentTransaction : Fragment(){
    lateinit var recyclerView: RecyclerView
    lateinit var transViewModel : TransactionViewModel
    lateinit var noData : TextView
    private var transAdapter : TransactionAdapter?=null
    private lateinit var amountExpense : TextView
    private lateinit var amountIncome : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTransactionBinding>(
            inflater,R.layout.fragment_transaction,container,false
        )

        recyclerView=binding.recyclerTransaction
        noData=binding.tvNoData
        amountExpense=binding.tvAmountExpense
        amountIncome=binding.tvAmountIncome
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transAdapter =TransactionAdapter(context!!)
        transViewModel=ViewModelProviders.of(this).get(TransactionViewModel::class.java)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(context)
            adapter = transAdapter
        }
        totalExpense()
        displayData()

    }

    private fun displayData(){
        transViewModel.selectAll.observe(this,object : Observer<List<Transaction>>{
            override fun onChanged(t: List<Transaction>?) {
                if (t?.size!=0){
                    transAdapter!!.setTransaction(t!!)
                    noData.visibility=View.GONE
                }else{
                    noData.visibility=View.VISIBLE
                }
            }
        })
    }

    fun totalExpense (){
        var amount =0.00
        transViewModel.totalExpense.observe(this,
            Observer<List<Transaction>> { t ->
                if (t?.size!=0){
                    for (i in t!!.indices){
                        amount += t[i].amount
                        amountExpense.text=amount.toString()
                    }
                }else{
                    amountExpense.text=resources.getString(R.string.nol_rupiah)
                }
            })
    }
}