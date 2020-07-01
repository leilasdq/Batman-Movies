package com.example.batman_project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.batman_project.MoviesViewModel
import com.example.batman_project.R
import com.example.batman_project.adapter.MoviesListAdapter
import com.example.batman_project.databinding.FragmentMovieListBinding


/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {
    private lateinit var mBinding: FragmentMovieListBinding
    private lateinit var mViewModel: MoviesViewModel
    private lateinit var mAdapter: MoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_movie_list, container, false)

        (activity as MainActivity).supportActionBar?.title = "Batman"
        //(activity as MainActivity).supportActionBar?.setIcon(R.drawable.logo)

        val application = requireNotNull(value = this.activity).application
        mViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(MoviesViewModel::class.java)

        mAdapter = MoviesListAdapter(mViewModel)
        mViewModel.getAllMovies().observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
        })

        mViewModel.search.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it!=null) {
                    mViewModel.navigateFinished()
                    this.findNavController().navigate(
                        MovieListFragmentDirections.actionMovieListFragmentToDetailFragment(it.imdbID)
                    )
                }
            }
        })

        mBinding.moviesRecycle.layoutManager = GridLayoutManager(activity, 2)
        mBinding.moviesRecycle.adapter = mAdapter

        return mBinding.root
    }

}
