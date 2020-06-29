package com.example.batman_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.batman_project.databinding.FragmentMovieListBinding

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {
    private lateinit var mBinding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)

        return mBinding.root
    }

}
