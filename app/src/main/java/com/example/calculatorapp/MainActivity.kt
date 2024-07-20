package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var tvinput: TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets

            tvinput = findViewById(R.id.tvinput)
        }

        fun onDigit(view: View){

        tvinput?.append((view as Button).text)
            lastNumeric = true
            lastDot = false

            
        }
    fun onClear(view: View){
        tvinput?.text = ""
    }
    fun onDecimal(view: View){
        if(lastNumeric && !lastDot ){
            tvinput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun isOperatorAdded(value:String): Boolean{
        return if(value.startsWith("-")){
            false

        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
    fun onOperator(view: View){
        tvinput?.text?.let{

            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvinput?.append((view as Button).text)
                lastNumeric= false
                lastDot = false
            }

        }

    }
    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = tvinput?.text.toString()
            var prefix = ""
            try {

                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezeroafterDot((one.toDouble() - two.toDouble()).toString())

                }else if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezeroafterDot((one.toDouble() - two.toDouble()).toString())

                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezeroafterDot((one.toDouble() + two.toDouble()).toString())

                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezeroafterDot((one.toDouble() * two.toDouble()).toString())

                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezeroafterDot((one.toDouble() / two.toDouble()).toString())

                }else if(tvValue.contains("%")){
                    val splitValue = tvValue.split("%")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezeroafterDot((one.toDouble() % two.toDouble()).toString())

                }
            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removezeroafterDot(result: String) : String{
        var value = result
        if(result.contains("0")){
            value = result.substring(0,result.length-2)
        }
        return value
    }


    fun onDelete(view: View) {
        tvinput?.text?.let {
            if (it.isNotEmpty()) {
                tvinput?.text = it.substring(0, it.length - 1)
                // here substring property is used
                // here all the substing shown form index 0 to lenght -1 of the string
                // at last we able to see all the character except last one
            }
        }
    }
}
