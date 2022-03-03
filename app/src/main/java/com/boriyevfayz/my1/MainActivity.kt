package com.boriyevfayz.my1

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.boriyevfayz.my1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var _biding: ActivityMainBinding
    private val mBinding get() = _biding

    private var canAddOperation =false
    private var canAddDemical = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)



    }

    fun zeroBtn(view: View?) {
        if (view is Button)
        {
            workingtv.append(view.text)
        }
    }



    fun oneBtn(view: View?) {
        if (view is Button)
        {
            workingtv.append(view.text)
        }
    }

    fun twoBtn(view: View?) {
        if (view is Button)
        {
            workingtv.append(view.text)
        }
    }

    fun threeBtn(view: View?) {
        if (view is Button)
        {
            workingtv.append(view.text)
        }
    }

    fun fourBtn(view: View?) {
        if (view is Button)
        {
            workingtv.append(view.text)
        }
    }

    fun fiveBtn(view: View?) {
        if (view is Button)
        {
            workingtv.append(view.text)
        }
    }

    fun sixBtn(view: View?) {
        if (view is Button)
        {
            workingtv.append(view.text)
        }
    }

    fun sevenBtn(view: View?) {
        if (view is Button)
        {
            workingtv.append(view.text)
        }
    }

    fun eightBtn(view: View?) {
        if (view is Button)
        {
            workingtv.append(view.text)
        }
    }

    fun nineBtn(view: View?) {
        if (view is Button)
        {
            workingtv.append(view.text)
            canAddOperation = true
        }
    }

    fun plusBtn(view: View?) {
        if (view is Button && canAddOperation)
        {
            workingtv.append(view.text)
            canAddOperation = false
            canAddDemical = true
        }
    }

    fun minusBtn(view: View?) {

    }

    fun multiplyBtn(view: View?) {

    }

    fun divideBtn(view: View?) {

    }

    fun percentBtn(view: View?) {

    }

    fun clearBtn(view: View) {

        mBinding.workingtv.text = "" as Editable?
        mBinding.answertv.text = "" as Editable?
    }

    fun bracketsBtn(view: View) {
        val length = mBinding.workingtv.length()
        if(length > 0)
            mBinding.workingtv.text = mBinding.workingtv.text.subSequence(0,length - 1) as Editable?
    }

    fun pointBtn(view: View?) {
        if (canAddDemical)
            mBinding.workingtv.append(view.text)
        canAddDemical

    }

    fun equalBtn(view: View?) {
        mBinding.answertv.text = calculateResults()
    }

    private fun calculateResults(): String {
        val digitsOperators = digitsOperators()
        if(digitsOperators.isEmpty()) return ""
        val timesDivision = timesDivisionCalculate(digitsOperators)
        if(timesDivision.isEmpty()) return ""
        val result = addSubtractCalculate(timesDivision)
        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>):Float {
        var result = passedList[0] as Float
        for(i in passedList.indices){
            if(passedList[i] is Char && i!= passedList.lastIndex)
            {
                val operator = passedList[i]
                val nextDigit = passedList[i+1] as Float
                if(operator == '+')
                    result += nextDigit
                if(operator == '-')
                    result -= nextDigit
            }

        }
        return result
    }

    private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any> {
        var list =passedList
        while (list.contains('x')||list.contains("/")){
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any> {
        val  newList = mutableListOf<Any>()
        var restartIndex = passedList.size
        for(i in passedList.indices){
            if (passedList[i] is Char && i!=passedList.lastIndex && i<restartIndex){
                val operator = passedList[i]
                val prevDigit = passedList[i-1] as Float
                val nextDigit = passedList[i+1] as Float
                when(operator){
                    'x'->{
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/'->{
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else->{
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
                if (i>restartIndex)
                    newList.add(passedList[i])

            }
        }
        return newList
    }


    fun pamBtn(view: View?) {

    }

    fun backspaceBtn(view: View?) {

    }
    private fun digitsOperators(): MutableList<Any>{
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for(character in workingtv.text){
            if (character.isDigit() || character == ".")
                currentDigit += character
            else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(character)
            }
        }
        if(currentDigit != "")
            list.add(currentDigit.toFloat())
        return list
    }
}
