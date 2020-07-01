package com.example.batman_project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.example.batman_project.R
import com.example.batman_project.adapter.MoviesListAdapter
import com.example.batman_project.databinding.FragmentMovieListBinding
import com.example.batman_project.model.Search
import com.example.batman_project.viewmodel.MoviesViewModel


/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {
    private lateinit var mBinding: FragmentMovieListBinding
    private lateinit var mAdapter: MoviesListAdapter
    private val itemList = MediatorLiveData<List<Search>>()
    private val urlMap = HashMap<String, String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_list, container, false
        )
        (activity as MainActivity).supportActionBar?.title = "Batman"

        val mViewModel = initViewModel()
        mAdapter = MoviesListAdapter(mViewModel)
        initObservers(mViewModel)
        setupRecycle()

        return mBinding.root
    }

    private fun setupRecycle() {
        mBinding.moviesRecycle.layoutManager = GridLayoutManager(activity, 2)
        mBinding.moviesRecycle.adapter = mAdapter
    }

    private fun initViewModel(): MoviesViewModel {
        val application = requireNotNull(value = this.activity).application
        val mViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(
            MoviesViewModel::class.java
        )
        return mViewModel
    }

    private fun initObservers(mViewModel: MoviesViewModel) {
        mViewModel.getAllMovies().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                itemList.value = it
                mViewModel.cacheItems(it)
            }
        })

        itemList.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                mBinding.nothing.visibility = View.VISIBLE
            } else {
                addSlider(it)
                mBinding.nothing.visibility = View.INVISIBLE
                mAdapter.submitList(it)
            }
        })

        mViewModel.search.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it != null) {
                    mViewModel.navigateFinished()
                    this.findNavController().navigate(
                        MovieListFragmentDirections.actionMovieListFragmentToDetailFragment(it.imdbID)
                    )
                    mViewModel.navigateFinished()
                }
            }
        })
    }

    private fun addSlider(it: List<Search>) {
        val random = listOf(it).random()
        for (i in 0..5) {
            urlMap[random[i].Title] = random[i].Poster
        }
        for (name in urlMap.keys) {
            val textSliderView = TextSliderView(activity)
            // initialize a SliderLayout
            textSliderView
                .setScaleType(BaseSliderView.ScaleType.CenterInside)
                .description(name)
                .image(urlMap[name]).scaleType = BaseSliderView.ScaleType.Fit
            mBinding.slider.addSlider(textSliderView)
        }
    }

}
