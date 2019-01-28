package id.milzamhb.finance.reqap.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.FragmentTransactionBinding

class FragmentTransaction : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTransactionBinding>(
            inflater,R.layout.fragment_transaction,container,false
        )
        return binding.root
    }
}