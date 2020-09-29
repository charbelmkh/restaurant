package com.domain.restaurant.core.ui.views

import android.view.View


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}
fun View.isVisible() :Boolean{
   return visibility == View.VISIBLE
}
fun View.setVisible(visible:Boolean){
    visibility=   if (visible) View.VISIBLE else View.INVISIBLE

}

