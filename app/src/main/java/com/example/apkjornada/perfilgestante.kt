package com.example.apkjornada

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apkjornada.databinding.ExcluirgestanteBinding
import com.example.apkjornada.databinding.AtualizargestanteBinding
import com.example.apkjornada.databinding.LercadastrosBinding
import com.example.apkjornada.databinding.ActivityEditProfileBinding

class perfilgestante : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonViewatualizar.setOnClickListener {
            val intent = Intent(this, lercadastros::class.java)
            startActivity(intent)
            finish()
        }

        binding.atualizarbotao.setOnClickListener {
            val intent = Intent(this, atualizargestante::class.java)
            startActivity(intent)
            finish()
        }

        binding.deletarbotao.setOnClickListener {
            val intent = Intent(this, excluirgestante::class.java)
            startActivity(intent)
            finish()
        }
    }
}
