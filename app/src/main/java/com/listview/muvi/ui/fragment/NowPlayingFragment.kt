package com.listview.muvi.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.listview.muvi.R
import com.listview.muvi.databinding.FragmentNowPlayingBinding
import com.listview.muvi.ui.adapter.MovieAdapter
import com.listview.muvi.ui.api.ApiConfig
import com.listview.muvi.ui.api.response.Movie
import com.listview.muvi.ui.api.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingFragment : Fragment(), MovieAdapter.OnItemClickCallback {
    private var getBinding: FragmentNowPlayingBinding? = null
    private val binding get() = getBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getBinding = FragmentNowPlayingBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding?.rvNowPlaying?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding?.rvNowPlaying?.addItemDecoration(itemDecoration)

        findNowPlaying()
    }

    private fun findNowPlaying() {
        showLoading(true)
        val client = ApiConfig.getApiService().getNowPlaying()
        client.enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                Log.d("TAG", "bagus: ${response}")
                showLoading(false)
                if(response.isSuccessful){
                    Log.d("TAG", "onResponse: ${response}")
                    val responseResult = response.body()
                    if(responseResult != null){
                        setMovie(responseResult.results as List<Movie>)
                    }
                }else{
                    Log.e("MainActivity", "${response.message()}:")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                showLoading(false)
                Log.e("MainActivity", "${t.message}:")
            }
        })
    }

    private fun setMovie(results: List<Movie>) {
        val adapter = MovieAdapter()
        adapter.setListNotes(results)
        adapter.setOnItemClickCallback(this)
        binding?.rvNowPlaying?.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }

    override fun onItemClicked(filmModel: Movie) {
        val detailFragment = DetailFragment()
        detailFragment.mDetailMovie = filmModel
        val mFragmentManager = parentFragmentManager
        mFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, detailFragment, DetailFragment::class.java.simpleName)
            addToBackStack(null)
            commit()
        }

    }
}
