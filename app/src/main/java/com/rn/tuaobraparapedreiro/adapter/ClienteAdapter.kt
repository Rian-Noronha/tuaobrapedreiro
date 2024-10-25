package com.rn.tuaobraparapedreiro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rn.tuaobraparapedreiro.databinding.ItemClienteBinding
import com.rn.tuaobraparapedreiro.model.Cliente

class ClienteAdapter(
    private var clientes : List<Cliente>
) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
       val binding = ItemClienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ClienteViewHolder(binding)
    }

    override fun getItemCount() = clientes.size

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = clientes[position]
        holder.bind(cliente)
    }

    fun updateDemandas(newClientes: List<Cliente>) {
        clientes = newClientes
        notifyDataSetChanged()
    }


    class ClienteViewHolder(private val binding: ItemClienteBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(cliente: Cliente){
            binding.txtContatoCliente.text = cliente.contatoWhatsApp
            binding.txtNomeCliente.text = cliente.nome
            binding.txtEmailCliente.text = cliente.email
            binding.txtCepCliente.text = cliente.endereco.cep
            binding.txtNomeLugar.text = cliente.endereco.nomeLugar
            binding.txtNumeroLugar.text = cliente.endereco.numero
        }

    }


}