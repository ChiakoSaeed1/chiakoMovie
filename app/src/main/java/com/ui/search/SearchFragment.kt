package com.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adapters.AdapterPop
import com.example.chiakomovie.R
import com.example.chiakomovie.databinding.FragmentSearchBinding
import com.utils.MyResponse
import com.utils.isAdapter
import com.utils.isShown
import com.viewmodel.ViewModelSearch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    //binding
    private var _binding:FragmentSearchBinding?=null
    private val binding get() = _binding!!
    //Adapter
    @Inject
    lateinit var adapterPop: AdapterPop
    //viewModel
    private val viewModel:ViewModelSearch by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //call
        binding.apply {
            edtSearch.addTextChangedListener {
                val search=it.toString()
                if (search.length>1){
                    viewModel.loadSearch(search)
                }
            }
            viewModel.searchData.observe(viewLifecycleOwner){data->
                when(data){
                    is MyResponse.LOADING->{
                        load.isVisible=true
                        recSearch.isVisible=false
                    }
                    is MyResponse.SUCCESS->{
                        load.isVisible=false
                        recSearch.isVisible=true
                        data.data?.data?.let {
                            if (it.isNotEmpty()){
                                adapterPop.setData(it)
                                recSearch.isAdapter(GridLayoutManager(requireContext(),2),
                                adapterPop)
                                adapterPop.setonItemClickListener {action->
                                val directions=SearchFragmentDirections.actionToDetail(action.id!!)
                                findNavController().navigate(directions)

                                }
                            }
                        }

                    }
                    is MyResponse.ERROR->{
                        load.isVisible=false
                        recSearch.isVisible=true

                    }
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}