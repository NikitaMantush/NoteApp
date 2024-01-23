package com.noteappbymantushnikita.mobile

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.noteappbymantushnikita.mobile.databinding.ActivityTutorialBinding
import me.relex.circleindicator.CircleIndicator3


class TutorialActivity : AppCompatActivity() {

    private var descriptionsList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(binding.root)
        binding.tutorSkip.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        postToList()
        val viewPager2: ViewPager2 = binding.viewPager2
        viewPager2.adapter = ViewPagerAdapter(descriptionsList, imagesList)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val indicator: CircleIndicator3 = binding.circleIndicator
        indicator.setViewPager(viewPager2)
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
}