package com.example.homework17.ui.trailerfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.homework17.R
import com.example.homework17.TrailerFragmentArgs
import com.example.homework17.databinding.FragmentTrailerBinding

class TrailerFragment : Fragment() {

    private lateinit var binding: FragmentTrailerBinding
    private val args:TrailerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrailerBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        //args.id -> get link by api
//        binding.webView.apply {
//            webViewClient = WebViewClient()
//            loadUrl(args.url)
//        }
//        val webSettings: WebSettings = binding.webView.settings
//        webSettings.javaScriptEnabled = true
//
//        binding.webView.canGoBack()
//        binding.webView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_BACK && event.action === MotionEvent.ACTION_UP && binding.webView.canGoBack()) {
//                binding.webView.goBack()
//                return@OnKeyListener true
//            }
//            false
//        })
    }