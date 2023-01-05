package com.sun.android.contact_provider


import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.R
import com.sun.android.databinding.ContactChildBinding

class ContactAdapter(items: List<ContactDTO>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(){
    var list: List<ContactDTO> = items

    class ViewHolder(private val binding: ContactChildBinding): RecyclerView.ViewHolder(binding.root){
        private var contact: ContactDTO? = null
        init {
            binding.imageButtonCall.setOnClickListener {
                contact?.let {
                    val call = Intent(Intent.ACTION_DIAL)
                    call.data = Uri.parse("tel:${it.number}")
                    binding.root.context.startActivity(call)
                }
            }
        }
        fun bind(items: ContactDTO) {
            contact = items
            binding.textName.text = items.name
            binding.textNumber.text = items.number
            if (items.image != null){
                binding.ivProfile.setImageBitmap(items.image)
            }else {
                binding.ivProfile.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.mipmap.ic_launcher_round))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactChildBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}
