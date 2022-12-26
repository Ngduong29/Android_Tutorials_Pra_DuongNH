package com.sun.android.fragments


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sun.android.R
import com.sun.android.databinding.ActivityMainFragmentsBinding


class MainActivityFragments : AppCompatActivity() {
    val binding by lazy { ActivityMainFragmentsBinding.inflate(layoutInflater) }
    private var isFragmentDisplayed: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonOpen.setOnClickListener {
            if(!isFragmentDisplayed){
                displayFragment()
            }else{
                closeFragment()
            }
        }
    }


    fun displayFragment() {
        val simpleFragment: SimpleFragment? = SimpleFragment().newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        if (simpleFragment != null) {
            fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit()
        }
        binding.buttonOpen.setText(R.string.close)
        isFragmentDisplayed = true

    }

    fun closeFragment(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val simpleFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if (simpleFragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
        }
        binding.buttonOpen.setText(R.string.open)
        isFragmentDisplayed = false
    }

}
