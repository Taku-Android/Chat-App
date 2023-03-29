package com.example.chatapp.ui.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

// in kotlin  if u make inhertance the parent must be open class
abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel>
    : AppCompatActivity() , BaseNavigator {

    lateinit var viewBinding: VB
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, getLayoutId())


        viewModel = initViewModel()

    }


    abstract fun getLayoutId(): Int

    abstract fun initViewModel(): VM


    var alertDialog : AlertDialog? = null

    var progressDialog : ProgressDialog? = null

    override fun showLoading(message: String) {

        progressDialog = ProgressDialog(this  )
        progressDialog?.setMessage(message)
        progressDialog?.show()
    }

    override fun hideDialog() {
        progressDialog?.dismiss()
        alertDialog?.dismiss()
        alertDialog = null
        progressDialog = null
    }

    override fun showMessage(
        message: String,
        posActionTitle: String? ,
        posAction: OnDialogeClickListener?,
        negActionTitle: String?,
        negAction: OnDialogeClickListener?
    ) {

        val builder = AlertDialog.Builder(this)
            .setMessage(message)
           if (posActionTitle != null){
               builder.setPositiveButton(posActionTitle ){
                    dialogInterface , i ->
                   posAction?.onClick()
                   dialogInterface.dismiss()
               }.show()
           }
        if (negActionTitle != null){
            builder.setNegativeButton(negActionTitle ){
                    dialogInterface , i ->
                negAction?.onClick()
                dialogInterface.dismiss()
            }.show()
        }


    }

}

interface BaseNavigator {

    fun showLoading(message: String)

    fun hideDialog()

    fun showMessage(message: String ,
                    posActionTitle:String? = null ,
                    posAction:OnDialogeClickListener? = null ,
                    negActionTitle:String? = null ,
                    negAction:OnDialogeClickListener? = null


    )


}


open class BaseViewModel<N:BaseNavigator> : ViewModel(){

    var navigator : N? = null

}