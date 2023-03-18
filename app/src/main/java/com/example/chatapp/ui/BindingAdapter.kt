package com.example.chatapp.ui

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("error")
fun bindErrorOnTextInput(
    textInputLayout: TextInputLayout,
    errorMessage: String?) {

    textInputLayout.error = errorMessage

}