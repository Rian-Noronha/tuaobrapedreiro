package com.rn.tuaobraparapedreiro.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rn.tuaobraparapedreiro.model.DemandaCliente
import com.rn.tuaobraparapedreiro.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DemandaClienteViewModel : ViewModel() {
    private val _demandaCliente = MutableLiveData<DemandaCliente>()
    val demandaCliente: LiveData<DemandaCliente> = _demandaCliente

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    public fun fetchDemandaCliente(id: Long){
        RetrofitClient.instance.getDemandaCliente(id).enqueue(object: Callback<DemandaCliente>{
            override fun onResponse(
                call: Call<DemandaCliente>,
                response: Response<DemandaCliente>
            ) {
                if(response.isSuccessful){
                    _demandaCliente.value = response.body()
                }else{
                    _error.value = "Erro ao buscar demandaClient: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<DemandaCliente>, t: Throwable) {
                Log.i("Falha demandaCliente: ", "${t.message}")
                _error.value = "Falha de conexão: ${t.message}"
            }

        })
    }
}