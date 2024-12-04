package com.example.recipebook.base

import androidx.activity.addCallback
import androidx.fragment.app.Fragment

abstract class RootFragment : Fragment() {

    override fun onStart() {
        super.onStart()

        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }
    }
}
