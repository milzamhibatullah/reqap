package id.milzamhb.finance.reqap.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.IncomeCategoryItemBinding
import id.milzamhb.finance.reqap.utils.CategoryItem

class IncomeCategoryAdapter(private val items:List<CategoryItem>,val listener : IncomeData ) : RecyclerView.Adapter<
        IncomeCategoryAdapter.ViewHolder>(){
    lateinit var binding : IncomeCategoryItemBinding
    private var incomeData : IncomeData ?=null
    interface IncomeData{
        fun set(kategori : String, type :Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): IncomeCategoryAdapter.ViewHolder {
        binding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.income_category_item,parent,false)
        incomeData=listener
        return ViewHolder(binding,incomeData!!)
    }

    override fun getItemCount(): Int=items.size

    override fun onBindViewHolder(holder: IncomeCategoryAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }
    class ViewHolder(itemBinding : IncomeCategoryItemBinding, private val incomeData: IncomeData) : RecyclerView.ViewHolder(itemBinding.root) {
        private val title=itemBinding.tvItem
        private val img=itemBinding.imgItem
        fun bind(bindItem : CategoryItem){
            title.text=bindItem.title
            img.setImageResource(bindItem.image)
            itemView.setOnClickListener {
                val kategori=bindItem.title
                val type=2
                incomeData.set(kategori,type)
            }
        }
    }

}