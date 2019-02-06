package id.milzamhb.finance.reqap.view.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import id.milzamhb.finance.reqap.view.adapter.ExpenseCategoryAdapter
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.FragmentTransactionBinding
import id.milzamhb.finance.reqap.utils.AddItemCategory
import id.milzamhb.finance.reqap.model.Transaction
import id.milzamhb.finance.reqap.model.pojo.SumAvg
import id.milzamhb.finance.reqap.view.adapter.IncomeCategoryAdapter
import id.milzamhb.finance.reqap.view.adapter.ItemTransAdapter
import id.milzamhb.finance.reqap.viewmodel.TransactionViewModel
import id.milzamhb.finance.reqap.view.adapter.TransactionAdapter
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class FragmentTransaction : Fragment(), TransactionAdapter.SetData,ExpenseCategoryAdapter.SetData,
IncomeCategoryAdapter.IncomeData{


    private lateinit var recyclerView: RecyclerView
    private lateinit var monthTitle : TextView
    private lateinit var transViewModel : TransactionViewModel
    private lateinit var itemTransVM : TransactionViewModel
    private lateinit var noData : TextView
    private var click =0
    private var count =0
    private var ttt : Date?=null
    private lateinit var prevMonth : ImageButton
    private lateinit var nextMonth : ImageButton
    private lateinit var dialog: BottomSheetDialog
    private lateinit var btnNewTrans : FloatingActionButton
    private var transAdapter : TransactionAdapter?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTransactionBinding>(
            inflater,R.layout.fragment_transaction,container,false
        )
        bindWidget(binding)
        monthTitle.text=thisMonth()
        return binding.root
    }

    private fun bindWidget(binding: FragmentTransactionBinding?) {
        recyclerView=binding!!.recyclerTransaction
        noData=binding.tvNoData
        btnNewTrans=binding.btnNewTrans
        monthTitle=binding.monthTransTitle
        prevMonth=binding.transDateLeft
        nextMonth=binding.transDateRight
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonClicked()
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
    private fun buttonClicked() {
        btnNewTrans.setOnClickListener {
            callBottomDialog()
        }
        prevMonth.setOnClickListener {
            click= click.minus(1)
            count=click
            monthTitle.text=getMonthYear()
            displayData()
        }
        nextMonth.setOnClickListener {
            click= click.plus(1)
            count=click
            monthTitle.text=getMonthYear()
            displayData()
        }
    }
    
    @SuppressLint("SimpleDateFormat")
    private fun thisMonth():String{
        val dateFormat = SimpleDateFormat("MMM yyy")
        ttt=Calendar.getInstance().time
        return dateFormat.format(Calendar.getInstance().time)
    }
    @SuppressLint("SimpleDateFormat")
    private fun getMonthYear() : String{
        val dateFormat = SimpleDateFormat("MMM yyyy")
        val cal = Calendar.getInstance()
        val date =Date()
        cal.time=date
        cal.add(Calendar.MONTH,count)
        ttt=cal.time
        return dateFormat.format(cal.time)
    }
    private fun callBottomDialog() {
        val view =layoutInflater.inflate(R.layout.bottom_dialog_category,null)
        val recyclerExpense : RecyclerView=view.findViewById(R.id.recyclerExpenseCategory)
        val recyclerIncome: RecyclerView=view.findViewById(R.id.recyclerIncomeCategory)
        recyclerExpense.visibility=View.VISIBLE
        val adapterExpense= ExpenseCategoryAdapter(
         AddItemCategory.expenseItem(),
            this
        )
        val adapterIncome=IncomeCategoryAdapter(AddItemCategory.incomeItem(),
            this
        )
        recyclerExpense.apply {
            setHasFixedSize(true)
            layoutManager= GridLayoutManager(context,4)
            adapter=adapterExpense
        }

        recyclerIncome.apply {
            setHasFixedSize(true)
            layoutManager= GridLayoutManager(context,4)
            adapter=adapterIncome
        }
        dialog= BottomSheetDialog(view.context)
        dialog.setContentView(view)
        dialog.create()
        dialog.show()
    }
    override fun send(type: Int, kategori: String) {
        if (dialog.isShowing)dialog.dismiss()
        Navigation.findNavController(view!!)
            .navigate(FragmentTransactionDirections.actionFragmentTransactionToFragmentAdd(
            type,kategori
        ))
    }
    override fun set(kategori: String, type: Int) {
        if (dialog.isShowing)dialog.dismiss()
        Navigation.findNavController(view!!)
            .navigate(FragmentTransactionDirections.actionFragmentTransactionToFragmentAdd(
            type,kategori
        ))
    }
    @SuppressLint("SimpleDateFormat")
    private fun displayData(){
        val month=SimpleDateFormat("MM").format(ttt)
        val year=SimpleDateFormat("yyyy").format(ttt)
        transViewModel.groupByDate(month,year).observe(this,Observer<List<SumAvg>>{
            t -> if (t!!.isNotEmpty()) {
                 noData.visibility=View.GONE
                 recyclerView.visibility=View.VISIBLE
                 transAdapter!!.setTransaction(t)
            }else {
            noData.visibility=View.VISIBLE
            recyclerView.visibility=View.GONE
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

    override fun onResume() {
        super.onResume()
        displayData()
    }
}