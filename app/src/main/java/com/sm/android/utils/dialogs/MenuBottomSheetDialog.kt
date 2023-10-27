package com.sm.android.utils.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sm.android.utils.R


class MenuBottomSheetDialog : BottomSheetDialogFragment() {
    private var view:View?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view =  inflater.inflate(R.layout.bottom_menu, container, false)
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { setupBottomSheet(it) }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.SheetDialog)
        val params = (view.parent as View)
            .layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior
        (view.parent as View).setBackgroundColor(Color.TRANSPARENT)*/
        setUpViews()
    }
    private fun setupBottomSheet(dialogInterface: DialogInterface) {
        val bottomSheetDialog = dialogInterface as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet)
            ?: return
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)
    }
    private fun setUpViews() {
        // We can have cross button on the top right corner for providing elemnet to dismiss the bottom sheet
        view?.findViewById<ImageView>(R.id.iv_close)?.setOnClickListener { dismissAllowingStateLoss() }
        view?.findViewById<AppCompatTextView>(R.id.share_app)?.setOnClickListener {
            dismissAllowingStateLoss()
            mListener?.onBottomSheetItemClick("Share")

        }

        view?.findViewById<AppCompatTextView>(R.id.more_apps)?.setOnClickListener {
            dismissAllowingStateLoss()
            mListener?.onBottomSheetItemClick("MoreApps")

        }
        view?.findViewById<AppCompatTextView>(R.id.policy_app)?.setOnClickListener {
            dismissAllowingStateLoss()
            mListener?.onBottomSheetItemClick("Policy")

        }
        view?.findViewById<AppCompatTextView>(R.id.rate_app)?.setOnClickListener {
            dismissAllowingStateLoss()
            mListener?.onBottomSheetItemClick("Rate")

        }
        view?.findViewById<AppCompatTextView>(R.id.exit_app)?.setOnClickListener {
            dismissAllowingStateLoss()
            mListener?.onBottomSheetItemClick("Exit")

        }
    }

    private var mListener: ItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ItemClickListener) {
            mListener = context as ItemClickListener
        }
        else {
            throw RuntimeException(
                context.toString()
                    .toString() + " must implement ItemClickListener"
            )
        }

    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
    interface ItemClickListener {
        fun onBottomSheetItemClick(item: String)
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): MenuBottomSheetDialog {
            val fragment = MenuBottomSheetDialog()
            fragment.arguments = bundle
            return fragment
        }
    }
}