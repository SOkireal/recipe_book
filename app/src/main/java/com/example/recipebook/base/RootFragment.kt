package com.example.recipebook.base

import android.util.Log
import androidx.activity.addCallback
import androidx.fragment.app.Fragment

abstract class RootFragment : Fragment() {

    override fun onStart() {
        super.onStart()

        requireActivity().onBackPressedDispatcher.addCallback {
            Log.d("bbb", this.toString())
            requireActivity().finish()
        }
    }
}
