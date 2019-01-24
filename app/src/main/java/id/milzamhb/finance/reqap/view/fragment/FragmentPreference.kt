package id.milzamhb.finance.reqap.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import id.milzamhb.finance.reqap.databinding.FragmentPrefrenceBinding
import id.milzamhb.finance.reqap.R

class FragmentPreference : Fragment(){
    lateinit var start : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding=DataBindingUtil.inflate<FragmentPrefrenceBinding>(
            inflater,R.layout.fragment_prefrence,container,false
        )
        start=binding.btnStartPref
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start.setOnClickListener { Navigation.findNavController(it).
            navigate(R.id.action_fragmentPreference_to_fragmentPersonalize) }
    }
}