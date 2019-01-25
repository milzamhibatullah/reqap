package id.milzamhb.finance.reqap.view.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import id.milzamhb.finance.reqap.databinding.FragmentPersonaliseBinding
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.model.User
import id.milzamhb.finance.reqap.view.*
import id.milzamhb.finance.reqap.utils.StaticClass
import id.milzamhb.finance.reqap.utils.datarepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentPersonalize : Fragment(){
    lateinit var fullName : EditText
    lateinit var emailAddress : EditText
    lateinit var phoneNumber : EditText
    lateinit var enter : Button
    lateinit var pref : SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentPersonaliseBinding>(
            inflater,R.layout.fragment_personalise,container,false
        )
        pref=activity!!.applicationContext.getSharedPreferences(StaticClass.FILE_NAME,Context.MODE_PRIVATE)
        fullName=binding.etFullName
        emailAddress=binding.etEmailAddress
        phoneNumber=binding.etPhoneNumber
        enter=binding.btnEnter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enter.setOnClickListener {
            if (fullName.text.isEmpty() || emailAddress.text.isEmpty() || phoneNumber.text.isEmpty()){
                Toast.makeText(context,R.string.cek_inputan,Toast.LENGTH_LONG).show()
            }else {
                newMember()
                setSharedPref()
            }
        }
    }
    private fun newMember(){
        val retroServices=datarepo.create()
        retroServices.register(fullName.text.toString(),emailAddress.text.toString(),
            phoneNumber.text.toString(),"rahasia")
            .enqueue(object :Callback<List<User>>{
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.e("GAGAL","error : ${t.message}")
                }
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful){
                        val data=response.body()
                        Log.i("size","berhasil")

                    }
                }

            })
    }

    private fun setSharedPref() {
        val editor= pref.edit()
        editor.putString(StaticClass.PERSONALIZED,"true")
        editor.putString(StaticClass.FULL_NAME,fullName.text.toString())
        editor.putString(StaticClass.EMAIL,emailAddress.text.toString())
        editor.putString(StaticClass.PHONE,phoneNumber.text.toString())
        editor.apply()
        gotoDashboard()
    }
    private fun gotoDashboard(){
        val intent=Intent(context!!,MainActivity::class.java)
        startActivity(intent)
        (activity as AppCompatActivity).finish()
    }
}