package com.madhav.culinaryfood.core.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatingModel(
    val rating: Double,
    val comment: String,
): Parcelable