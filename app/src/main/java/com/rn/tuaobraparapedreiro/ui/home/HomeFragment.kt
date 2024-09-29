package com.rn.tuaobraparapedreiro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rn.tuaobraparapedreiro.adapter.DemandaAdapter
import com.rn.tuaobraparapedreiro.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var demandasAdapter: DemandaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        configurarRecyclerView()

        homeViewModel.demandas.observe(viewLifecycleOwner) { demandas ->

            demandasAdapter.updateDemandas(demandas)
        }


        homeViewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun configurarRecyclerView() {

        demandasAdapter = DemandaAdapter(emptyList()) { id ->
            abrirDemandaCliente(id)
        }

        binding.recyclerViewDemanda.layoutManager = LinearLayoutManager(context)

        binding.recyclerViewDemanda.adapter = demandasAdapter
    }

    private fun abrirDemandaCliente(id: Long){

        val action = HomeFragmentDirections.actionNavigationHomeToNavigationDemandaCliente(id)
        findNavController().navigate(action)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
