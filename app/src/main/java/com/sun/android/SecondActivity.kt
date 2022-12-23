package com.sun.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sun.android.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    val binding by lazy { ActivitySecondBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var message = intent.getStringExtra(EXTRA_MESSEAGE )
            binding.textMessage.setText(message)

        binding.buttonSecond.setOnClickListener {
            val replyIntent = Intent(this, MainActivity::class.java)
            val reply = binding.editTextSecond.text.toString()
            replyIntent.putExtra(EXTRA_REPLY, reply)
            setResult(RESULT_OK, replyIntent)
            finish()
        }
    }
}
