package com.sun.android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sun.android.R
import com.sun.android.databinding.FragmentSimpleBinding


private const val YES = 0
private const val NO = 1

class SimpleFragment : Fragment() {
    val  binding by lazy { FragmentSimpleBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
           val textView: TextView = binding.fragmentHeader
           val radioButton: View = binding.radioGroup.findViewById(checkedId)
           val index: Int = binding.radioGroup.indexOfChild(radioButton)
           when(index){
               YES -> textView.setText(R.string.yes_message)
               NO -> textView.setText(R.string.no_message)
           }
       }
        
        binding.radtingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            val myRating: String = getString(R.string.my_rating) + ratingBar.getRating()
            Toast.makeText(context, myRating, Toast.LENGTH_SHORT).show()
        }

        
        return binding.root
    }
    fun newInstance(): SimpleFragment? {
        return SimpleFragment()
    }

}
