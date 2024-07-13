package com.example.qsolver

import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var root1: Double = Double.NaN
        var root2: Double = Double.NaN
        var flag: String = "Root1"

        val rootSpinner : Spinner = findViewById(R.id.rootSpinner)
        var options = arrayOf("Root1","Root2")
        rootSpinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        rootSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = options.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                flag = "Root1"
            }
        }

        val solveBtn = findViewById<Button>(R.id.solveBtn)
        solveBtn.setOnClickListener {
            var a: Double = findViewById<EditText>(R.id.valAText).text.toString().toDouble()
            var b: Double = findViewById<EditText>(R.id.valBText).text.toString().toDouble()
            var c: Double = findViewById<EditText>(R.id.valCText).text.toString().toDouble()

            if(a == 0.0){
                root1 = -c/b
                root2 = Double.NaN
            }
            else{
                var delta: Double = sqrt(b.pow(2) - (4 * a * c))
                root1 = (-b + delta) / (2 * a);
                root2 = (-b - delta) / (2 * a);
            }

            val rootText = findViewById<TextView>(R.id.rootText)
            if(flag == "Root1"){
                if(root1.isNaN()){
                    rootText.text = "NA"
                }
                else {
                    rootText.text = String.format(Locale.US, "%.4f", root1)
                }
            }
            else{
                if(root2.isNaN()){
                    rootText.text = "NA"
                }
                else {
                    rootText.text = String.format(Locale.US, "%.4f", root2)
                }
            }

        }
    }
}