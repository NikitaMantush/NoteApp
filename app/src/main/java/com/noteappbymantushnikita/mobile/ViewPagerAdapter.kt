package com.noteappbymantushnikita.mobile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noteappbymantushnikita.mobile.databinding.ItemPageBinding

class ViewPagerAdapter(
    private val description: List<String>,
    private val images: List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(val binding: ItemPageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val itemBinding =
            ItemPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Pager2ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.binding.tutorDescription.text = description[position]
        holder.binding.tutorImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return description.size
    }
}
