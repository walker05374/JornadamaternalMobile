package com.example.apkjornada

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apkjornada.databinding.ExcluirgestanteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ExcluirGestanteActivity : AppCompatActivity() {

    private lateinit var binding: ExcluirgestanteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ExcluirgestanteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("gestantes")

        binding.deletarbotao.setOnClickListener {
            val cpf = binding.cpfEditText.text.toString()
            if (cpf.isNotEmpty()) {
                deleteData(cpf)
            } else {
                Toast.makeText(this, "Digite o CPF para exclusão", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(cpf: String) {
        databaseReference.child(cpf).removeValue().addOnSuccessListener {
            binding.cpfEditText.text?.clear()
            Toast.makeText(this, "Cadastro excluído com sucesso!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Erro ao excluir cadastro!", Toast.LENGTH_SHORT).show()
        }
    }
}
