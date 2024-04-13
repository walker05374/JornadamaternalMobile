package com.example.apkjornada

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apkjornada.databinding.AtualizargestanteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class atualizargestante : AppCompatActivity() {

    private lateinit var binding: AtualizargestanteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AtualizargestanteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.atualizarbotao.setOnClickListener {
            val customer = Customer(
                nomeGestante = binding.nomeGestanteEditText.text.toString(),
                cpf = binding.cpfEditText.text.toString(),
                sus = binding.susEditText.text.toString(),
                nomeCrianca = binding.nomeCriancaEditText.text.toString(),
                idade = binding.idadeEditText.text.toString(),
                estado = binding.estadoEditText.text.toString(),
                cidade = binding.cidadeEditText.text.toString(),
                cep = binding.cepEditText.text.toString()
            )

            updateData(customer)
        }
    }

    private fun updateData(customer: Customer) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Lista de Imoveis")
        val imovelData = mapOf(
            "nomeGestante" to customer.nomeGestante,
            "cpf" to customer.cpf,
            "sus" to customer.sus,
            "nomeCrianca" to customer.nomeCrianca,
            "idade" to customer.idade,
            "estado" to customer.estado,
            "cidade" to customer.cidade,
            "cep" to customer.cep
        )
        databaseReference.child(customer.cpf ?: "").updateChildren(imovelData)
            .addOnSuccessListener {
                clearFields()
                Toast.makeText(this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearFields() {
        binding.nomeGestanteEditText.text?.clear()
        binding.cpfEditText.text?.clear()
        binding.susEditText.text?.clear()
        binding.nomeCriancaEditText.text?.clear()
        binding.idadeEditText.text?.clear()
        binding.estadoEditText.text?.clear()
        binding.cidadeEditText.text?.clear()
        binding.cepEditText.text?.clear()
    }
}
