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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadastrar.setOnClickListener {
            cadastrarPedreiro()
        }
    }


    private fun cadastrarPedreiro() {
        if (validarCamposPreenchidos()) {
            auth.createUserWithEmailAndPassword(
                binding.edtEmail.text.toString(),
                binding.edtSenha.text.toString()
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val endereco = Endereco(
                            cep = binding.edtCEP.text.toString(),
                            nomeLugar = binding.edtNomeLugar.text.toString(),
                            numero = binding.edtNumero.text.toString()
                        )

                        val currentUser = FirebaseAuth.getInstance().currentUser
                        val firebaseUid = currentUser?.uid
                            ?: throw IllegalStateException("Usuário não autenticado")

                        val pedreiro = Pedreiro(
                            nome = binding.edtNome.text.toString(),
                            email = binding.edtEmail.text.toString(),
                            contatoWhatsApp = binding.edtWhastApp.text.toString(),
                            descricao = binding.edtDescricao.text.toString(),
                            endereco = endereco,
                            firebaseUid = firebaseUid
                        )

                        RetrofitClient.instance.cadastrarPedreiro(pedreiro)
                            .enqueue(object : Callback<Void> {
                                override fun onResponse(
                                    call: Call<Void>,
                                    response: Response<Void>
                                ) {
                                    if (response.isSuccessful) {
                                        auth.signInWithEmailAndPassword(
                                            binding.edtEmail.text.toString(),
                                            binding.edtSenha.text.toString()
                                        ).addOnCompleteListener { signInTask ->
                                            if (signInTask.isSuccessful) {
                                                Log.d(
                                                    "FirebaseUID",
                                                    "UID do Firebase: $firebaseUid"
                                                )
                                                Toast.makeText(
                                                    this@CadastroActivity,
                                                    "Cadastro e login realizados com sucesso!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                finish()
                                            } else {
                                                val errorMessage = when (response.code()) {
                                                    400 -> "Erro nos dados enviados. Por favor, verifique as informações."
                                                    409 -> "E-mail já cadastrado."
                                                    else -> "Erro ao cadastrar: ${response.message()}"
                                                }
                                                Toast.makeText(
                                                    this@CadastroActivity,
                                                    errorMessage,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }

                                    } else {
                                        Toast.makeText(
                                            this@CadastroActivity,
                                            "Erro ao cadastrar: ${response.message()}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    Toast.makeText(
                                        this@CadastroActivity,
                                        "Falha na conexão: ${t.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Log.i("Info: ", t.message.toString())
                                }

                            })
                    } else {
                        Toast.makeText(
                            this,
                            "Erro ao criar usuário no Firebase: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }


    private fun validarCamposPreenchidos(): Boolean {
        val nome = binding.edtNome.text.toString().isNotBlank()
        val email = binding.edtEmail.text.toString().isNotBlank()
        val senha = binding.edtSenha.text.toString().isNotBlank()
        val confirmarSenha = binding.edtConfirmarSenha.text.toString().isNotBlank()
        val whatsApp = binding.edtWhastApp.text.toString().isNotBlank()
        val cep = binding.edtCEP.text.toString().isNotBlank()
        val nomeLugar = binding.edtNomeLugar.text.toString().isNotBlank()
        val numero = binding.edtNumero.text.toString().isNotBlank()

        if (nome && email && senha && confirmarSenha && whatsApp && cep && nomeLugar && numero) {
            return true
        } else {
            Toast.makeText(
                binding.root.context,
                "Por favor, preencha todos os campos.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
    }

    private fun validarSenhasIguais(): Boolean {
        return if (binding.edtSenha.text.toString() == binding.edtConfirmarSenha.text.toString()) {
            true
        } else {
            Toast.makeText(binding.root.context, "As senhas não coincidem!", Toast.LENGTH_SHORT)
                .show()
            false
        }
    }


    private fun validarCampos(): Boolean {
        return validarCamposPreenchidos() && validarSenhasIguais()
    }

}