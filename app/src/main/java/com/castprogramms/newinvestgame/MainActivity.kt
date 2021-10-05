package com.castprogramms.newinvestgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import androidx.core.view.contains
import androidx.core.view.get
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.castprogramms.newinvestgame.databinding.BadgeLayoutBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textview.MaterialTextView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    var isNewsFragment = MutableLiveData(false)
    var currentSize = 0
    private val viewModel: MainActivityViewModel by viewModel()

    private lateinit var navController: NavController
    private lateinit var navMenu: BottomNavigationView
    private val badge by lazy { layoutInflater.inflate(R.layout.badge_layout, navMenu, false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        navMenu = findViewById(R.id.nav_view)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.allStockFragment, R.id.newsFragment, R.id.profileFragment
            )
        )

        viewModel.getNews().observe(this, {
            if (isNewsFragment.value == true)
                currentSize = it.size
            setBadgeNews(it.size - currentSize)
        })

        isNewsFragment.observe(this, {
            if (it) {
                ((navMenu.getChildAt(0) as BottomNavigationMenuView).getChildAt(1) as BottomNavigationItemView).removeView(
                    badge
                )
                currentSize =
                    if (viewModel.getNews().value != null) viewModel.getNews().value!!.size else 0
            }
        })


        setupActionBarWithNavController(navController, appBarConfiguration)
        navMenu.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    private fun setBadgeNews(number: Int) {
        if (number > 0) {
            val badgeLayoutBinding = BadgeLayoutBinding.bind(badge)
            badgeLayoutBinding.number.text = if (number < 100) number.toString() else "99+"
            if (((navMenu.getChildAt(0) as BottomNavigationMenuView).getChildAt(1) as BottomNavigationItemView).contains(
                    badge
                )
            )
                ((navMenu.getChildAt(0) as BottomNavigationMenuView).getChildAt(1) as BottomNavigationItemView)[2]
                    .findViewById<MaterialTextView>(R.id.number).text =
                    if (number < 100) number.toString() else "99+"
            else
                ((navMenu.getChildAt(0) as BottomNavigationMenuView).getChildAt(1) as BottomNavigationItemView).addView(
                    badge,
                    FrameLayout.LayoutParams(badge.layoutParams).apply {
                        gravity = Gravity.CENTER_HORIZONTAL
                        leftMargin = 40
                    }
                )
        } else
            ((navMenu.getChildAt(0) as BottomNavigationMenuView).getChildAt(1) as BottomNavigationItemView).removeView(
                badge
            )
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopGame()
    }

    override fun onStart() {
        super.onStart()
        viewModel.startGame()
    }
}