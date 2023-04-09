package com.example.stopwatch

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
   var x : Int = 25
    var proc_per = 100
    var standerd_time : Long = (x.toLong())*60*1000
    var reman_time =standerd_time
    var myTimer : CountDownTimer? = null
    lateinit var title_tv : TextView
    lateinit var timer_tv: TextView
    lateinit var reset_tv : TextView
    lateinit var btn : Button
    lateinit var prog_bar : ProgressBar
    lateinit var set_time : TextInputEditText
    lateinit var set_btn : Button

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var_imple()
        btn.setOnClickListener {

            if(btn.text.toString()=="Start"){

                timer_on()
                title_tv.setText(R.string.keep)

                btn.setText(R.string.stop)
            }
            else if (btn.text.toString()=="Stop"){
                myTimer?.cancel()

                btn.setText(R.string.start)
                title_tv.setText(R.string.paused)
                standerd_time= reman_time

            }

        }
        reset_tv.setOnClickListener {

            standerd_time = (x.toLong()) * 1000 * 60
            btn.setText(R.string.start)
            reset_time()
            if (title_tv.text.toString() == "Keep Going ....") {
                title_tv.setText(R.string.take_a_pomodoro)

            }

        }

       set_btn.setOnClickListener {
           x=set_time.text.toString().toInt()
            standerd_time=(x.toLong())*1000*60
           reman_time =standerd_time
           update_timer()


       }
    }

    private fun timer_on() {
         myTimer = object : CountDownTimer(standerd_time, 1000) {
            override fun onTick(p0: Long) {
                reman_time = p0
                update_timer()
                prog_bar.progress = ((100 * reman_time.toDouble())/standerd_time.toDouble()).toInt()
                proc_per = prog_bar.progress
            }

            override fun onFinish() {
                reset_time()
            }


        }.start()
    }


    private fun var_imple(){

        title_tv = findViewById(R.id.title_tv)
        timer_tv = findViewById(R.id.timer_tv)
        reset_tv = findViewById(R.id.reset_tv)
        btn = findViewById(R.id.btn)
        prog_bar = findViewById(R.id.prog_bar)
        set_time = findViewById(R.id.set_time_et)
        set_btn = findViewById(R.id.set_btn)
    }
    private fun update_timer(){
        var minute = reman_time.div(1000).div(60)
        var second = reman_time.div(1000)%60
        var format_time = String.format("%02d:%02d",minute,second)
        timer_tv.setText(format_time)

    }
    private fun reset_time(){
        myTimer?.cancel()
        reman_time= standerd_time
        update_timer()
        prog_bar.progress=100


    }

}