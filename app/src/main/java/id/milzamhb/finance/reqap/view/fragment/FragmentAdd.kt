package id.milzamhb.finance.reqap.view.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import id.milzamhb.finance.reqap.model.Transaction
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.FragmentAddBinding
import kotlinx.android.synthetic.main.activity_main.*
import id.milzamhb.finance.reqap.viewmodel.TransactionViewModel
import kotlinx.android.synthetic.main.activity_preference.view.*
import java.text.SimpleDateFormat
import java.util.*

class FragmentAdd : Fragment() {



    lateinit var transactionTitle : TextView
    lateinit var category : TextView
    lateinit var dateField : EditText
    lateinit var etAmount : EditText
    lateinit var etName : EditText
    lateinit var etCategory : EditText
    lateinit var btnSave : Button
    lateinit var etPayment : Spinner
    lateinit var btnClose : ImageButton
    private var type : Int?=0
    private var inputDate : String?=null
    @SuppressLint("RestrictedApi", "SimpleDateFormat")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding=DataBindingUtil.inflate<FragmentAddBinding>(
            inflater, R.layout.fragment_add,container,false
        )
        (activity as AppCompatActivity).bottomNavigationView.visibility=View.GONE
        bindWidget(binding)
        setSpinner()
        val stringFormat = SimpleDateFormat("dd MMMM yyyy",Locale("in","ID"))
        dateField.setText(stringFormat.format(hariIni()))
        inputDate=SimpleDateFormat("yyyy-MM-dd").format(hariIni())
        val args=FragmentAddArgs.fromBundle(arguments!!)
        type=args.type
        when(type){
            1 -> transactionTitle.text=resources.getString(R.string.tambah_pengeluaran)
            2 -> transactionTitle.text=resources.getString(R.string.tambah_pemasukan)
        }
        category.text = args.kategori
        return binding.root
    }


    private fun bindWidget(binding: FragmentAddBinding?) {
        transactionTitle=binding!!.titleAdd
        category=binding.etAddKategori
        dateField=binding.etAddDate
        etAmount=binding.etAddAmount
        etName=binding.etAddName
        btnSave=binding.btnSaveTrans
        etPayment=binding.etPaymentMethod
        etCategory=binding.etAddKategori
        btnClose=binding.btnCloseAd
    }
    private fun setSpinner() {
           val spinAdapter = ArrayAdapter.createFromResource(
               context!!,R.array.payment_method, android.R.layout.simple_spinner_item)
            spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            etPayment.adapter= spinAdapter
    }

    private fun hariIni () : Date? = Calendar.getInstance().time

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionClicked(view.context)

    }

    @SuppressLint("RestrictedApi")
    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).bottomNavigationView.visibility=View.VISIBLE
    }

    private fun actionClicked(context : Context) {
        val viewModel = ViewModelProviders.of(this).get(TransactionViewModel::class.java)
        btnSave.setOnClickListener {
            val amount : Double=etAmount.text.toString().toDouble()
           val transaction = Transaction(0,etName.text.toString(),etCategory.text.toString(),
               type!!,inputDate!!,amount,etPayment.selectedItem.toString()
           )
           viewModel.insert(transaction)
            Navigation.findNavController(it).navigate(R.id.action_fragmentAdd_to_fragmentTransaction)
        }
        btnClose.setOnClickListener { (activity as AppCompatActivity).onBackPressed() }
        dateField.setOnClickListener {
            showPickerDialog()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun showPickerDialog() {
        val calendar=Calendar.getInstance()
            DatePickerDialog(context,DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val displayFormat = "dd MMMM yyyy"
                val inputFormat = "yyyy-MM-dd"
                dateField.setText(SimpleDateFormat(displayFormat, Locale("in","ID")).format(calendar.time))
                inputDate=SimpleDateFormat(inputFormat).format(calendar.time)
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()

    }



}