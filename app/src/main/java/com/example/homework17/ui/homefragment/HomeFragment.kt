package com.example.homework17.ui.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework17.MovieAdapter
import com.example.homework17.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModelHome : HomeViewModel by viewModel()
    private lateinit var binding :FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = MovieAdapter()
        viewModelHome.movies.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = adapter
            adapter.submitList(it)
        }
    }
}