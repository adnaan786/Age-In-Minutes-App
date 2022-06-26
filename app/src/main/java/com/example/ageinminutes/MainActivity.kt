package com.example.ageinminutes

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1:Button=findViewById(R.id.button1)
        button1.setOnClickListener{
            clickDatePicker()
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(){
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val date=myCalendar.get(Calendar.DAY_OF_MONTH)
        val tvselectedDate:TextView=findViewById(R.id.selectedDate)
        val finalTime:TextView=findViewById(R.id.finaltime)
        val dpd=DatePickerDialog(this,{_,Selectedyear,Selectedmonth,Selectedday ->
            val selectedDate="$Selectedday/${Selectedmonth+1}/$Selectedyear"
            tvselectedDate.text=selectedDate

            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            theDate?.let {
                val selectdDateInMinutes=theDate.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currDtotime=currentDate.time/60000
                    val diff=currDtotime-selectdDateInMinutes
                    finalTime.text=diff.toString()
                }
            }
        },year,month,date)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }



}






