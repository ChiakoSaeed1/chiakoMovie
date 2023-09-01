package com.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.NavHostFragment
import com.example.chiakomovie.R
import com.example.chiakomovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding:ActivityMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment=supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment

        val popupMenu=android.widget.PopupMenu(this,null)
        popupMenu.inflate(R.menu.menu)
        binding.apply {
            bottomBar.setupWithNavController(popupMenu.menu,navHostFragment.navController)
            navHostFragment.navController.addOnDestinationChangedListener{_,destination,_ ->
                when(destination.id){
                    R.id.splashFragment->{
                        bottomBar.visibility = View.GONE
                    } R.id.detailFragment->{
                    bottomBar.visibility = View.GONE
                }
                    else->{
                        bottomBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}