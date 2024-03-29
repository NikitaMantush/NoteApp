package com.noteappbymantushnikita.mobile.ui.list

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.databinding.FragmentNoteListBinding
import com.noteappbymantushnikita.mobile.util.openFragment
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.repository.SharedPreferencesRepository
import com.noteappbymantushnikita.mobile.ui.note.AddNoteFragment
import com.noteappbymantushnikita.mobile.ui.LogInFragment
import com.noteappbymantushnikita.mobile.ui.list.adapter.NoteListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment() {
    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

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
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_item_decorator, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        binding?.run {
            recyclerView.addItemDecoration(dividerItemDecoration)
            logoutButton.setOnClickListener {
                sharedPreferencesRepository.logout()
                logoutDialog()
            }
            addNoteButton.setOnClickListener {
                requireActivity().supportFragmentManager.openFragment(
                    AddNoteFragment(),
                    AddNoteFragment.TAG
                )
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
                    onClickId = { itemId, view ->
                        createMenu(itemId, view)
                    },
                    onClick = { noteTitle ->
                        Toast.makeText(requireActivity(), noteTitle, Toast.LENGTH_LONG).show()
                    },
                )
            }
            (adapter as? NoteListAdapter)?.submitList(listNote)
        }
    }

    private fun logoutDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.logout)
            .setIcon(R.drawable.ic_logout)
            .setMessage(R.string.logout_warning)
            .setPositiveButton(R.string.yes){_,_ ->
                requireActivity().supportFragmentManager.openFragment(LogInFragment())
            }
            .setNegativeButton(R.string.no){_,_ ->
                //do nothing
            }
            .show()
    }

    private fun createMenu(itemId: Int, view: View) {
        val popup = PopupMenu(requireContext(), view, Gravity.END)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_item, popup.menu)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popup.setForceShowIcon(true)
        }
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.show -> {

                }

                R.id.edit -> {

                    //TODO edit

                }

                R.id.delete -> {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(R.string.delete)
                        .setIcon(R.drawable.ic_warning)
                        .setMessage(R.string.delete_warning)
                        .setPositiveButton(R.string.yes) { _, _ ->
                            viewModel.deleteNote(itemId)
                        }
                        .setNegativeButton(R.string.no) { _, _ ->
                            //do nothing
                        }
                        .show()
                }
            }
            return@OnMenuItemClickListener true
        }
        )
        popup.show()
    }

    companion object {
        const val TAG = "NoteListFragment"
    }
}