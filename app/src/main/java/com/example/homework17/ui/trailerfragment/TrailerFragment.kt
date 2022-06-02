package com.example.homework17.ui.trailerfragment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
        if (!hasInternetConnection()) {
            binding.tvMessage.visibility = View.VISIBLE
            binding.webView.visibility = View.GONE
        }
        trailerViewModel.getTrailer(args.id)
        trailerViewModel.trailer.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.webView.apply {
                    webViewClient = WebViewClient()
                    loadUrl(it)
                }
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

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = context?.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}