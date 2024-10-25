package com.rn.tuaobraparapedreiro.ui.contato

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rn.tuaobraparapedreiro.adapter.ClienteAdapter
import com.rn.tuaobraparapedreiro.databinding.FragmentContatoBinding

class ContatoFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentContatoBinding? = null
    private val binding get() = _binding!!
    private lateinit var contatoViewModel: ContatoViewModel
    private lateinit var clientesAdapter: ClienteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContatoBinding.inflate(inflater, container, false)
         contatoViewModel = ViewModelProvider(this).get(ContatoViewModel::class.java)
         auth = FirebaseAuth.getInstance()

        configurarRecyclerView()

        val currentUser = auth.currentUser
        val pedreiroEmail = currentUser?.email


        contatoViewModel.fetchClientesVinculadoPedreiroDemanda(pedreiroEmail.toString())

        contatoViewModel.clientes.observe(viewLifecycleOwner) { clientes ->
            clientesAdapter.updateDemandas(clientes)
        }

        contatoViewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let{
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


    private fun configurarRecyclerView(){
        clientesAdapter = ClienteAdapter(emptyList())

        binding.recyclerViewContato.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewContato.adapter = clientesAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}