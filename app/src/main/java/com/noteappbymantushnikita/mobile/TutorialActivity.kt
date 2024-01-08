package com.noteappbymantushnikita.mobile

import android.content.Intent
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3


class TutorialActivity: AppCompatActivity() {

    private var titlesList = mutableListOf<String>()
    private var descriptionsList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_tutorial)

        findViewById<TextView>(R.id.tutor_skip).setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        postToList()
        val viewPager2: ViewPager2 = findViewById(R.id.view_pager2)
        viewPager2.adapter = ViewPagerAdapter(titlesList, descriptionsList, imagesList)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val indicator : CircleIndicator3 = findViewById(R.id.circle_indicator)
        indicator.setViewPager(viewPager2)

    }

    private fun addToList(title: String, description: String, image: Int){
        titlesList.add(title)
        descriptionsList.add(description)
        imagesList.add(image)
    }

    private fun postToList(){
        addToList("Note App", getString(R.string.tutor_page_1_discover),
            R.drawable.ic_planet2)
        addToList("Note App", getString(R.string.tutor_page_2_discover),
            R.drawable.ic_level_up)
        addToList("Note App", getString(R.string.tutor_page_3_discover),
            R.drawable.ic_chat)
    }
}