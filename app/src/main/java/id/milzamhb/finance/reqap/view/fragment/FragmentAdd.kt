package id.milzamhb.finance.reqap.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Log
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
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class FragmentAdd : Fragment() {

    private lateinit var transactionTitle : TextView
    private lateinit var category : TextView
    private lateinit var dateField : EditText
    private lateinit var tvImageUpload : TextView
    private lateinit var etAmount : EditText
    private lateinit var etName : EditText
    private lateinit var etCategory : EditText
    private lateinit var btnSave : Button
    private lateinit var previewImage : ImageView
    private lateinit var btnAddImage : ImageButton
    private lateinit var etPayment : Spinner
    private lateinit var btnClose : ImageButton
    private var bitmap : Bitmap?=null
    private val REQUEST_IMAGE_CAPTURE = 1
    private var type : Int?=0
    private var currentPhotoPath : String?=null
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
        btnAddImage=binding.btnAddImageTrans
        tvImageUpload=binding.tvUploadBuktiTrans
        previewImage=binding.imgUploadTrans
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
            saveToNewStrorage()
            val amount : Double=etAmount.text.toString().toDouble()
            val transaction = Transaction(0,etName.text.toString(),etCategory.text.toString(),
                type!!,inputDate!!,amount,etPayment.selectedItem.toString(),currentPhotoPath!!
            )
            viewModel.insert(transaction)
            Navigation.findNavController(it).navigate(R.id.action_fragmentAdd_to_fragmentTransaction)
        }
        btnClose.setOnClickListener { (activity as AppCompatActivity).onBackPressed() }
        dateField.setOnClickListener {
            showPickerDialog()
        }
        btnAddImage.setOnClickListener {
              takePicture()
        }
    }

    private fun takePicture() {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
                intent.resolveActivity(context!!.packageManager)?.also {
                       startActivityForResult(intent,REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    

    @SuppressLint("SimpleDateFormat")
    private fun showPickerDialog() {
        val calendar=Calendar.getInstance()
            DatePickerDialog(context!!,DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val displayFormat = "dd MMMM yyyy"
                val inputFormat = "yyyy-MM-dd"
                dateField.setText(SimpleDateFormat(displayFormat, Locale("in","ID")).format(calendar.time))
                inputDate=SimpleDateFormat(inputFormat).format(calendar.time)
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            hideUploadBtn()
            bitmap=data?.extras!!.get("data") as Bitmap
            previewImage.setImageBitmap(bitmap)
        }
    }


    private fun hideUploadBtn():Boolean{
        btnAddImage.visibility=View.GONE
        tvImageUpload.visibility=View.GONE
        previewImage.visibility=View.VISIBLE
        return true
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            context?.sendBroadcast(mediaScanIntent)
        }
    }

    @SuppressLint("SdCardPath", "SimpleDateFormat")
    private fun saveToNewStrorage() {
        val timeStamp: String = SimpleDateFormat("ddMMyyyy_HHmmss").format(Date())
        val storageDir: File = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val image = File.createTempFile(
            "BUKTI_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            currentPhotoPath=absolutePath
        }
     try {
         val out = FileOutputStream(image)
         bitmap!!.compress(Bitmap.CompressFormat.JPEG,70,out)
         out.flush()
         out.close()
     }catch (e : Exception){e.localizedMessage}
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 0) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePicture()
            }
        }
    }

    
}