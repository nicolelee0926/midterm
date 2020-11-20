package com.nicole.publisher

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat

@BindingAdapter("timeLongToString")
fun showTimeFormat(textView: TextView, long: Long?){
    long?.let {
        val date = java.util.Date(it)
        val df2 = SimpleDateFormat("yyyy.dd.MM HH:mm")
        val dateText: String = df2.format(date)
        textView.text = dateText
    }
}