package com.example.submission

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dog(
    val name: String,
    val age: Int,
    val breed: String,
    val gender: String,
    val activityLevel: String,
    val size: String,
    val personality: String,
    val description: String,
    val photo: Int
) : Parcelable
