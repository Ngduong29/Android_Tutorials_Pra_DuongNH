package com.sun.android.user_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sun.android.R
import com.sun.android.databinding.ActivityUserNavigationBinding


class UserNavigationActivity : AppCompatActivity() {
    val binding by lazy { ActivityUserNavigationBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val pagerAdapter = PagerAdapter(supportFragmentManager, lifecycle)
        val viewPager: ViewPager2 = binding.pager
        viewPager.adapter = pagerAdapter
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager){tab, position ->
              when(position){
                  0 -> tab.text = getString(R.string.tab_label1)
                  1 -> tab.text = getString(R.string.tab_label2)
                  2 -> tab.text = getString(R.string.tab_label3)
              }
          }.attach()

    }

}
