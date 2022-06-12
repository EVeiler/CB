package com.kamil.challenge3.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Identity(
    val name: String? = null,
    val usia: Int? = null,
    val alamat: String? = null,
    val pekerjaan: String? = null
) : Parcelable
