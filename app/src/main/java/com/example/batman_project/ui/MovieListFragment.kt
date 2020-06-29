package com.example.batman_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.batman_project.R
import com.example.batman_project.adapter.MoviesListAdapter
import com.example.batman_project.databinding.FragmentMovieListBinding
import com.example.batman_project.model.Search

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
        mBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_movie_list, container, false)

        val adapter = MoviesListAdapter()
        val fakeItems = ArrayList<Search>()
        for (i in 1..10){
            fakeItems.add(
                Search(
                    "https://m.media-amazon.com/images/M/MV5BZmUwNGU2ZmItMmRiNC00MjhlLTg5YWUtODMyNzkxODYzMmZlXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg",
                    "Movie Name$i", "MOVIE", "200$i", "test$i"
                )
            )
        }
        adapter.submitList(fakeItems)
        mBinding.moviesRecycle.layoutManager = GridLayoutManager(activity, 2)
        mBinding.moviesRecycle.adapter = adapter

        return mBinding.root
    }

}
