package com.rn.tuaobraparapedreiro.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.rn.tuaobraparapedreiro.databinding.ItemDemandaBinding
import com.rn.tuaobraparapedreiro.model.Demanda

class DemandaAdapter(
    private var demandas: List<Demanda>,
    private val onItemClick: (Long) -> Unit
    ) : RecyclerView.Adapter<DemandaAdapter.DemandaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemandaViewHolder {
        val binding = ItemDemandaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DemandaViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DemandaViewHolder, position: Int) {
        val demanda = demandas[position]
        holder.bind(demanda)

        holder.itemView.setOnClickListener{
            onItemClick(demanda.id!!)
        }
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
            binding.txtHorarioPublicacao.text = formatarData(demanda.dataPublicacao)
            binding.txtLocalizacao.text = demanda.endereco.cep
        }


        fun formatarData(data: String): String {
            val partesDataHora = data.split("T")
            val dataParte = partesDataHora[0]
            val horaParte = partesDataHora[1].split(":")
            val anoMesDia = dataParte.split("-")
            val horas = horaParte[0].toInt() - 3
            val minutos = horaParte[1].toInt()
            val horaAjustada = if (horas < 0) 24 + horas else horas
            val dia = anoMesDia[2]
            val mes = anoMesDia[1]
            val ano = anoMesDia[0]
            val horaFormatada = "${horaAjustada}h${if (minutos > 0) "${minutos}min" else ""}"
            return "Às $horaFormatada, $dia/$mes/$ano"
        }

    }
}
