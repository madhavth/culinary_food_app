package com.madhav.culinaryfood.core.data.helpers

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class ShareDataHelper {
    fun sharePlainText(context: Context, text: String) {
        try {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error occurred while sharing data", Toast.LENGTH_SHORT).show()
        }
    }
}