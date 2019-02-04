package id.milzamhb.finance.reqap.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.TransactionItemBinding
import id.milzamhb.finance.reqap.model.Transaction

class TransactionAdapter(val context : Context) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>(){
    lateinit var binding : TransactionItemBinding
    private  var transaction : List<Transaction>?=null

    fun setTransaction(transaction : List<Transaction>){
        this.transaction=transaction
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TransactionAdapter.ViewHolder {
        binding=DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.transaction_item,parent,false
        )
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
       return if (transaction!=null) transaction!!.size
        else 0
    }

    override fun onBindViewHolder(holder: TransactionAdapter.ViewHolder, position: Int) {
        if (transaction!=null){
            holder.bindItem(transaction!![position])
        }

    }


    class ViewHolder (itemBinding: TransactionItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val category = itemBinding.tvStatisticCategory
        private val name = itemBinding.tvStatisticName
        private val amount = itemBinding.tvStatisticAmount

        fun bindItem(item : Transaction){
            category.text=item.category
            name.text=item.name
            amount.text=item.amount.toString()
        }
    }
}