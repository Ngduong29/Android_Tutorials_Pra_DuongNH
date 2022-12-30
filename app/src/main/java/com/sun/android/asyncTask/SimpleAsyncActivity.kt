package com.sun.android.asyncTask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.sun.android.R
import com.sun.android.databinding.ActivitySimpleAsyncBinding
import java.util.Random
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SimpleAsyncActivity : AppCompatActivity() {
    val binding by lazy { ActivitySimpleAsyncBinding.inflate(layoutInflater) }
    private var TEXT_STAGE: String = "currentText"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(savedInstanceState != null){
            binding.textView1.setText(savedInstanceState.getString(TEXT_STAGE))
        }

        val service: ExecutorService = Executors.newSingleThreadExecutor()
        binding.button.setOnClickListener {
            binding.textView1.setText(R.string.napping)
            binding.progressBarAsync.progress = 0
            service.execute {

                val r = Random()
                val n: Int = r.nextInt(11)
                val s: Int = n * 200
                try {
                    Thread.sleep(s.toLong())
                }catch (e: InterruptedException){
                    e.printStackTrace()
                }
                for(i in 0..100){
                    SystemClock.sleep((s/100).toLong())
                    runOnUiThread {
                        binding.progressBarAsync.progress = i
                    }

            }
                runOnUiThread {
                        binding.textView1.setText(getString(R.string.progress,s))
                }

            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_STAGE, binding.textView1.text.toString())
    }

}
