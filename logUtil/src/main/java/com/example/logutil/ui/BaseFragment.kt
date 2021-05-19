package com.example.smartcity_1.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment(val layoutRes: Int) : Fragment() {
    lateinit var mView: View
    lateinit var mContext: Context
    var init = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!this::mView.isInitialized) {
            mView = inflater.inflate(layoutRes, container, false)
        }
        return mView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (init) return
        init = true
        initViews()
    }

    abstract fun initViews()
    fun back() {
        findNavController().navigateUp()
    }

    fun goTo(id: Int, bundle: Bundle? = null) {
        if (bundle == null) {
            findNavController().navigate(id)
        }else
        findNavController().navigate(id, bundle)
    }

    fun setTitle(title: String) {
        (mContext as AppCompatActivity).supportActionBar?.title = title
    }
    fun <T>getA(): T {
        return arguments?.get("A") as T
    }
    fun getB(): Map<String, Any> {
        return arguments?.get("A") as Map<String,Any>
    }
}