package id.milzamhb.finance.reqap.view.adapter

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.ExpenseItemCategoryBinding
import id.milzamhb.finance.reqap.utils.CategoryItem
import id.milzamhb.finance.reqap.view.fragment.FragmentTransactionDirections
import kotlinx.android.synthetic.main.activity_preference.view.*

class ExpenseCategoryAdapter(private val  items:List<CategoryItem>,val listener : SetData) : RecyclerView.Adapter<ExpenseCategoryAdapter.ViewHolder>(){

    lateinit var binding : ExpenseItemCategoryBinding
    private var setData : SetData?=null
    interface SetData{
        fun send(type : Int,kategori : String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ExpenseCategoryAdapter.ViewHolder {
        binding=DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.expense_item_category,parent,false
        )
        setData=listener
        return ViewHolder(binding,setData!!)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ExpenseCategoryAdapter.ViewHolder, position: Int) {
       holder.bind(items[position])
    }
    class ViewHolder(itemBinding:ExpenseItemCategoryBinding,val setData : SetData) : RecyclerView.ViewHolder(itemBinding.root) {
        val title=itemBinding.tvItem
        val img=itemBinding.imgItem
        fun bind(bindItem: CategoryItem){
            title.text=bindItem.title
            img.setImageResource(bindItem.image)
            itemView.setOnClickListener {
                val kategori=bindItem.title
                val type=1
                setData.send(type,kategori)
            }
        }
    }

}