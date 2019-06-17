package com.structure.kotlin.custom

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import android.view.View
import android.view.ViewGroup
import org.jetbrains.annotations.NotNull

/**
 * Created by Himangi on 16/1/19
 */

class BottomDialog(@NotNull context: Context) : BottomSheetDialog(context) {


    override fun setContentView(view: View, params: ViewGroup.LayoutParams?) {
        super.setContentView(view)
    }


    override fun setContentView(view: View) {
        super.setContentView(view)
        (view.parent as View)
            .setBackgroundColor(context.resources.getColor(android.R.color.transparent))

        val layoutParams = (view.parent as View)
            .layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.setMargins(50, 0, 50, 0)
        (view.parent as View).layoutParams = layoutParams
    }

}