package com.example.apkjornada

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apkjornada.databinding.LercadastrosBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class lercadastros : AppCompatActivity() {

    private lateinit var binding: LercadastrosBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LercadastrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("Listagem de Gestante")

        binding.buttonViewatualizar.setOnClickListener {
            val campoPesquisa: String = binding.campoPesquisa.text.toString()
            if (campoPesquisa.isNotEmpty()) {
                readData(campoPesquisa)
            } else {
                Toast.makeText(this, "Por favor entre com o seu CPF!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(cpf: String) {
        databaseReference.child(cpf).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                val nomeGestante = dataSnapshot.child("nomeGestante").value
                val cpf = dataSnapshot.child("cpf").value
                val sus = dataSnapshot.child("sus").value
                val nomeCrianca = dataSnapshot.child("nomeCrianca").value
                val idade = dataSnapshot.child("idade").value
                val cidade = dataSnapshot.child("cidade").value
                val estado = dataSnapshot.child("estado").value
                val cep = dataSnapshot.child("cep").value

                Toast.makeText(this, "Pesquisa Encontrada", Toast.LENGTH_SHORT).show()
                binding.campoPesquisa.text?.clear()
                binding.lernamegestante.setText(nomeGestante?.toString() ?: "")
                binding.lercpf.setText(cpf?.toString() ?: "")
                binding.lersus.setText(sus?.toString() ?: "")
                binding.lercrianca.setText(nomeCrianca?.toString() ?: "")
                binding.leridade.setText(idade?.toString() ?: "")
                binding.lercidade.setText(cidade?.toString() ?: "")
                binding.lerestado.setText(estado?.toString() ?: "")
                binding.lercep.setText(cep?.toString() ?: "")

            } else {
                Toast.makeText(this, "Pesquisa não encontrada!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Algo deu errado!", Toast.LENGTH_SHORT).show()
        }
    }
}
