package com.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.adapters.*
import com.data.models.ResponseTopTv
import com.example.chiakomovie.databinding.FragmentMainBinding
import com.utils.MyResponse
import com.utils.isAdapter
import com.utils.isShown
import com.viewmodel.ViewModelTop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    //binding
    private var _binding:FragmentMainBinding?=null
    private val binding get() = _binding!!
    //viewModel
    private val viewModel:ViewModelTop by viewModels()
    //adapter
    @Inject
    lateinit var adapterTop: AdapterTop
    @Inject
    lateinit var adapterGenres: AdapterGenres
    @Inject
    lateinit var adapterPop: AdapterPop
    @Inject
    lateinit var adapterRec: AdapterRec
    @Inject
    lateinit var adapterLast: AdapterLast

    //more
    private var index = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //callApi
        cacheTopMovie()
        viewModel.loadGenres()
        viewModel.loadPopData(9)
        viewModel.loadRecData(1)
        viewModel.loadLastData(11)
        binding.apply {
            //Top
            viewModel.topData.observe(viewLifecycleOwner) { data ->
                when (data) {
                    is MyResponse.LOADING -> {
                        load.isShown(true, container)
                    }
                    is MyResponse.SUCCESS -> {
                        load.isShown(false, container)
                        data.data!!.data.let {
                            autoScroll(it!!)
                            if (it.isNotEmpty()) {
                                adapterTop.setData(it)

/*
                              autoScroll(it.items)
*/

                                adapterTop.setonItemClickListener {data->
                                    val action=MainFragmentDirections.actionToDetail(data.id!!)
                                    findNavController().navigate(action)

                                }
                                val snapHelper = LinearSnapHelper()
                                snapHelper.attachToRecyclerView(recTop)
                                recTop.onFlingListener=null

                                indicator.attachToRecyclerView(recTop, snapHelper)


                            }
                        }

                    }
                    is MyResponse.ERROR -> {
                        load.isShown(false, container)

                    }
                }

            }
            //Genres
            viewModel.apiGenres.observe(viewLifecycleOwner){state->
                when(state){
                    is MyResponse.LOADING->{
                        load.isShown(true,container)
                    }
                    is MyResponse.SUCCESS->{
                        load.isShown(false,container)
                        state.data.let {
                            if (it!!.isNotEmpty()){
                                adapterGenres.setData(it)
                                recGener.isAdapter(LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false),
                                adapterGenres)
                            }
                        }

                    }
                    is MyResponse.ERROR->{
                        load.isShown(false,container)
                    }


                }

            }
            //Pop
            viewModel.popData.observe(viewLifecycleOwner){state->
                when(state){
                    is MyResponse.LOADING->{
                        load.isShown(true,container)
                    }
                    is MyResponse.SUCCESS->{
                        load.isShown(false,container)
                        state.data!!.data?.let {
                            if (it.isNotEmpty()){
                                adapterPop.setData(it)
                                recPopular.isAdapter(LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false),
                                adapterPop)
                                adapterPop.setonItemClickListener {data->
                                    val action=MainFragmentDirections.actionToDetail(data.id!!)
                                    findNavController().navigate(action)

                                }
                            }
                        }
                    }
                    is MyResponse.ERROR->{
                        load.isShown(false,container)

                    }
                }

            }
            //Rec
            viewModel.recData.observe(viewLifecycleOwner){state->
                when(state){
                    is MyResponse.LOADING->{
                        load.isShown(true,container)
                    }
                    is MyResponse.SUCCESS->{
                        load.isShown(false,container)
                        state.data!!.data?.let {
                            if (it.isNotEmpty()){
                                adapterRec.setData(it)
                                recRecently.isAdapter(LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false),
                                    adapterRec)
                                adapterRec.setonItemClickListener {data->
                                    val action=MainFragmentDirections.actionToDetail(data.id!!)
                                    findNavController().navigate(action)

                                }

                            }
                        }
                    }
                    is MyResponse.ERROR->{
                        load.isShown(false,container)

                    }
                }

            }
            //Last
            viewModel.apiLast.observe(viewLifecycleOwner){state->
                when(state){
                    is MyResponse.LOADING->{
                        load.isShown(true,container)
                    }
                    is MyResponse.SUCCESS->{
                        load.isShown(false,container)
                        state.data!!.data?.let {
                            if (it.isNotEmpty()){
                                adapterLast.setData(it)
                                recLase.isAdapter(LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false),
                                    adapterLast)
                                adapterLast.setonItemClickListener {data->
                                    val action=MainFragmentDirections.actionToDetail(data.id!!)
                                    findNavController().navigate(action)

                                }

                            }
                        }
                    }
                    is MyResponse.ERROR->{
                        load.isShown(false,container)

                    }
                }

            }

        }

    }
    private fun autoScroll(list: List<ResponseTopTv.Data>){
        lifecycleScope.launch {
            repeat(100){
                delay(4000)
                if (index<list.size){
                    index++
                }else{
                    index=0
                }
                binding.recTop.smoothScrollToPosition(index)
            }
        }
    }

    //cache
    private fun cacheTopMovie(){
        viewModel.readCache.observe(viewLifecycleOwner){dataBase->
            binding.apply {
                recTop.isAdapter(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false), adapterTop
                )
                if (dataBase.isNotEmpty()){
                    dataBase[0].data?.let {
                        adapterTop.setData(it)
                        autoScroll(it)
                    }
                }else{
                    viewModel.loadTopData(5)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}