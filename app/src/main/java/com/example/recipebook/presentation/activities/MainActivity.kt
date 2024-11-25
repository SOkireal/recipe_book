package com.example.recipebook.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipebook.app.App
import com.example.recipebook.databinding.ActivityMainBinding
import com.example.recipebook.presentation.navigation.BottomNavigationBarHandler
import com.example.recipebook.presentation.navigation.FragmentRouter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentRouter: FragmentRouter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).appComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationBarHandler = BottomNavigationBarHandler(
            fragmentRouter,
            binding.bottomNavigationView,
        )

        fragmentRouter.apply {
            setFragmentManager(supportFragmentManager)
            setContainerId(binding.containerFragment.id)
            setBottomNavigationBarHandler(bottomNavigationBarHandler)
            showCatalogFragment()
        }
    }
}
