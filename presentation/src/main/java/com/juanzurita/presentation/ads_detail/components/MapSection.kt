package com.juanzurita.presentation.ads_detail.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.juanzurita.core.util.extensions.doubleValue
import com.juanzurita.presentation.R
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import com.google.android.gms.maps.model.CameraPosition
import com.juanzurita.domain.ad_list.models.AdDetail

@Composable
fun MapSection(adDetail: AdDetail?, mapState: MapState) {
    adDetail?.ubication?.let { location ->
        location["latitude"]?.let { lat ->
            location["longitude"]?.let { long ->
                Spacer(modifier = Modifier.height(Dimens.Spacing.large).padding(horizontal = Dimens.Spacing.extraSmall))
                MapCard(
                    latitude = lat,
                    longitude = long,
                    mapState = mapState,
                    modifier = Modifier.fillMaxWidth().height(Dimens.Map.height)
                )
            }
        }
    }
}


@Composable
fun MapCard(
    latitude: String,
    longitude: String,
    mapState: MapState, // Recibir el estado del mapa
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        AdLocationMap(
            latitude = latitude,
            longitude = longitude,
            mapState = mapState, // Pasar el estado del mapa
            modifier = Modifier.fillMaxSize()
        )
    }
}
@Composable
fun AdLocationMap(
    latitude: String,
    longitude: String,
    mapState: MapState, // Recibir el estado del mapa
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Usar el estado de la cámara desde el ámbito superior
    val cameraPositionState = remember(mapState) {
        CameraPositionState(mapState.cameraPosition)
    }

    // Marcador estable
    val markerPosition = remember(latitude, longitude) {
        LatLng(latitude.doubleValue(), longitude.doubleValue())
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = remember {
            MapProperties(
                mapType = MapType.NORMAL,
                isBuildingEnabled = true
            )
        },
        uiSettings = remember {
            MapUiSettings(
                zoomControlsEnabled = false,
                mapToolbarEnabled = false
            )
        },
        onMapClick = { openMapsApp(context, latitude, longitude) }
    ) {
        Marker(
            state = MarkerState(position = markerPosition),
            title = stringResource(R.string.map_marker_title),
            snippet = stringResource(R.string.tap_for_navigation),
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        )
    }
}
/**
 * Abre la ubicación en Google Maps o en un navegador web si la app no está instalada.
 *
 * @param context Contexto de la aplicación
 * @param latitude Latitud de la ubicación
 * @param longitude Longitud de la ubicación
 */
private fun openMapsApp(context: Context, latitude: String, longitude: String) {
    // Intent para abrir en la app de Google Maps
    val mapsIntent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
        setPackage("com.google.android.apps.maps")
    }

    // Intent alternativo para abrir en un navegador web
    val webIntent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("https://www.google.com/maps/search/?api=1&query=$latitude,$longitude")
    }

    // Intenta abrir la app de Maps, si no está disponible usa el navegador
    try {
        context.startActivity(mapsIntent)
    } catch (e: Exception) {
        context.startActivity(webIntent)
    }
}

@Composable
fun rememberMapState(ubication: Map<String?, String?>?): MapState {
    return remember {
        val lat = ubication?.getValue("latitude")?.doubleValue() ?: 0.0
        val long = ubication?.getValue("longitude")?.doubleValue() ?: 0.0
        MapState(cameraPosition = CameraPosition.fromLatLngZoom(LatLng(lat, long), Dimens.Map.zoomLevel))
    }
}

data class MapState(val cameraPosition: CameraPosition)

