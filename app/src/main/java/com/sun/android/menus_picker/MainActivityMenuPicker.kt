
package com.sun.android.menus_picker

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sun.android.EXTRA_MESSEAGE
import com.sun.android.R
import com.sun.android.databinding.ActivityMainMenuPickerBinding



class MainActivityMenuPicker : AppCompatActivity() {
    var mOrderMessage: String = ""
    val binding by lazy { ActivityMainMenuPickerBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        when(id){
            R.id.action_order -> {
                var intent = Intent (this, OrderActivity::class.java )
                intent.putExtra(EXTRA_MESSEAGE, mOrderMessage)
                startActivity(intent)
                return true
            }
            R.id.action_status -> {
                var intent = Intent (this, AlertActivity::class.java )
                startActivity(intent)
                return true
            }
            R.id.action_favorites -> {
                var intent = Intent (this, PickDateActivity::class.java )
                startActivity(intent)
                return true
            }
            R.id.action_contact -> {
                displayToast(getString(R.string.action_contact_message))
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun displayToast (message: String){
         Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
