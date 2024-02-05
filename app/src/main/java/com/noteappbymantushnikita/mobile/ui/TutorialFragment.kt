package com.noteappbymantushnikita.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.ui.list.adapter.ViewPagerAdapter
import com.noteappbymantushnikita.mobile.databinding.FragmentTutorialBinding
import com.noteappbymantushnikita.mobile.util.openFragment
import me.relex.circleindicator.CircleIndicator3

class TutorialFragment:Fragment() {

    private var binding: FragmentTutorialBinding? = null
    private var descriptionsList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTutorialBinding.inflate(inflater)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tutorSkip?.setOnClickListener {
            parentFragmentManager.openFragment(SignUpFragment(), SignUpFragment.TAG)
        }
        postToList()
        val viewPager2: ViewPager2? = binding?.viewPager2?.apply {
            adapter = ViewPagerAdapter(descriptionsList, imagesList)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        val indicator: CircleIndicator3? = binding?.circleIndicator
        indicator?.setViewPager(viewPager2)
    }
    private fun addToList(description: String, image: Int) {
        descriptionsList.add(description)
        imagesList.add(image)
    }
    private fun postToList() {
        addToList(
            getString(R.string.tutor_page_1_discover),
            R.drawable.ic_planet2
        )
        addToList(
            getString(R.string.tutor_page_2_discover),
            R.drawable.ic_level_up
        )
        addToList(
            getString(R.string.tutor_page_3_discover),
            R.drawable.ic_chat
        )
    }
    companion object{
        const val TAG = "TutorialFragment"
    }
}