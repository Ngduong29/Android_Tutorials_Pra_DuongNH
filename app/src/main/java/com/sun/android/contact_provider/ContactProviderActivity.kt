package com.sun.android.contact_provider

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.android.databinding.ActivityContactProviderBinding

class ContactProviderActivity : AppCompatActivity() {
    val binding by lazy { ActivityContactProviderBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.contactList.layoutManager = LinearLayoutManager(this)
        val contactList: MutableList<ContactDTO> = ArrayList()
        val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
        if (contacts != null) {
            while (contacts.moveToNext()){
                val name = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                var obj = ContactDTO(name,number,image = null)
                val photo_uri = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                if(photo_uri != null){
                    val image = MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(photo_uri))
                    obj = obj.copy(image = image)
                }
                contactList.add(obj)
            }
            binding.contactList.adapter = ContactAdapter(contactList)
            contacts.close()
        }

    }

}
