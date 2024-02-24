package com.example.apkjornada

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CameraActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        imageView = findViewById(R.id.cameraView)
        val buttonOpenCamera: Button = findViewById(R.id.captureButton)

        buttonOpenCamera.setOnClickListener {
            // Antes de chamar dispatchTakePictureIntent, adicione a seguinte verificação
            if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                // Adicione esta verificação antes de chamar dispatchTakePictureIntent
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent()
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
                }
            } else {
                Toast.makeText(this, "Este dispositivo não possui uma câmera", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        try {
            Log.d("CameraActivity", "dispatchTakePictureIntent: Before starting activity")
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                Log.d("CameraActivity", "dispatchTakePictureIntent: After starting activity")
            } else {
                Log.e("CameraActivity", "dispatchTakePictureIntent: No camera app found")
                // Exiba uma mensagem para o usuário informando que nenhum aplicativo de câmera foi encontrado
                Toast.makeText(this, "Nenhum aplicativo de câmera encontrado", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e("CameraActivity", "dispatchTakePictureIntent: Error starting the camera activity", e)
            // Adicione qualquer tratamento de erro que você achar necessário
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("CameraActivity", "onActivityResult: requestCode=$requestCode, resultCode=$resultCode")

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
            Log.d("CameraActivity", "onActivityResult: Image set to ImageView")
        } else {
            Log.d("CameraActivity", "onActivityResult: Capture failed or cancelled")
        }
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 101
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CAMERA_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent()
                } else {
                    Toast.makeText(this, "Permissão de câmera negada", Toast.LENGTH_SHORT).show()
                }
            }
            // Adicione outros casos conforme necessário
        }
    }
}
