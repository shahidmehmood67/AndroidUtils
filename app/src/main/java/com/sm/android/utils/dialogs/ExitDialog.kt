package com.sm.android.utils.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sm.android.utils.databinding.DialogExitBinding
import com.sm.android.utils.utils.toast


class ExitDialog : DialogFragment() {

        private lateinit var exitDialogLayoutBinding: DialogExitBinding


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

            exitDialogLayoutBinding = DialogExitBinding.inflate(inflater, container, false)
            with(exitDialogLayoutBinding)
            {

                ivBtnNegative.setOnClickListener {
                    requireActivity().toast{
                        "Cancel"
                    }
                     dismiss()
                 }

                ivBtnPositive.setOnClickListener {
                    requireActivity().toast{
                        "Exit"
                    }
                    dismiss()
//                     requireActivity().finish()
//                     requireActivity().finishAffinity()
//                    exitProcess(0)
//                    System.exit(0)
                 }

            }

            return exitDialogLayoutBinding.root
        }


    }
