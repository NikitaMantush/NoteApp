package com.noteappbymantushnikita.mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.noteappbymantushnikita.mobile.databinding.FragmentNoteListBinding
import com.noteappbymantushnikita.mobile.model.NoteDB
import com.noteappbymantushnikita.mobile.noteAdapter.NoteListAdapter

class NoteListFragment: Fragment() {

    private lateinit var adapter: NoteListAdapter
    private var binding: FragmentNoteListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextThemeWrapper: Context =
            ContextThemeWrapper(requireContext(), R.style.Theme_NoteApp_list)
        val localInflater = inflater.cloneInContext(contextThemeWrapper)
        binding = FragmentNoteListBinding.inflate(localInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.logoutButton?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, LogInFragment())
                .addToBackStack(LogInFragment.TAG)
                .commit()
        }
        binding?.addNoteButton?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, AddNoteFragment())
                .addToBackStack(AddNoteFragment.TAG)
                .commit()
        }
        val recyclerView = binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
        }
        adapter = NoteListAdapter(
            onClick = { noteTitle ->
                Toast.makeText(requireActivity(), noteTitle, Toast.LENGTH_LONG).show()
            },
            onDeleteNoteSelected = { note ->
                NoteDB.noteList.remove(note)
                adapter.notifyDataSetChanged()
            }
        )
        recyclerView?.adapter = adapter
        adapter.submitList(NoteDB.noteList)

    }
    companion object{
        const val TAG = "NoteListFragment"
    }
}