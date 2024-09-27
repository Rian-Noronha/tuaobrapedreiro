package com.rn.tuaobraparapedreiro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rn.tuaobraparapedreiro.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.txtCadastrar.setOnClickListener{
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

    }
}