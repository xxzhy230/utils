package com.yqjr.utils.utils

import android.graphics.Color
import android.os.CountDownTimer
import android.widget.Button
//验证码倒计时
class CountDownUtils {
    private var countDownTimer: CountDownTimer? = null

    constructor(button: Button, max: Int, interval: Int,content:String,textColor:String) {
        countDownTimer = object : CountDownTimer((max * 1000).toLong(), (interval * 1000 - 10).toLong()) {
            override fun onTick(time: Long) {
                button.isEnabled = false
                button.text = ((time + 15L) / 1000L).toString()  + "$content"
            }

            override fun onFinish() {
                button.setTextColor(Color.parseColor("$textColor"))
                button.isEnabled = true
                button.text = "重新发送"
            }
        }
    }

    fun start() {
        countDownTimer!!.start()
    }
}