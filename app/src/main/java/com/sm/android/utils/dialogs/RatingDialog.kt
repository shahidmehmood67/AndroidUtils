package com.sm.android.utils.dialogs

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.DialogFragment
import com.sm.android.utils.R
import com.sm.android.utils.databinding.DialogRateusBinding
import com.sm.android.utils.utils.toast


class RatingDialog : DialogFragment() {

    private var isRating = false
    private lateinit var rateUsDialogLayoutBinding: DialogRateusBinding


    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rateUsDialogLayoutBinding = DialogRateusBinding.inflate(inflater, container, false)
        with(rateUsDialogLayoutBinding)
        {

            negativeBtn.setOnClickListener {
                requireActivity().toast{
                    "Cancel"
                }
                dismiss()
            }

            positiveBtn.setOnClickListener {
                val rating = ratingBar.rating.toInt()
                if (rating <= 3) {
                    requireContext().composeEmail()
                } else {
                    requireContext().rateUs()
                }
                dismiss()
            }

        }

        return rateUsDialogLayoutBinding.root
    }


    fun Context.rateUs() {
        try {
            val feedss =
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            val rate = Intent(Intent.ACTION_VIEW, feedss)
            startActivity(rate)
        } catch (e: Exception) {
        }
    }

    fun Context.composeEmail() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:email@gmail.com") // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "")
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            startActivity(intent)

        } catch (e: Exception) {
        }
    }


}



