package com.sun.android.user_navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class PagerAdapter(fm: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fm,lifecycle){
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return TabFragment1()
            1 -> return TabFragment2()
            2 -> return TabFragment3()
        }
        return TabFragment1()
    }
}
