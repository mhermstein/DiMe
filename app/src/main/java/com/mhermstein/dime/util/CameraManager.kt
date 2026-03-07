package com.mhermstein.dime.util

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import kotlin.math.sqrt

object CameraManager {

    fun setupCamera(context: Context, lifecycleOwner: LifecycleOwner) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
            try {
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build()

                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()

                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalysis
                )
            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(context))
    }

    /**
     * Placeholder: replace with real detection (e.g. ML Kit, ARCore) later.
     */
    fun detectARPoints(imageData: ByteArray): List<Pair<Float, Float>> {
        return listOf(
            100f to 100f,
            200f to 150f,
            150f to 250f
        )
    }

    fun calculateDistanceFromARPoints(point1: Pair<Float, Float>, point2: Pair<Float, Float>): Double {
        val dx = point2.first - point1.first
        val dy = point2.second - point1.second
        return sqrt((dx * dx + dy * dy).toDouble())
    }

    fun placeARMarker(x: Float, y: Float): Pair<Float, Float> = x to y
}