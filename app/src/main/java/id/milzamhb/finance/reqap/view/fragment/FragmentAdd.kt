package id.milzamhb.finance.reqap.view.fragment

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.FragmentAddBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_preference.view.*

class FragmentAdd : Fragment(){
    lateinit var transactionTitle : TextView
    lateinit var category : TextView
    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding=DataBindingUtil.inflate<FragmentAddBinding>(
            inflater, R.layout.fragment_add,container,false
        )
        (activity as AppCompatActivity).bottomNavigationView.visibility=View.GONE
        (activity as AppCompatActivity).floatingActionButton.visibility=View.GONE
        transactionTitle=binding.titleAdd
        category=binding.etAddKategori
        val args=FragmentAddArgs.fromBundle(arguments!!)
        if (args.type==1) transactionTitle.text="Tambah Pengeluaran"
        else transactionTitle.text="Tambah Pemasukan"

        category.text = args.kategori
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).bottomNavigationView.visibility=View.VISIBLE
        (activity as AppCompatActivity).floatingActionButton.visibility=View.VISIBLE
    }
}