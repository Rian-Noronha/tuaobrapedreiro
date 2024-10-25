package com.rn.tuaobraparapedreiro.ui.contato

import com.rn.tuaobraparapedreiro.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.rn.tuaobraparapedreiro.model.Cliente
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContatoViewModel : ViewModel() {

    private val _clientesPedreiroDemanda = MutableLiveData<List<Cliente>>()
    val clientes: LiveData<List<Cliente>> = _clientesPedreiroDemanda
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


   fun fetchClientesVinculadoPedreiroDemanda(email : String){
       RetrofitClient.instance.listarClientesVinculadoPedreiroDemanda(email).enqueue(object : Callback<List<Cliente>> {
           override fun onResponse(call: Call<List<Cliente>>, response: Response<List<Cliente>>) {
              if(response.isSuccessful){
                  _clientesPedreiroDemanda.value = response.body() ?: emptyList()
              }else{
                  _error.value = "Erro ao buscar contatos: ${response.message()}"
              }
           }

           override fun onFailure(call: Call<List<Cliente>>, t: Throwable) {
               Log.i("Falha contatos: ", "${t.message}")
               _error.value = "Falha de conexão: ${t.message}"
           }

       })
   }

}