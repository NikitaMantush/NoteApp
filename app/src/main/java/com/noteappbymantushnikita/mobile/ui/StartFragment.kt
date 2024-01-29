package com.noteappbymantushnikita.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.databinding.FragmentStartBinding

class StartFragment: Fragment() {

    private var binding: FragmentStartBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.mainLoginTitle?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, LogInFragment())
                .addToBackStack(LogInFragment.TAG)
                .commit()
        }
        binding?.discoverPlatformButton?.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.container, TutorialFragment())
                .addToBackStack(TutorialFragment.TAG)
                .commit()
        }
    }
}