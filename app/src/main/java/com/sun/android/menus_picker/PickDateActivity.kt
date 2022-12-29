package com.sun.android.menus_picker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.sun.android.R
import com.sun.android.databinding.ActivityPickDateBinding

class PickDateActivity : AppCompatActivity() {
    val binding by lazy { ActivityPickDateBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonDate.setOnClickListener{
            var newFragmentDate = DatePickerFragment()
            newFragmentDate.show(supportFragmentManager,"datePicker")
        }
        binding.buttonTime.setOnClickListener{
            var newFragmentTime = TimePickerFragment()
            newFragmentTime.show(supportFragmentManager,"timePicker")
        }

    }
    fun processDatePickerResult(year: Int, month: Int, day: Int){
        Toast.makeText(this,  getString(R.string.dateMessage,day,month+1,year), Toast.LENGTH_SHORT).show()
    }

    fun processTimePickerResult(hour: Int, minute: Int){
        Toast.makeText(this,  getString(R.string.timeMessage,hour,minute), Toast.LENGTH_SHORT).show()
    }
}
