package com.example.apkjornada

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.apkjornada.ui.theme.ApkjornadaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            val intent = Intent(this@MainActivity, login::class.java)
            startActivity(intent)
            ApkjornadaTheme {

            }
        }
    }
}

