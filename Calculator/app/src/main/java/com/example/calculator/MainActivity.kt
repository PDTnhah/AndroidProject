package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var bt0: Button
    private lateinit var bt1: Button
    private lateinit var bt2: Button
    private lateinit var bt3: Button
    private lateinit var bt4: Button
    private lateinit var bt5: Button
    private lateinit var bt6: Button
    private lateinit var bt7: Button
    private lateinit var bt8: Button
    private lateinit var bt9: Button
    private lateinit var bang: Button
    private lateinit var cong: Button
    private lateinit var tru: Button
    private lateinit var nhan: Button
    private lateinit var chia: Button
    private lateinit var btC: Button
    private lateinit var btCE: Button
    private lateinit var btcham: Button
    private lateinit var btBS: Button
    private lateinit var btcongtru: Button
    private lateinit var mainscreen: TextView
    private lateinit var secondscreen: TextView

    private var tinhtoan: String = ""
    private var pheptoan: String = ""
    private var checkDot: Boolean = false
    private var checkBang: Boolean = false
    private var so1: Double = 0.0
    private var so2: Double = 0.0
    private var ketqua: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        anhxa()

        btC.setOnClickListener {
            secondscreen.text = ""
            mainscreen.text = ""
            tinhtoan = ""
            pheptoan = ""
            checkBang = false
            checkDot = false
        }

        btCE.setOnClickListener {
            if (!checkBang) {
                mainscreen.text = ""
            }
        }

        btBS.setOnClickListener {
            if (!checkBang) {
                val valText = mainscreen.text.toString()
                if (valText.isNotEmpty()) {
                    mainscreen.text = valText.dropLast(1)
                }
            }
        }

        btcongtru.setOnClickListener {
            if (!checkBang) {
                val valText = mainscreen.text.toString()
                if (valText.isNotEmpty()) {
                    val currentValue = valText.toDouble()
                    mainscreen.text = (-currentValue).toString()
                }
            }
        }

        bt0.setOnClickListener { appendNumber("0") }
        bt1.setOnClickListener { appendNumber("1") }
        bt2.setOnClickListener { appendNumber("2") }
        bt3.setOnClickListener { appendNumber("3") }
        bt4.setOnClickListener { appendNumber("4") }
        bt5.setOnClickListener { appendNumber("5") }
        bt6.setOnClickListener { appendNumber("6") }
        bt7.setOnClickListener { appendNumber("7") }
        bt8.setOnClickListener { appendNumber("8") }
        bt9.setOnClickListener { appendNumber("9") }

        btcham.setOnClickListener {
            if (!checkBang && !checkDot) {
                tinhtoan = mainscreen.text.toString()
                mainscreen.text = "$tinhtoan."
                checkDot = true
            }
        }

        cong.setOnClickListener { setOperation("+", " + ") }
        tru.setOnClickListener { setOperation("-", " - ") }
        nhan.setOnClickListener { setOperation("x", " x ") }
        chia.setOnClickListener { setOperation("/", " / ") }

        bang.setOnClickListener {
            if (!checkBang) {
                checkDot = false
                tinhtoan = mainscreen.text.toString()
                so2 = mainscreen.text.toString().toDouble()
                secondscreen.text = secondscreen.text.toString() + mainscreen.text.toString()
                mainscreen.text = ""
                ketqua = when (pheptoan) {
                    "+" -> so1 + so2
                    "-" -> so1 - so2
                    "x" -> so1 * so2
                    "/" -> so1 / so2
                    else -> 0.0
                }
                checkBang = true
                mainscreen.text = ketqua.toString()
                pheptoan = ""
            }
        }
    }

    private fun anhxa() {
        bt0 = findViewById(R.id.bt0)
        bt1 = findViewById(R.id.bt1)
        bt2 = findViewById(R.id.bt2)
        bt3 = findViewById(R.id.bt3)
        bt4 = findViewById(R.id.bt4)
        bt5 = findViewById(R.id.bt5)
        bt6 = findViewById(R.id.bt6)
        bt7 = findViewById(R.id.bt7)
        bt8 = findViewById(R.id.bt8)
        bt9 = findViewById(R.id.bt9)
        bang = findViewById(R.id.bang)
        cong = findViewById(R.id.cong)
        tru = findViewById(R.id.tru)
        nhan = findViewById(R.id.nhan)
        chia = findViewById(R.id.chia)
        btC = findViewById(R.id.btC)
        btCE = findViewById(R.id.btCE)
        btBS = findViewById(R.id.btBS)
        btcongtru = findViewById(R.id.btcongtru)
        btcham = findViewById(R.id.btcham)
        mainscreen = findViewById(R.id.man_hinh)
        secondscreen = findViewById(R.id.man_hinh_phu)
    }

    private fun appendNumber(number: String) {
        if (!checkBang) {
            tinhtoan = mainscreen.text.toString()
            mainscreen.text = tinhtoan + number
        }
    }

    private fun setOperation(operation: String, display: String) {
        if (mainscreen.text.isEmpty()) {
            so1 = 0.0
            secondscreen.text = "0$display"
        } else {
            so1 = mainscreen.text.toString().toDouble()
            secondscreen.text = mainscreen.text.toString() + display
        }
        mainscreen.text = ""
        tinhtoan = ""
        pheptoan = operation
        checkDot = false
        checkBang = false
    }
}