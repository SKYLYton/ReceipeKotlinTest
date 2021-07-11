package com.receipe

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.receipe.databinding.ActivityMainBinding
import com.receipe.serveces.MyService
import com.receipe.serveces.MyService.ServiceBinder


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_activity_main)


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                openFrgOfTabIndex(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        // startActivity(Intent(this, ServiceActivity::class.java))

    }

    private fun openFrgOfTabIndex(tab: TabLayout.Tab?) {
        if (tab == null) return

        when (tab.position) {
            0 -> {
                navController.navigate(R.id.searchRecipeFragment)
            }
            1 -> {
                navController.navigate(R.id.historyFragment)
            }
        }
    }


}