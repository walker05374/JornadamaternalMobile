package com.example.apkjornada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            val usuario = findViewById<EditText>(R.id.login_username).text.toString()
            val senha = findViewById<EditText>(R.id.login_password).text.toString()

            if (validarCredenciais(usuario, senha)) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun validarCredenciais(usuario: String, senha: String): Boolean {
        return usuario == "admin" && senha == "admin"
    }
}
