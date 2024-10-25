package com.rn.tuaobraparapedreiro.model

data class Cliente(
    val id: Long,
    val nome: String,
    val email: String,
    val contatoWhatsApp: String,
    val endereco: Endereco
)
