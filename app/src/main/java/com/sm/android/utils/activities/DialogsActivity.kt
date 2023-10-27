package com.sm.android.utils.activities

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.Window
import android.view.WindowManager
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sm.android.utils.databinding.ActivityDialogsBinding
import com.sm.android.utils.databinding.DialogBottom4Binding
import com.sm.android.utils.databinding.DialogSettingsBinding
import com.sm.android.utils.dialogs.ExitDialog
import com.sm.android.utils.dialogs.MenuBottomSheetDialog
import com.sm.android.utils.dialogs.RatingDialog
import com.sm.android.utils.utils.toast
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner

class DialogsActivity : AppCompatActivity()   , MenuBottomSheetDialog.ItemClickListener {

    private lateinit var binding: ActivityDialogsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDialogJava.setOnClickListener{
            startActivity(Intent(this, DialogsJavaActivity::class.java))
        }

//        Dialog
        binding.btnDialog1.setOnClickListener{
            val dialog = ExitDialog()
            dialog.show(this.supportFragmentManager, "exitdialog")
        }
        binding.btnDialog2.setOnClickListener{
            val dialog = RatingDialog()
            dialog.show(this.supportFragmentManager, "ratingdialog")
        }
        binding.btnDialog3.setOnClickListener{
            showPermissionSettingDialog3("Storage")
        }
        binding.btnDialog4.setOnClickListener{
            showRatingDialog4()
        }
        binding.btnDialog5.setOnClickListener{
            callbackDialog5()
        }

//        AlertDialog
        binding.btnDialogAlert1.setOnClickListener{
            showBottomAlertDialog1()
        }

//        BottomSheetDialogFragment
        binding.btnDialogBSheet1.setOnClickListener{
            supportFragmentManager.let {
                MenuBottomSheetDialog.newInstance(Bundle()).apply {
                    show(it, tag)
                }
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

    private fun showRatingDialog4() {
        val dialog  = Dialog(this@DialogsActivity)
        try {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        } catch (ne:NullPointerException) {
        } catch (e:Exception) {
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(com.sm.android.utils.R.layout.dialog_rateus2)
        val submit: TextView = dialog.findViewById(com.sm.android.utils.R.id.submit)
        val cancel: TextView = dialog.findViewById(com.sm.android.utils.R.id.cancelBtn)
        val ratingBar: RatingBar = dialog.findViewById(com.sm.android.utils.R.id.rating_bar)
        ratingBar.max = 5
        ratingBar.numStars = 5

        cancel.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })

        submit.setOnClickListener(View.OnClickListener {
            if (ratingBar.rating <= 0) {
                this.toast {
                    "rating"
                }
                return@OnClickListener
            } else if (ratingBar.rating <= 3) {
                this.toast {
                    "rating"
                }
            } else {
                this.toast {
                    "rating"
                }
            }
            dialog.dismiss()
        })
        dialog.show()
    }

    private fun callbackDialog5(){
        MaterialDialog(this).show {
            title(com.sm.android.utils.R.string.useGoogleLocationServices)
            message(com.sm.android.utils.R.string.useGoogleLocationServicesPrompt)
            positiveButton(com.sm.android.utils.R.string.agree)
            negativeButton(com.sm.android.utils.R.string.disagree)
            neutralButton(com.sm.android.utils.R.string.more_info)
            lifecycleOwner(this@DialogsActivity)
        }
    }

    private fun showBottomAlertDialog1() {
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



    override fun onBottomSheetItemClick(item: String) {
        when(item){
            "Share"->{
                this.toast {
                 "$item"
                }
            }
            "MoreApps"->{
                 this.toast {
                 "$item"
                }
            }
            "Policy"->{
                 this.toast {
                 "$item"
                }
            }
            "Rate"->{
                 this.toast {
                 "$item"
                }
            }
            "Exit"->{
                this.toast {
                    "$item"
                }
            }
            else->{
                //Handle data
                this.toast {
                    "$item"
                }
            }
        }
    }
}