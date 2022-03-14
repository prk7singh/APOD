package com.example.apod.ui.search

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.apod.R
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireActivity(),this,year,month,day)
        //disable future dates
        datePicker.datePicker.maxDate = System.currentTimeMillis()
        return datePicker
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
        val dateBuilder = StringBuilder()
        dateBuilder.append(year)
        dateBuilder.append("-")
        dateBuilder.append(month+1)
        dateBuilder.append("-")
        dateBuilder.append(day)
        val date = dateBuilder.toString()
        Log.d("TAG","date picked is $date")
        val bundle = Bundle()
        bundle.putString("date",date)
        findNavController().navigate(R.id.navigation_home,bundle)
    }
}