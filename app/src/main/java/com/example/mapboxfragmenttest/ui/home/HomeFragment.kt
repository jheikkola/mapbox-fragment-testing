package com.example.mapboxfragmenttest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mapboxfragmenttest.R
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.maps.Style.MAPBOX_STREETS
import com.mapbox.mapboxsdk.style.sources.CustomGeometrySource
import com.mapbox.mapboxsdk.style.sources.GeometryTileProvider
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Mapbox.getInstance(requireActivity(), getString(R.string.mapbox_access_token))
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapview.onCreate(savedInstanceState)

        mapview.getMapAsync() {
            it.setStyle(Style.Builder()
                .fromUri(MAPBOX_STREETS)
                .withSource(CustomGeometrySource("SOURCE_ID", CustomTileProvider()))
            )
        }
    }

    override fun onStop() {
        super.onStop()
        mapview.onStop()
    }

    override fun onStart() {
        super.onStart()
        mapview.onStart()
    }

    override fun onPause() {
        super.onPause()
        mapview.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapview.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapview.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapview.onDestroy()
    }
}

class CustomTileProvider : GeometryTileProvider {
    override fun getFeaturesForBounds(bounds: LatLngBounds?, zoomLevel: Int): FeatureCollection {
        Thread.sleep(500) // simulate work
        return FeatureCollection.fromFeatures(listOf<Feature>())
    }
}