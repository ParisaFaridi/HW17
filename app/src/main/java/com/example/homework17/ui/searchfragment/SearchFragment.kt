package com.example.homework17.ui.searchfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework17.MovieAdapter
import com.example.homework17.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel : SearchViewModel by viewModels()
    private lateinit var binding : FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        activity?.title = "Search"
        val adapter = MovieAdapter {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it.id)
            findNavController().navigate(action)
        }
        searchViewModel.searchResults.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = adapter
            adapter.submitList(it)
        }
        var job :Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable.let {
                    if (editable.toString().isNotEmpty()){
                        searchViewModel.search(editable.toString())
                    }
                }

            }
        }
    }
}