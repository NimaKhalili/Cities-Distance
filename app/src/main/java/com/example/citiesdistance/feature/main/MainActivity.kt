package com.example.citiesdistance.feature.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.citiesdistance.R
import com.example.citiesdistance.common.BaseActivity
import com.example.citiesdistance.data.DistanceListCount
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.color.MaterialColors
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import com.google.android.material.R.attr
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationView:BottomNavigationView
    private val viewModel:MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar_main))
        bottomNavigationView = findViewById(R.id.bottomNavigationView_main)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainer_main
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView_main)
        bottomNavigationView.setupWithNavController(navController)

        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home, R.id.distance_List,  R.id.gas)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDistanceListCountChangeEvent(distanceListCount: DistanceListCount) {
        val badge = bottomNavigationView.getOrCreateBadge(R.id.distance_List)
        badge.badgeGravity = BadgeDrawable.BOTTOM_END
        badge.backgroundColor = MaterialColors.getColor(bottomNavigationView, attr.colorPrimary)
        badge.number = distanceListCount.count
        badge.isVisible = distanceListCount.count > 0
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDistanceListCount()
    }
}