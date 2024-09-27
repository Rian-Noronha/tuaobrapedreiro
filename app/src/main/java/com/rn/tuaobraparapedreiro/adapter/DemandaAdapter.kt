package com.rn.tuaobraparapedreiro.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.rn.tuaobraparapedreiro.databinding.ItemDemandaBinding
import com.rn.tuaobraparapedreiro.model.Demanda
import java.time.format.DateTimeFormatter

class DemandaAdapter(private var demandas: List<Demanda>) : RecyclerView.Adapter<DemandaAdapter.DemandaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemandaViewHolder {
        val binding = ItemDemandaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DemandaViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DemandaViewHolder, position: Int) {
        val demanda = demandas[position]
        holder.bind(demanda)
    }

    override fun getItemCount() = demandas.size


    @SuppressLint("NotifyDataSetChanged")
    fun updateDemandas(newDemandas: List<Demanda>) {
        demandas = newDemandas
        notifyDataSetChanged()
    }


    class DemandaViewHolder(private val binding: ItemDemandaBinding) : RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(demanda: Demanda) {
            binding.txtTrabalhoASerFeito.text = demanda.trabalhoSerFeito
            binding.txtHorarioPublicacao.text = demanda.dataPublicacao
            binding.txtLocalizacao.text = demanda.cepOndeSera
        }
    }
}
