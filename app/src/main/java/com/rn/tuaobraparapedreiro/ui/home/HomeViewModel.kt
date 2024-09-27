package com.rn.tuaobraparapedreiro.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rn.tuaobraparapedreiro.model.Demanda
import com.rn.tuaobraparapedreiro.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _demandas = MutableLiveData<List<Demanda>>()
    val demandas: LiveData<List<Demanda>> = _demandas

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


    init{
        fetchDemandas()
    }


    private fun fetchDemandas(){
        RetrofitClient.instance.getDemandas().enqueue(object: Callback<List<Demanda>>{
            override fun onResponse(call: Call<List<Demanda>>, response: Response<List<Demanda>>) {
                if(response.isSuccessful) {
                    _demandas.value = response.body() ?: emptyList()
                }else{
                    _error.value = "Erro ao buscar demandas: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<Demanda>>, t: Throwable) {
                 Log.i("Falha demanda: ", "${t.message}")
                _error.value = "Falha de conexão: ${t.message}"
            }

        })
    }

}