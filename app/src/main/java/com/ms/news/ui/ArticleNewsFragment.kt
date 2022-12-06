package com.ms.news.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ms.news.MainActivity
import com.ms.news.databinding.FragmentArticleNewsBinding

class ArticleNewsFragment : Fragment() {


    private var _binding: FragmentArticleNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel:NewsViewModel


    val args:ArticleNewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentArticleNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        val article=args.article

       binding.webView.apply {
           webViewClient=WebViewClient()
           article.url?.let { loadUrl(it) }
       }

        binding.fab.setOnClickListener {
            viewModel.savedArticle(article)
            Snackbar.make(view,"Article saved successfully",Snackbar.LENGTH_SHORT).show()
        }
    }


}