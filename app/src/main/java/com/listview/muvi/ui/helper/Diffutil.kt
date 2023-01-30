package com.listview.muvi.ui.helper

import androidx.recyclerview.widget.DiffUtil
import com.listview.muvi.ui.api.response.Movie

class Diffutil(private val mOldNoteMovie: List<Movie>, private val mNewNoteMovie: List<Movie>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteMovie.size
    }
    override fun getNewListSize(): Int {
        return mNewNoteMovie.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteMovie[oldItemPosition].id == mNewNoteMovie[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteMovie[oldItemPosition]
        val newEmployee = mNewNoteMovie[newItemPosition]
        return oldEmployee.title == newEmployee.title && oldEmployee.description == newEmployee.description
    }
}