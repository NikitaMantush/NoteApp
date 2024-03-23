//package com.noteappbymantushnikita.mobile.noteAdapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.recyclerview.widget.RecyclerView
//import com.noteappbymantushnikita.mobile.R
//import com.noteappbymantushnikita.mobile.model.Note
//
//class NoteRecyclerAdapter(): RecyclerView.Adapter<NoteViewHolder>() {
//
//    private var list = arrayListOf<Note>()
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
//        return NoteViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
//        )
//    }
//    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
//        holder.bind(list[position])
//        holder.itemView.setOnClickListener{
//            //onClick(list[position].id)
//            Toast.makeText(it.context, list[position].title, Toast.LENGTH_LONG).show()
//        }
//    }
//    override fun getItemCount() = list.size
//    fun submitList(list: ArrayList<Note>){
//        this.list = list
//        notifyDataSetChanged()
//    }
//}