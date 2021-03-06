package id.milzamhb.finance.reqap.view

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.databinding.ActivityMainBinding
import id.milzamhb.finance.reqap.utils.AddItemCategory
import id.milzamhb.finance.reqap.view.adapter.ExpenseCategoryAdapter
import id.milzamhb.finance.reqap.view.adapter.IncomeCategoryAdapter
import id.milzamhb.finance.reqap.view.fragment.FragmentTransactionDirections

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavBar : BottomNavigationView
    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main)
         navController = Navigation.findNavController(this,R.id.mainNavHost)
        bottomNavBar=binding.bottomNavigationView
        NavigationUI.setupWithNavController(bottomNavBar,navController)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
