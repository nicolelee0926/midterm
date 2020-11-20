package com.nicole.publisher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.nicole.publisher.article.ArticleViewModel
import com.nicole.publisher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        val navController = findNavController(R.id.nav_host_fragment)

        binding.publishFab.setOnClickListener {
            navController.navigate(NavigationDirections.actionGlobalPublishFragment())
        }

    }



}