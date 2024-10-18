package com.rn.tuaobraparapedreiro.model

import java.time.LocalDateTime

data class Demanda(
    val id: Long? = null,
    val detalhes: String,
    val trabalhoSerFeito: String,
    val dataPublicacao: String,
    val endereco: Endereco
)
