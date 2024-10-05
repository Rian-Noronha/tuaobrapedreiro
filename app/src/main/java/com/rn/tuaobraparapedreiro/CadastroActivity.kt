package com.rn.tuaobraparapedreiro

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rn.tuaobraparapedreiro.databinding.ActivityCadastroBinding
import com.rn.tuaobraparapedreiro.model.Endereco
import com.rn.tuaobraparapedreiro.model.Pedreiro
import com.rn.tuaobraparapedreiro.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadastrar.setOnClickListener{
            cadastrarPedreiro()
        }
    }


    private fun cadastrarPedreiro(){
        val endereco = Endereco(
            cep = binding.edtCep.text.toString(),
            nomeLugar = binding.edtNomeLugar.text.toString(),
            numero = binding.edtNumero.text.toString()
        )
        val pedreiro = Pedreiro(
            nome = binding.edtNome.text.toString(),
            email = binding.edtEmail.text.toString(),
            contatoWhatsApp = binding.edtContatoWhatsApp.text.toString(),
            descricao = binding.edtDescricao.text.toString(),
            endereco = endereco,
        )

       RetrofitClient.instance.cadastrarPedreiro(pedreiro).enqueue(object : Callback<Void> {
           override fun onResponse(call: Call<Void>, response: Response<Void>) {
              if(response.isSuccessful){
                  Toast.makeText(this@CadastroActivity, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                  finish()
              }else{
                  Toast.makeText(this@CadastroActivity, "Erro ao cadastrar: ${response.message()}", Toast.LENGTH_SHORT).show()
              }
           }

           override fun onFailure(call: Call<Void>, t: Throwable) {
               Toast.makeText(this@CadastroActivity, "Falha na conexão: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.i("Info: ", t.message.toString())
           }

       })
    }

}