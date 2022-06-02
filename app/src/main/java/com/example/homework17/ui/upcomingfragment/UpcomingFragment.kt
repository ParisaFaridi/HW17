package com.example.homework17.ui.upcomingfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework17.MovieAdapter
import com.example.homework17.Resource
import com.example.homework17.databinding.FragmentUpcomingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpcomingFragment : Fragment() {

    private lateinit var binding:FragmentUpcomingBinding
    private val upComingViewModel : UpComingViewModel by viewModel()
    private lateinit var movieAdapter:MovieAdapter

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
        setRecyclerView()

        upComingViewModel.upComingMovies.observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let { data ->
                        movieAdapter.submitList(data)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    binding.tvMessage.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.tvMessage.text = response.message
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        }
    }
    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.GONE
    }
    private fun setRecyclerView() {
        movieAdapter = MovieAdapter {
            val action = UpcomingFragmentDirections.actionUpcomingFragmentToDetailFragment(it.id)
            findNavController().navigate(action)
        }
        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}