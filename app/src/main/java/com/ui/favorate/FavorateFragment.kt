package com.ui.favorate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adapters.AdapterFav
import com.data.database.MovieEntity
import com.example.chiakomovie.R
import com.example.chiakomovie.databinding.FragmentFavorateBinding
import com.utils.DataStatus
import com.utils.isAdapter
import com.viewmodel.ViewModelFavorite
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavorateFragment : Fragment() {
    private var _binding: FragmentFavorateBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapterFav: AdapterFav
    private val viewModel: ViewModelFavorite by viewModels()

    @Inject
    lateinit var entity: MovieEntity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavorateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadMovies()
        with(binding) {
            viewModel.movieData.observe(viewLifecycleOwner) { state ->
                empty(state.isEmpty!!)
                state.data.let {
                    adapterFav.setData(it!!)
                    recFav.isAdapter(
                        GridLayoutManager(requireContext(), 2),
                        adapterFav
                    )
                    adapterFav.setonItemClickListener {
                        val action = FavorateFragmentDirections.actionToDetail(it.id)
                        findNavController().navigate(action)
                    }
                }

            }
        }
    }

    private fun empty(isEmpty: Boolean) {
        if (isEmpty) {
            binding.recFav.visibility = View.GONE
            binding.layEmpty.visibility = View.VISIBLE
        } else {
            binding.recFav.visibility = View.VISIBLE
            binding.layEmpty.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}