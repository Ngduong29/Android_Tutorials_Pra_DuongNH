package com.sun.android.drawables_styles_themes

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.sun.android.R
import com.sun.android.databinding.ActivityMainDrawablesBinding

class MainActivityDrawables : AppCompatActivity() {
    val binding by lazy { ActivityMainDrawablesBinding.inflate(layoutInflater) }
    var mScore1 = 0
    var mScore2 = 0
    val STATE_SCORE_1 = "Team 1 Score"
    val STATE_SCORE_2 = "Team 2 Score"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonDecrease1.setOnClickListener {
            mScore1--
            binding.textScore1.text = mScore1.toString()
        }
        binding.buttonDecrease2.setOnClickListener {
            mScore2--
            binding.textScore2.text= mScore2.toString()
        }
        binding.buttonIncrease1.setOnClickListener {
            mScore1++
            binding.textScore1.text = mScore1.toString()
        }
        binding.buttonIncrease2.setOnClickListener {
            mScore2++
            binding.textScore2.text = mScore2.toString()
        }


        if(savedInstanceState != null){
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1)
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2)

            binding.textScore1.setText(mScore1.toString())
            binding.textScore2.setText(mScore2.toString())
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.night_mode ){
            var nightMode: Int = AppCompatDelegate.getDefaultNightMode()
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES){
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        var nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.day_mode);
        } else {
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.night_mode);
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SCORE_1, mScore1)
        outState.putInt(STATE_SCORE_2, mScore2)
        super.onSaveInstanceState(outState)
    }

}
