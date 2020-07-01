package com.example.batman_project.ui

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.batman_project.viewmodel.DetailViewModel

import com.example.batman_project.R
import com.example.batman_project.databinding.FragmentDetailBinding
import com.example.batman_project.model.Detail
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private lateinit var mBinding: FragmentDetailBinding
    private lateinit var mViewModel: DetailViewModel
    private lateinit var mData: Detail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        var item: Detail? = null
        val args = DetailFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(value = this.activity).application
        mViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(
            DetailViewModel::class.java
        )
        mBinding.viewModel = mViewModel

        mViewModel.getSpecificMovie(args.imdbId).observe(viewLifecycleOwner, Observer {
            item = it
            (activity as MainActivity).supportActionBar?.title = it.Title
            mBinding.nothing.visibility = View.INVISIBLE
            mBinding.constraint.visibility = View.VISIBLE
            mViewModel.putMovieToDatabase(it)
            mBinding.detail = it
            mData = it
            mBinding.movieBanner.load(it.Poster) {
                crossfade(true)
                scale(Scale.FIT)
                transformations(RoundedCornersTransformation(1f))
            }
        })

        if (item == null){
            mBinding.constraint.visibility = View.INVISIBLE
            (activity as MainActivity).supportActionBar?.title = "Nothing is here"
            mBinding.nothing.text = "Nothing found here\nCheck your connection First"
        }

        mViewModel.play.observe(viewLifecycleOwner, Observer {
            if (it) {
                Snackbar.make(mBinding.root, "This is not available yet", Snackbar.LENGTH_LONG)
                    .show()
                mViewModel.onPlayFinished()
            }
        })

        mViewModel.share.observe(viewLifecycleOwner, Observer {
            if (it) {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Check this awesome movie! \n ${mData.Title}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Send Movie to a friend")
                startActivity(shareIntent)

                mViewModel.onShareFinished()
            }
        })

        return mBinding.root
    }

}
