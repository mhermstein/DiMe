# DiMe — Distance Measurement

DiMe is an Android app for measuring distances, areas, and perimeters in the field. It combines camera-based trigonometry, GPS tracking with OpenStreetMap, and a digital ruler into a single offline-capable tool.

---

## Features

**Camera Measurement**
Point the camera at a target and use trigonometric calculations to estimate distance. No internet required.

**GPS & Map Measurement**
Place waypoints on a live OpenStreetMap and measure distances or polygon areas/perimeters directly on the map. Works online and offline (tile caching via OSMDroid).

**Digital Ruler**
An on-screen ruler that can be zoomed and rotated — useful for measuring physical objects held up to the screen.

**Measurement History**
All measurements are saved locally in a Room database. Entries can be reviewed, edited, and deleted. Supports export to PDF and CSV.

**Unit Conversion**
Switch between metric and imperial units on the fly: meter, kilometer, centimeter, millimeter, feet, inch, yard, mile.

**Dark Mode**
Full dark/light mode toggle, built on Material3 dynamic theming.

**Coordinate Display**
GPS coordinates shown in both decimal degrees and DMS (degrees, minutes, seconds).

---

## Architecture

DiMe follows **MVVM** with a clean separation of concerns:

```
UI (Compose Screens)
    ↓
ViewModel (MeasurementViewModel)
    ↓
Repository (MeasurementRepository)
    ↓
Room DAO → Entity → Domain Model
```

- **UI layer**: Jetpack Compose with Material3, Navigation Compose
- **State**: Kotlin StateFlow / Coroutines
- **Persistence**: Room with KSP annotation processing
- **Domain model**: `Measurement`, `MeasurementType`, `MeasurementUnit`

---

## Screens

| Screen | Purpose |
|---|---|
| Home | Dashboard with quick-access cards |
| Camera | Camera preview + trigonometric measurement |
| GPS / Map | OSMDroid map with waypoint placement |
| Ruler | On-screen digital ruler |
| History | Saved measurements list with edit/delete |
| Settings | Unit selection, dark mode, online/offline toggle |

---

## Libraries & Dependencies

| Library | Version | Purpose |
|---|---|---|
| Jetpack Compose BOM | 2023.10.01 | Declarative UI framework |
| Material3 | (via BOM) | Design system & components |
| Material Icons Extended | (via BOM) | Icon set |
| Navigation Compose | 2.7.0 | Screen navigation |
| AndroidX Core KTX | 1.12.0 | Kotlin extensions for Android |
| Lifecycle Runtime KTX | 2.6.1 | Lifecycle-aware coroutines |
| Activity Compose | 1.7.1 | Compose entry point |
| Room Runtime | 2.5.2 | Local SQLite database |
| Room KTX | 2.5.2 | Coroutine support for Room |
| CameraX (camera2) | 1.2.0 | Camera hardware access |
| CameraX Lifecycle | 1.2.0 | Lifecycle-bound camera |
| CameraX View | 1.2.0 | PreviewView composable wrapper |
| Google Play Services Location | 21.0.1 | Fused location provider (GPS) |
| OSMDroid | 6.1.13 | OpenStreetMap rendering & tile cache |
| Kotlin Coroutines | (via KTX) | Async / reactive programming |
| KSP | 1.9.10-1.0.13 | Annotation processor for Room |

---

## Resources & Data Sources

- **Map tiles**: © [OpenStreetMap contributors](https://www.openstreetmap.org/copyright), served via `tile.openstreetmap.org`
- **Offline tiles**: Cached locally by OSMDroid (no additional configuration needed)
- **Earth radius constant**: 6,371,000 m (used in Haversine & spherical excess calculations)

---

## Licenses

| Component | License |
|---|---|
| AndroidX libraries (Compose, Room, CameraX, etc.) | [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) |
| OSMDroid | [Apache License 2.0](https://github.com/osmdroid/osmdroid/blob/master/LICENSE) |
| OpenStreetMap tile data | [ODbL 1.0](https://opendatacommons.org/licenses/odbl/) |
| Google Play Services Location | [Google APIs Terms of Service](https://developers.google.com/terms) |
| Kotlin Standard Library | [Apache License 2.0](https://github.com/JetBrains/kotlin/blob/master/license/LICENSE.txt) |
| KSP (Kotlin Symbol Processing) | [Apache License 2.0](https://github.com/google/ksp/blob/main/LICENSE) |

---

*Made with Kotlin & Jetpack Compose.*
