package com.noteappbymantushnikita.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.noteappbymantushnikita.mobile.databinding.FragmentStartBinding
import com.noteappbymantushnikita.mobile.util.openFragment

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
        binding?.run {
            mainLoginTitle.setOnClickListener {
               parentFragmentManager.openFragment(LogInFragment())
            }
            discoverPlatformButton.setOnClickListener{
                parentFragmentManager.openFragment(TutorialFragment())
            }
        }
    }
}