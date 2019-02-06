package id.milzamhb.finance.reqap.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.CardTransactionBinding
import id.milzamhb.finance.reqap.model.Transaction
import id.milzamhb.finance.reqap.model.pojo.SumAvg
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class TransactionAdapter(val context : Context, val listener : SetData) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>(){
    lateinit var binding :  CardTransactionBinding
    private  var transaction : List<SumAvg>?=null
    lateinit var setData : SetData

    interface SetData{
        fun set(date : String,adapter: ItemTransAdapter)
    }
    fun setTransaction(transaction : List<SumAvg>){
        this.transaction=transaction
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TransactionAdapter.ViewHolder {
        binding=DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.card_transaction,parent,false
        )
        setData=listener
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
       return if (transaction!=null) transaction!!.size
        else 0
    }

    override fun onBindViewHolder(holder: TransactionAdapter.ViewHolder, position: Int) {
        if (transaction!=null){
            holder.date.text= parseDate(transaction!![position].date)
            holder.amount.text=currencyFormat(transaction!![position].total)
            val itemAdapter = ItemTransAdapter(context)
            holder.recyclerView.apply {
                setHasFixedSize(true)
                layoutManager=LinearLayoutManager(context)
                adapter=itemAdapter
            }
            setData.set(transaction!![position].date,itemAdapter)
        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun parseDate(date: String) : String {
      val parsed = SimpleDateFormat("yyyy-MM-dd").parse(date)
       return SimpleDateFormat("dd MMM yyyy").format(parsed)
    }

    private fun currencyFormat(amount : Double) : String{
        val parsed=DecimalFormat("#,###").format(amount)
        return "Rp.$parsed"
    }

    class ViewHolder (itemBinding: CardTransactionBinding) : RecyclerView.ViewHolder(itemBinding.root) {
         val date = itemBinding.cardTitle
         val recyclerView = itemBinding.cardRecycler
         val amount= itemBinding.cardAmountTitle


    }
}