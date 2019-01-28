package id.milzamhb.finance.reqap.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.ExpenseItemCategoryBinding
import id.milzamhb.finance.reqap.utils.CategoryItem

class ExpenseCategoryAdapter(private val  items:List<CategoryItem>) : RecyclerView.Adapter<ExpenseCategoryAdapter.ViewHolder>(){

    lateinit var binding : ExpenseItemCategoryBinding
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ExpenseCategoryAdapter.ViewHolder {
        binding=DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.expense_item_category,parent,false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ExpenseCategoryAdapter.ViewHolder, position: Int) {
       holder.bind(items[position])
    }
    class ViewHolder(itemBinding:ExpenseItemCategoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val title=itemBinding.tvItem
        val img=itemBinding.imgItem
        fun bind(bindItem: CategoryItem){
            title.text=bindItem.title
            img.setImageResource(bindItem.image)
        }
    }

}