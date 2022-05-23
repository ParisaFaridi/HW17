package com.example.homework17.ui.detailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homework17.R
import com.example.homework17.databinding.FragmentDetailBinding
import com.example.homework17.network.POSTER_PATH
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val args:DetailFragmentArgs by navArgs()
    private val detailViewModel : DetailViewModel by viewModel()
    private lateinit var binding :FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detail,container,false)
        binding.vModel = detailViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getMovie(args.movieId)

        detailViewModel.movie.observe(viewLifecycleOwner){
            Glide.with(binding.imageView2).load(POSTER_PATH + it.poster_path)
                .into(binding.imageView2)
            activity?.title = it.title
        }

    }
}