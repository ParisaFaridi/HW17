package com.example.homework17.ui.detailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homework17.R
import com.example.homework17.Resource
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getMovie(args.movieId)
        detailViewModel.movie.observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Success ->{
                    binding.progressBar.visibility = View.GONE
                    binding.layout.visibility = View.VISIBLE
                    response.data?.let {
                        binding.movie = it
                        Glide.with(binding.imageView2).load(POSTER_PATH + it.backdrop_path)
                            .into(binding.imageView2)
                        activity?.title = it.title
                    }
                }
                is Resource.Error ->{
                    binding.progressBar.visibility = View.GONE
                    binding.layout.visibility = View.GONE
                    binding.tvMessage.visibility = View.VISIBLE
                    binding.tvMessage.text = response.message
                }
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.layout.visibility = View.GONE
                }
            }
        }
        binding.btnShowTrailer.setOnClickListener {
            val action = detailViewModel.movie.value?.let { it1 ->
                it1.data?.let { it2 ->
                    DetailFragmentDirections.actionDetailFragmentToTrailerFragment(
                        it2.id)
                }
            }
            if (action != null) {
                findNavController().navigate(action)
            }
        }

    }
}