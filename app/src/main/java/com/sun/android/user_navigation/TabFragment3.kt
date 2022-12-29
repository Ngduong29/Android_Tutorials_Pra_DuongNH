package com.sun.android.user_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.android.R
import com.sun.android.databinding.FragmentTab2Binding
import com.sun.android.databinding.FragmentTab3Binding

class TabFragment3 : Fragment() {
    val binding by lazy { FragmentTab3Binding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

}
