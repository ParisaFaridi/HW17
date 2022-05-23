package com.example.homework17.ui.detailfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.homework17.R
import com.example.homework17.ui.homefragment.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    val args:DetailFragmentArgs by navArgs()
    private val detailViewModel : DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textv = view.findViewById<TextView>(R.id.text)
        detailViewModel.getMovie(args.movieId)
        detailViewModel.movie.observe(viewLifecycleOwner){
            textv.text = it.overview
        }

    }
}