package com.boriyevfayz.my1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.boriyevfayz.my1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var _binding: ActivityMainBinding
    private val mBinding: ActivityMainBinding get() = _binding
    private var input: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        input = mBinding.tvWorking
        mBinding.clear.setOnClickListener {
            mBinding.tvWorking.text = ""
            mBinding.tvResult.text = ""
        }
        initNumPad()

    }

    private fun initNumPad() {
        mBinding.cvNum0.setOnClickListener(this)
        mBinding.cvNum1.setOnClickListener(this)
        mBinding.cvNum2.setOnClickListener(this)
        mBinding.cvNum3.setOnClickListener(this)
        mBinding.cvNum4.setOnClickListener(this)
        mBinding.cvNum5.setOnClickListener(this)
        mBinding.cvNum6.setOnClickListener(this)
        mBinding.cvNum7.setOnClickListener(this)
        mBinding.cvNum8.setOnClickListener(this)
        mBinding.cvNum9.setOnClickListener(this)
        mBinding.cvPoint.setOnClickListener(this)
    }

    private fun sum() {

    }

    private fun getNumb() {
        val nums = mBinding.tvWorking.text.split("")
        Log.d("TAG", "getNums: $nums ")
    }

    private fun result() {
    }


    override fun onClick(view: View) {

        when (view.id) {
            mBinding.cvNum0.id -> {
                when {
                    input!!.text.isNullOrEmpty() -> input!!.text = "0"
                    input!!.text[0] == '0' -> {
                    }
                    else -> {
                        val tmp = StringBuilder()
                        tmp.append(input!!.text)
                        tmp.append(0)
                        input!!.text = tmp.toString()
                    }
                }
            }
            mBinding.cvNum1.id -> pastNumb(1.toString(), input!!)
            mBinding.cvNum2.id -> pastNumb(2.toString(), input!!)
            mBinding.cvNum3.id -> pastNumb(3.toString(), input!!)
            mBinding.cvNum4.id -> pastNumb(4.toString(), input!!)
            mBinding.cvNum5.id -> pastNumb(5.toString(), input!!)
            mBinding.cvNum6.id -> pastNumb(6.toString(), input!!)
            mBinding.cvNum7.id -> pastNumb(7.toString(), input!!)
            mBinding.cvNum8.id -> pastNumb(8.toString(), input!!)
            mBinding.cvNum9.id -> pastNumb(9.toString(), input!!)
            mBinding.cvPoint.id -> {
                if (input!!.text.isNullOrEmpty()) input!!.text = "0."
                else if (input!!.text[0] == '0') {
                    input!!.text = "0."
                } else {
                    val tmp = StringBuilder()
                    tmp.append(input!!.text)
                    tmp.append('.')
                    input!!.text = tmp.toString()
                }
            }
            mBinding.cvPlus.id -> {
                pasteSymbol(resources.getString(R.string.sum), input)
            }

        }

    }

    private fun pastNumb(num: String, input: TextView) {
        if (input.text.isNullOrEmpty()) input.text = num
        else if ((input.text[0] == '0' && input.text[1] != '.') || (input.text[0] == '-' && input.text[0] != '0' && input.text[2] != '.')) {
            input.text = num
        } else {
            val tmp = StringBuilder()
            tmp.append(input.text)
            tmp.append(num)
            input.text = tmp.toString()
        }
    }

}