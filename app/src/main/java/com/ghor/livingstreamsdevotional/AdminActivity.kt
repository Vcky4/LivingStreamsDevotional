package com.ghor.livingstreamsdevotional

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ghor.livingstreamsdevotional.databinding.ActivityAdminBinding
import com.ghor.livingstreamsdevotional.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.adminNavView

        val navController = findNavController(R.id.nav_host_fragment_activity_admin)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_devotional_admin, R.id.navigation_nugget_admin, R.id.navigation_event_admin
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}