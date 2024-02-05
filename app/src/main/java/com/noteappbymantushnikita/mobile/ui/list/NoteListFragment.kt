package com.noteappbymantushnikita.mobile.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.databinding.FragmentNoteListBinding
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.ui.note.AddNoteFragment
import com.noteappbymantushnikita.mobile.ui.LogInFragment
import com.noteappbymantushnikita.mobile.ui.list.adapter.NoteListAdapter

class NoteListFragment : Fragment() {

    private var binding: FragmentNoteListBinding? = null

    private val viewModel: NoteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {

            logoutButton.setOnClickListener {
                parentFragmentManager.beginTransaction().replace(R.id.container, LogInFragment())
                    .addToBackStack(LogInFragment.TAG)
                    .commit()
            }

            addNoteButton.setOnClickListener {
                parentFragmentManager.beginTransaction().replace(R.id.container, AddNoteFragment())
                    .addToBackStack(AddNoteFragment.TAG)
                    .commit()
            }

        }
        viewModel.listNote.observe(viewLifecycleOwner) { listNote ->
            setListNote(listNote)
        }
        viewModel.loadListNote()
    }

    private fun setListNote(listNote: ArrayList<Note>) {
        binding?.recyclerView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = NoteListAdapter(
                    onClick = { noteTitle ->
                        Toast.makeText(requireActivity(), noteTitle, Toast.LENGTH_LONG).show()
                    },
                    onDeleteNoteSelected = { note ->
                        viewModel.deleteNote(note)
                        viewModel.loadListNote()
                    })
            }
            (adapter as? NoteListAdapter)?.submitList(listNote)
        }
    }

    companion object {
        const val TAG = "NoteListFragment"
    }
}