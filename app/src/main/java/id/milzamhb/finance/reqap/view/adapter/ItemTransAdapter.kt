package id.milzamhb.finance.reqap.view.adapter

import id.milzamhb.finance.reqap.model.Transaction
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.TransactionItemBinding
import java.text.DecimalFormat

class ItemTransAdapter(val context : Context) : RecyclerView.Adapter<ItemTransAdapter.ViewHolder>(){
    lateinit var binding: TransactionItemBinding
    private var transaction : List<Transaction>?=null
    fun setTransaction(transaction: List<Transaction>){
        this.transaction=transaction
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemTransAdapter.ViewHolder {
        binding=DataBindingUtil.inflate(
            LayoutInflater.from(p0.context), R.layout.transaction_item,p0,false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (transaction!=null) transaction!!.size
        else 0
    }

    override fun onBindViewHolder(p0: ItemTransAdapter.ViewHolder, p1: Int) {
           if (transaction!=null){
               val format=DecimalFormat("#,###").format(transaction!![p1].amount)
               if (transaction!![p1].type_category ==2) p0.amount.setTextColor(Color.parseColor("#3F51B5"))
               p0.category.text=transaction!![p1].category
               p0.ket.text=transaction!![p1].name
               p0.amount.text="Rp.$format"
           }
    }

    class ViewHolder(itemBinding: TransactionItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
          val category = itemBinding.tvStatisticCategory
          val ket = itemBinding.tvStatisticName
          val amount = itemBinding.tvStatisticAmount
    }
}