package com.rn.tuaobraparapedreiro.model

data class DemandaCliente(
    val id: Long? = null,
    val detalhes: String,
    val dataPublicacao: String,
    val trabalhoASerFeito: String,
    val cep: String,
    val nomeCliente: String,
    val emailCliente: String,
    val contatoCliente: String
)
