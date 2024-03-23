package com.noteappbymantushnikita.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.repository.SharedPreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InitialFragment : Fragment() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return View(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when {
            sharedPreferencesRepository.isFirstLaunch() -> {
                findNavController().navigate(InitialFragmentDirections.actionInitialFragmentToStartFragment())
                sharedPreferencesRepository.setIsFirstLaunch()
            }

            sharedPreferencesRepository.getUserEmail() == null -> {
                findNavController().navigate(InitialFragmentDirections.actionInitialFragmentToLogInFragment())
            }

            else -> {
                findNavController().navigate(InitialFragmentDirections.actionInitialFragmentToMainFragment())
            }
        }
    }
}