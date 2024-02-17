package com.example.apkjornada

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.apkjornada.ui.theme.ApkjornadaTheme

public class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, login::class.java)
        startActivity(intent)


        finish()
    }
}
