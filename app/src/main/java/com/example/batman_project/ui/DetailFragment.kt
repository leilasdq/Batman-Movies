package com.example.batman_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation

import com.example.batman_project.R
import com.example.batman_project.databinding.FragmentDetailBinding
import com.example.batman_project.network.FetchItems

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private lateinit var mBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        FetchItems.getSpecificMovie("tt0372784").observe(viewLifecycleOwner, Observer {
            mBinding.movieBanner.load(it.Poster){
                crossfade(true)
                scale(Scale.FIT)
                transformations(RoundedCornersTransformation(1f))
            }
            mBinding.movieName.text = it.Title
            mBinding.movieType.text = it.Type
            mBinding.movieYear.text = it.Released
        })

        return mBinding.root
    }

}
