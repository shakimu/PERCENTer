package com.example.percenter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etBaseAmount = findViewById<EditText>(R.id.etBaseAmount)
        val etComparedAmount = findViewById<EditText>(R.id.etComparedAmount)
        val etPercentage = findViewById<EditText>(R.id.etPercentage)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val tvError = findViewById<TextView>(R.id.tvError)

        btnCalculate.setOnClickListener {
            val baseAmount = etBaseAmount.text.toString().toDoubleOrNull()
            val comparedAmount = etComparedAmount.text.toString().toDoubleOrNull()
            val percentage = etPercentage.text.toString().toDoubleOrNull()

            if ((baseAmount != null && comparedAmount != null && percentage == null) ||
                (baseAmount != null && comparedAmount == null && percentage != null) ||
                (baseAmount == null && comparedAmount != null && percentage != null)) {
                tvError.visibility = TextView.GONE
                val result = when {
                    baseAmount != null && comparedAmount != null -> (comparedAmount / baseAmount) * 100
                    baseAmount != null && percentage != null -> (baseAmount * percentage) / 100
                    comparedAmount != null && percentage != null -> (comparedAmount * 100) / percentage
                    else -> 0.0
                }
                tvResult.text = "$result"
            } else {
                tvError.visibility = TextView.VISIBLE
                tvError.text = "数を２つ入力してください"
            }
        }

        btnClear.setOnClickListener {
            etBaseAmount.text.clear()
            etComparedAmount.text.clear()
            etPercentage.text.clear()
//            tvResult.text = "結果"
            tvError.visibility = TextView.GONE
        }
    }
}