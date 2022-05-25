package com.example.homework17.ui.homefragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework17.MovieAdapter
import com.example.homework17.R
import com.example.homework17.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModelHome : HomeViewModel by viewModel()
    private lateinit var binding :FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding =FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = MovieAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.id)
            findNavController().navigate(action)
        }
        viewModelHome.movies.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = adapter
            adapter.submitList(it)
        }
        activity?.title = "Movie App"
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