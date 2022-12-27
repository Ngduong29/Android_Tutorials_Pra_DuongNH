package com.sun.android

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sun.android.databinding.ActivityMainBinding

const val EXTRA_MESSEAGE = "name"
const val EXTRA_REPLY = "exReply"
class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val getReply = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK){
                val resultValue = it.data?.extras?.getString(EXTRA_REPLY)
                    binding.textMessageReply.setText(resultValue)
            }
        }
        binding.buttonMain.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            val message = binding.editTextMain.text.toString()
            intent.putExtra(EXTRA_MESSEAGE , message)
            binding.editTextMain.text = null
            getReply.launch(intent)
        }

    }
}
