package com.example.homework17.ui.trailerfragment

import android.os.Bundle
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.homework17.databinding.FragmentTrailerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrailerFragment : Fragment() {

    private lateinit var binding: FragmentTrailerBinding
    private val args: TrailerFragmentArgs by navArgs()
    private val trailerViewModel: TrailerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrailerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trailerViewModel.getTrailer(args.id)
        trailerViewModel.trailer.observe(viewLifecycleOwner) {
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(it)
            }
        }
        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true

        binding.webView.canGoBack()
        binding.webView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action === MotionEvent.ACTION_UP && binding.webView.canGoBack()) {
                binding.webView.goBack()
                return@OnKeyListener true
            }
            false
        })
    }
}