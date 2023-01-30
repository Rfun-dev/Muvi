package com.listview.muvi.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.listview.muvi.R
import com.listview.muvi.databinding.FragmentDetailBinding
import com.listview.muvi.databinding.FragmentNowPlayingBinding
import com.listview.muvi.ui.api.response.Movie
import com.listview.muvi.ui.helper.Helper.load

class DetailFragment : Fragment() {
    var mDetailMovie : Movie? = null
    private var getBinding: FragmentDetailBinding? = null
    private val binding get() = getBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getBinding = FragmentDetailBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tvTitleDescriptionMovie?.text = mDetailMovie?.title
        binding?.tvDetailDescriptionMovie?.text = mDetailMovie?.description
        binding?.imgImageDescriptionMovie?.load(mDetailMovie?.image)
        binding?.tvTextItem1?.text = mDetailMovie?.release
        val rating = mDetailMovie?.rating?.div(2).toString()
        binding?.tvTextItem3?.text = rating
        binding?.btnBackDescipritonMovie?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

}