package com.ms.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ms.news.databinding.ActivityMainBinding
import com.ms.news.db.ArticleDatabase
import com.ms.news.repository.NewsRepository
import com.ms.news.ui.NewsViewModel
import com.ms.news.ui.NewsViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel:NewsViewModel

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository=NewsRepository(ArticleDatabase(this))
        val factory=NewsViewModelProviderFactory(application,repository)
        viewModel= ViewModelProvider(this,factory)[NewsViewModel::class.java]

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController=navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }
}