package com.example.age_to_minutes_calculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var textViewSelectedDate: TextView? = null
    private var minutesPassedTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewSelectedDate = findViewById(R.id.textViewSelectedDate)
        val datePickerButton: Button = findViewById(R.id.buttonDatePicker)
        minutesPassedTextView = findViewById(R.id.minutesPassedTextView)

        datePickerButton.setOnClickListener {

            datePicker()

        }
    }
    private fun datePicker(){
        val calendar = java.util.Calendar.getInstance()
        val year = calendar.get(java.util.Calendar.YEAR)
        val month = calendar.get(java.util.Calendar.MONTH)
        val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)
        val DPD = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "The year selected was $selectedYear, the month ${selectedMonth+1}" +
                    " and the day $selectedDayOfMonth", Toast.LENGTH_LONG).show()

            var selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            textViewSelectedDate?.text = selectedDate

            val simpleDateFormatVal = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            var theDate = simpleDateFormatVal.parse(selectedDate)

            theDate?.let {
                val selectedDateInMinutes = theDate.time / 60000

                val currentDate = simpleDateFormatVal.parse(simpleDateFormatVal.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 60000

                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    minutesPassedTextView?.text = differenceInMinutes.toString()
                }

            }


        },
            year, month, day)

        DPD.datePicker.maxDate = System.currentTimeMillis() - 86400000
        DPD.show()









    }
}

