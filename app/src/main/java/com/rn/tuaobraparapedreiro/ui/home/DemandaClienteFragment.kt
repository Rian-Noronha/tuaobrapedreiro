package com.rn.tuaobraparapedreiro.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rn.tuaobraparapedreiro.databinding.FragmentDemandaClienteBinding

class DemandaClienteFragment : Fragment() {
   private lateinit var demandaClienteViewModel: DemandaClienteViewModel
    private var _binding: FragmentDemandaClienteBinding? = null
    private val binding get() = _binding!!
    private val args: DemandaClienteFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        demandaClienteViewModel = ViewModelProvider(this).get(DemandaClienteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDemandaClienteBinding.inflate(inflater, container, false)

        val demandaId = args.demandaId

        demandaClienteViewModel.fetchDemandaCliente(demandaId)

        demandaClienteViewModel.demandaCliente.observe(viewLifecycleOwner){ demandaCliente ->

            if(demandaCliente != null){
                binding.txtDetalhes.text = demandaCliente.detalhes
                binding.txtDataPublicacao.text = demandaCliente.dataPublicacao
                binding.txtTrabalhoASerFeito.text = demandaCliente.trabalhoASerFeito
                binding.txtLocalDemanda.text = demandaCliente.cep
                binding.txtNomeLugar.text = demandaCliente.nomeLugar
                binding.txtNumeroLugar.text = demandaCliente.numero

                binding.btnQueroContato.setOnClickListener{
                    showBottomDialog(
                        demandaCliente.nomeCliente,
                        demandaCliente.emailCliente,
                        demandaCliente.contatoCliente
                    )
                }
            }
        }

        demandaClienteViewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun showBottomDialog(nome: String, email: String, contato: String){
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetView = layoutInflater.inflate(com.rn.tuaobraparapedreiro.R.layout.bottom_sheet_contato_cliente, null)

        bottomSheetView.findViewById<TextView>(com.rn.tuaobraparapedreiro.R.id.txtNomeCliente).text = nome
        bottomSheetView.findViewById<TextView>(com.rn.tuaobraparapedreiro.R.id.txtEmailCliente).text = email
        bottomSheetView.findViewById<TextView>(com.rn.tuaobraparapedreiro.R.id.txtContatoCliente).text = contato

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}