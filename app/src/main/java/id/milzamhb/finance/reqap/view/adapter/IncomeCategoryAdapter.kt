package id.milzamhb.finance.reqap.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.IncomeCategoryItemBinding
import id.milzamhb.finance.reqap.utils.CategoryItem

class IncomeCategoryAdapter(private val items:List<CategoryItem>) : RecyclerView.Adapter<
        IncomeCategoryAdapter.ViewHolder>(){
    lateinit var binding : IncomeCategoryItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): IncomeCategoryAdapter.ViewHolder {
        binding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.income_category_item,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int=items.size

    override fun onBindViewHolder(holder: IncomeCategoryAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }
    class ViewHolder(itemBinding : IncomeCategoryItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val title=itemBinding.tvItem
        private val img=itemBinding.imgItem
        fun bind(bindItem : CategoryItem){
            title.text=bindItem.title
            img.setImageResource(bindItem.image)
        }
    }

}