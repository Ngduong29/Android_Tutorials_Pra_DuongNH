package com.sun.android.menus_picker

import android.app.Dialog
import android.text.format.DateFormat
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.annotation.NonNull
import androidx.fragment.app.DialogFragment
import java.util.*


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onTimeSet(timePicker: TimePicker?, hourOfDay: Int, minute: Int) {
        var activity = activity as? PickDateActivity
        activity?.processTimePickerResult(hour = hourOfDay, minute = minute)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(requireActivity(), this, hour, minute, DateFormat.is24HourFormat(requireActivity()))
    }
}
