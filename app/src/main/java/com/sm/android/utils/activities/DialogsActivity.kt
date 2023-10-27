package com.sm.android.utils.activities

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.Window
import android.view.WindowManager
import com.sm.android.utils.databinding.ActivityDialogsBinding
import com.sm.android.utils.databinding.DialogBottom4Binding
import com.sm.android.utils.databinding.DialogSettingsBinding
import com.sm.android.utils.dialogs.ExitDialog
import com.sm.android.utils.dialogs.RatingDialog
import com.sm.android.utils.utils.AppConstants.FLASHING_TYPE_CONTINUOUS
import com.sm.android.utils.utils.AppConstants.FLASHING_TYPE_RHYTHM
import com.sm.android.utils.utils.SharedPrefConstants

class DialogsActivity : AppCompatActivity()  , OnClickListener {

    private lateinit var binding: ActivityDialogsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDialogJava.setOnClickListener(this)
        binding.btnDialog1.setOnClickListener(this)
        binding.btnDialog2.setOnClickListener(this)
        binding.btnDialog3.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v){
            binding.btnDialogJava ->{
                startActivity(Intent(this, DialogsJavaActivity::class.java))
            }
            binding.btnDialog1 ->{
                val dialog = ExitDialog()
                dialog.show(this.supportFragmentManager, "exitdialog")
            }
            binding.btnDialog2 ->{
                val dialog = RatingDialog()
                dialog.show(this.supportFragmentManager, "ratingdialog")
            }
            binding.btnDialog3 ->{
                showPermissionSettingDialog3("")
            }
            binding.btnDialog4 ->{
                showBottomDialog4()
            }


        }


    }

    private fun showPermissionSettingDialog3(value: String) {
        val dialog = Dialog(this, R.style.ThemeOverlay)
        val dialogBinding = DialogSettingsBinding.inflate(LayoutInflater.from(this))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(dialogBinding.root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val appneedsText = String.format(
            getString(com.sm.android.utils.R.string.thisappneeds),
            value
        )
        val instructionText = String.format(
            getString(com.sm.android.utils.R.string.instruction3),
            value
        )

        dialogBinding.tvDisable.text = appneedsText
        dialogBinding.tvInstruction3.text = instructionText

        dialogBinding.tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.tvSettings.setOnClickListener {
            openAppSettings()
        }

        dialog.show()
    }

    private fun showBottomDialog4() {
        val dialog = AlertDialog.Builder(this)
        val dialogBinding = DialogBottom4Binding.inflate(LayoutInflater.from(this))
        dialog.setView(dialogBinding.root)
        val alertDialog = dialog.create()
        alertDialog.window?.decorView?.setPadding(0, 0, 0, 0)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(alertDialog.window?.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        alertDialog.window?.attributes = layoutParams

//        val tmode = prefdefault.getString(SharedPrefConstants.FLASHING_TYPE, FLASHING_TYPE_CONTINUOUS)

/*        if (tmode == FLASHING_TYPE_CONTINUOUS){
            dialogBinding.rBtnContiousFlash.isChecked = true
        }
        else if (tmode == FLASHING_TYPE_RHYTHM){
            dialogBinding.rBtnSos.isChecked = true
        }*/

        dialogBinding.ivCancel.setOnClickListener{
            alertDialog.dismiss()
        }

        dialogBinding.rBtnContiousFlash.setOnClickListener {
            dialogBinding.rBtnSos.isChecked = false
            dialogBinding.rBtnMusic.isChecked = false
/*            prefdefault.edit().apply {
                putString(SharedPrefConstants.FLASHING_TYPE, FLASHING_TYPE_CONTINUOUS)
            }.apply()*/
            alertDialog.dismiss()
        }

        dialogBinding.rBtnSos.setOnClickListener {
            dialogBinding.rBtnContiousFlash.isChecked = false
            dialogBinding.rBtnMusic.isChecked = false

/*            prefdefault.edit().apply {
                putString(SharedPrefConstants.FLASHING_TYPE, FLASHING_TYPE_RHYTHM)
            }.apply()*/
            alertDialog.dismiss()
        }


        alertDialog.show()
    }

    private fun openAppSettings() {
        try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", this.packageName, null)
            intent.data = uri
            startActivity(intent)
        } catch (e: Exception) {
        }
    }


}