package com.noteappbymantushnikita.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.databinding.FragmentMainBinding
import com.noteappbymantushnikita.mobile.ui.list.NoteListFragment
import com.noteappbymantushnikita.mobile.util.openFragmentWithBottomMenu

class MainFragment: Fragment() {

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
        childFragmentManager
            .openFragmentWithBottomMenu(NoteListFragment())
        binding?.bottomNavigationView?.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home ->{
                    childFragmentManager
                        .openFragmentWithBottomMenu(NoteListFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.search ->{
                    childFragmentManager
                        .openFragmentWithBottomMenu(SearchFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.profile ->{
                    childFragmentManager
                        .openFragmentWithBottomMenu(ProfileFragment())
                    return@setOnItemSelectedListener true
                }
                else ->{
                    return@setOnItemSelectedListener false
                }
            }
        }
    }
}