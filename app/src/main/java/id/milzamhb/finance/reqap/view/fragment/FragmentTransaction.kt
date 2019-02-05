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
import android.widget.Toast
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.FragmentTransactionBinding
import id.milzamhb.finance.reqap.model.Transaction
import id.milzamhb.finance.reqap.model.pojo.SumAvg
import id.milzamhb.finance.reqap.view.adapter.ItemTransAdapter
import id.milzamhb.finance.reqap.viewmodel.TransactionViewModel
import id.milzamhb.finance.reqap.view.adapter.TransactionAdapter
class FragmentTransaction : Fragment(), TransactionAdapter.SetData {


    lateinit var recyclerView: RecyclerView
    lateinit var transViewModel : TransactionViewModel
    lateinit var itemTransVM : TransactionViewModel
    lateinit var noData : TextView
    private var transAdapter : TransactionAdapter?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTransactionBinding>(
            inflater,R.layout.fragment_transaction,container,false
        )
        recyclerView=binding.recyclerTransaction
        noData=binding.tvNoData
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transAdapter =TransactionAdapter(context!!,this)
        transViewModel=ViewModelProviders.of(this).get(TransactionViewModel::class.java)
        itemTransVM=ViewModelProviders.of(this).get(TransactionViewModel::class.java)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(context)
            adapter = transAdapter
        }
        displayData()

    }

    private fun displayData(){
       transViewModel.groupByDate.observe(this,Observer<List<SumAvg>>{
           t -> if (t!!.isNotEmpty()){
                noData.visibility=View.GONE
                transAdapter!!.setTransaction(t)
            }else{
                noData.visibility=View.VISIBLE
            }
       })
    }
    override fun set(date: String, adapter: ItemTransAdapter) {
       itemTransVM.getByDate(date).observe(this,Observer<List<Transaction>>{
           t -> if (t!!.isNotEmpty()){
                adapter.setTransaction(t)
            }else Toast.makeText(context,"No Data",Toast.LENGTH_SHORT).show()
       })
    }
}