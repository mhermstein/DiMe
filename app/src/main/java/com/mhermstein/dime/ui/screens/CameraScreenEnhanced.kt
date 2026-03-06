// CameraScreenEnhanced.kt

package com.mhermstein.dime.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraX
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.ar.core.Frame
import com.google.ar.core.Point
import com.google.ar.core.Session
import androidx.camera.view.PreviewView
import com.mhermstein.dime.R

class CameraScreenEnhanced : AppCompatActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_screen_enhanced)

        previewView = findViewById(R.id.preview_view)
        setUpCameraPermissions()
    }

    private fun setUpCameraPermissions() {
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                startCamera()
            } else {
                // Handle permission denial
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            // Setup the camera preview
            CameraX.bindToLifecycle(this, cameraSelector)
        }, ContextCompat.getMainExecutor(this))
    }

    private fun detectPoints(frame: Frame) {
        val updatedPoints: List<Point> = frame.getUpdatedTrackables(Point::class.java)
        // Process detected points here
    }
}