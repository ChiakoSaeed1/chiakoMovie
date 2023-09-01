package com.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chiakomovie.R
import com.example.chiakomovie.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding:FragmentSplashBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleScope.launch {
                timer()
                delay(1000)
                findNavController().navigate(R.id.action_to_main)

            }
            findNavController().popBackStack(R.id.splashFragment,true)
         /*   root.setOnClickListener {
                findNavController().navigate(R.id.action_to_main)

            }*/

        }
    }
    private suspend fun timer(){
        (3 downTo 0).asFlow()
            .onEach {
                delay(1000)
            }
            .collect{
                binding.txtTimer.text=it.toString()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}