package com.example.homework17.ui.upcomingfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework17.MovieAdapter
import com.example.homework17.databinding.FragmentUpcomingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingFragment : Fragment() {

    lateinit var binding:FragmentUpcomingBinding
    private val upComingViewModel : UpComingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Upcoming Movies"
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        val adapter = MovieAdapter{
            val action = UpcomingFragmentDirections.actionUpcomingFragmentToDetailFragment(it.id)
            findNavController().navigate(action)
        }
        upComingViewModel.upComingMovies.observe(viewLifecycleOwner){
            binding.recyclerView.adapter = adapter
            adapter.submitList(it)
        }
    }
}