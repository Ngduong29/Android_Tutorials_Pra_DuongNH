package com.sun.android.contact_provider

import android.graphics.Bitmap

data class ContactDTO(
    val name: String,
    val number: String,
    val image: Bitmap? = null
)

