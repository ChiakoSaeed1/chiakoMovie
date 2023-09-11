package com.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.adapters.AdapterActor
import com.data.database.MovieEntity
import com.example.chiakomovie.R
import com.example.chiakomovie.databinding.FragmentDetailBinding
import com.utils.MyResponse
import com.utils.isAdapter
import com.utils.isShown
import com.viewmodel.ViewModelDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    //binding
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    //args
    private val args: DetailFragmentArgs by navArgs()
    var movieId = 0

    //viewModel
    private val viewModel: ViewModelDetail by viewModels()

    @Inject
    lateinit var adapterActor: AdapterActor

    //more
    private var isFav = false

    @Inject
    lateinit var entity: MovieEntity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //api
        args.let {
            movieId = args.movieId
        }
        viewModel.loadDetail(movieId)
        binding.apply {
            txtBack.setOnClickListener {
                findNavController().popBackStack()
            }
            viewModel.detailData.observe(viewLifecycleOwner) { data ->
                when (data) {
                    is MyResponse.LOADING -> {
                        load.isShown(true, container)
                    }
                    is MyResponse.SUCCESS -> {
                        load.isShown(false, container)

                        data.data?.let {
                            entity.id = it.id!!
                            entity.image = it.poster!!
                            entity.title = it.title!!
                            binding.apply {
                                adapterActor.setData(it.images!!)
                                recActor.isAdapter(
                                    LinearLayoutManager(
                                        requireContext(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    ),
                                    adapterActor
                                )
                                imgCoverBig.load(it.poster) {
                                    crossfade(true)
                                    crossfade(500)
                                }
                                imgCoverSmall.load(it.poster) {
                                    crossfade(true)
                                    crossfade(500)
                                }
                                txtTitle.text = it.title
                                txtCalender.text = it.year
                                txtTime.text = it.runtime
                                txtGenres.text = it.type
                                txtSummary.text = it.plot


                            }
                        }
                    }
                    is MyResponse.ERROR -> {
                        load.isShown(false, container)

                    }
                }

            }
            /*fav*/
            viewModel.loadExist(movieId)
            viewModel.isFavorite.observe(viewLifecycleOwner) { data ->
                isFav = data
                if (data) {
                    txtFav.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.tart_orange
                        )
                    )
                } else {
                    txtFav.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))

                }

            }

            txtFav.setOnClickListener {

                if (isFav) {
                    viewModel.deleteMovie(entity)
                } else {
                    viewModel.saveMovie(entity)

                }

            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}