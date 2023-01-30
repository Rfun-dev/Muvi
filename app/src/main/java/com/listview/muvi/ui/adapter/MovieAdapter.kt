package com.listview.muvi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.listview.muvi.databinding.ItemFragmentBinding
import com.listview.muvi.ui.api.response.Movie
import com.listview.muvi.ui.helper.Diffutil
import com.listview.muvi.ui.helper.Helper.load

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var onItemClickCallback : OnItemClickCallback? = null
    private val listMovie = ArrayList<Movie>()
    fun setListNotes(listMovie: List<Movie>) {
        val diffCallback = Diffutil(this.listMovie, listMovie)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listMovie.clear()
        this.listMovie.addAll(listMovie)
        diffResult.dispatchUpdatesTo(this)
    }
    private lateinit var binding : ItemFragmentBinding
    inner class ViewHolder(itemView: ItemFragmentBinding) : RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemFragmentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.apply {
            listMovie[position].apply {
                tvTitleItemFilm.text = title
                imgImageItemFilm.load(image)
                rbRatingItemFilm.rating = rating.div(2)
                itemFragment.setOnClickListener {
                    onItemClickCallback?.onItemClicked(this)
                }
            }
        }
    }


    interface OnItemClickCallback{
        fun onItemClicked(filmModel: Movie)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemCount(): Int = listMovie.size

    override fun getItemViewType(position: Int): Int = position

    override fun getItemId(position: Int): Long = position.toLong()
}