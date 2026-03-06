package com.mhermstein.dime.util

import kotlin.math.*

object GeoCalculations {

    fun decimalToDMS(decimal: Double): String {
        val degrees = decimal.toInt()
        val minutes = ((decimal - degrees) * 60).toInt()
        val seconds = ((decimal - degrees - minutes / 60.0) * 3600).toInt()
        return String.format("%d° %d' %d" , degrees, minutes, seconds)
    }

    fun dmsToDecimal(degrees: Int, minutes: Int, seconds: Int): Double {
        return degrees + minutes / 60.0 + seconds / 3600.0
    }

    fun distanceBetweenPoints(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371e3 // Earth's radius in meters
        val phi1 = Math.toRadians(lat1)
        val phi2 = Math.toRadians(lat2)
        val deltaPhi = Math.toRadians(lat2 - lat1)
        val deltaLambda = Math.toRadians(lon2 - lon1)
        val a = sin(deltaPhi / 2) * sin(deltaPhi / 2) + 
                cos(phi1) * cos(phi2) * 
                sin(deltaLambda / 2) * sin(deltaLambda / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c
    }

    fun calculateZoomLevel(lat: Double): Int {
        // Simplified formula for calculating zoom level based on latitude
        return (Math.log(360.0 / 256) / Math.log(2) - Math.log(Math.cos(Math.toRadians(lat))) / Math.log(2)).toInt()
    }

    fun getTileUrl(zoom: Int, x: Int, y: Int): String {
        return "https://tile.openstreetmap.org/$zoom/$x/$y.png"
    }
}