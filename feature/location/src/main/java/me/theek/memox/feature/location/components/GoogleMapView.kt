package me.theek.memox.feature.location.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import me.theek.memox.core.util.LocationState

@Composable
internal fun GoogleMapView(
    locationStream: LocationState,
    onCurrentLocationClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var isMapLoaded by remember { mutableStateOf(false) }
    val cameraPosition = rememberCameraPositionState()

    Box(modifier = modifier.fillMaxSize()) {

        AnimatedVisibility(
            modifier = Modifier.matchParentSize(),
            visible = !isMapLoaded,
            exit = fadeOut()
        ) {
            Column (
                modifier = Modifier
                    .matchParentSize()
                    .background(MaterialTheme.colorScheme.background)
                    .navigationBarsPadding()
                    .statusBarsPadding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(strokeCap = StrokeCap.Round)
                Text(text = "Please wait map is loading...")
            }
        }

        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPosition,
            onMapLoaded = { isMapLoaded = true },
            properties = MapProperties(
                isMyLocationEnabled = true,
                mapType = MapType.HYBRID
            ),
            uiSettings = MapUiSettings(
                compassEnabled = true,
                zoomControlsEnabled = false
            )
        )

        if (isMapLoaded) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.3f))
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                FilledTonalButton(onClick = onCurrentLocationClick) {
                    Text(text = "Current Location")
                }
            }
        }
    }

    when (locationStream){
        is LocationState.Failure -> {
            println("Failure: ${locationStream.message}")
        }
        LocationState.Idle -> {
            println("Location Idle...")
        }
        LocationState.Loading -> {
            println("Location Loading...")
        }
        is LocationState.Success -> {
            println("Location Success")
            LaunchedEffect(key1 = locationStream) {
                locationStream.data?.let {
                    cameraPosition.animate(
                        update = CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 16f),
                        durationMs = 1000
                    )
                }
            }
        }
    }


}