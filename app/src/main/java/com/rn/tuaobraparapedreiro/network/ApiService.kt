package com.rn.tuaobraparapedreiro.network

import com.rn.tuaobraparapedreiro.model.Demanda
import com.rn.tuaobraparapedreiro.model.DemandaCliente
import com.rn.tuaobraparapedreiro.model.Pedreiro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/api/pedreiro")
    fun cadastrarPedreiro(@Body pedreiro: Pedreiro): Call<Void>

    @GET("/api/demandas")
    fun getDemandas(): Call<List<Demanda>>

    @GET("/api/demandacliente/{id}")
    fun getDemandaCliente(@Path("id") id: Long): Call<DemandaCliente>

}