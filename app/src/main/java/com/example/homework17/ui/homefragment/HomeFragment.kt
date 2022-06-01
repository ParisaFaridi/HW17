package com.example.homework17.ui.homefragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework17.MovieAdapter
import com.example.homework17.R
import com.example.homework17.Resource
import com.example.homework17.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModelHome : HomeViewModel by viewModel()
    private lateinit var binding :FragmentHomeBinding
    private lateinit var movieAdapter : MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding =FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.title = "Movie App"
        setRecyclerView()
        viewModelHome.movies.observe(viewLifecycleOwner) { response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let { data ->
                        movieAdapter.submitList(data.results)
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

    private fun setRecyclerView() {
        movieAdapter = MovieAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.id)
            findNavController().navigate(action)
        }
        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.upcoming_item -> {
                findNavController().navigate(R.id.action_homeFragment_to_upcomingFragment)
                return true
            }
            R.id.search ->{
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}