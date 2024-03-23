package com.noteappbymantushnikita.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.databinding.FragmentMainBinding
import com.noteappbymantushnikita.mobile.ui.list.NoteListFragment

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.findFragmentById(R.id.bottom_nav)?.findNavController()
            ?.let { navController ->
                binding?.bottomNavigationView?.setupWithNavController(
                    navController
                )
            }
    }
}