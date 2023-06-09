package com.example.mapproject

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException


class SearchMapsActivity : FragmentActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    // creating a variable
    // for search view.
    lateinit var searchView: SearchView

    var addedMarker: Marker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_maps)


        initView()

    }

    private fun initView() {
        // initializing our search view.
        searchView = findViewById(R.id.idSearchView)

        // Obtain the SupportMapFragment and get notified
        // when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?


        // adding on query listener for our search view.
        searchView.setOnQueryTextListener(/* listener = */ object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // on below line we are getting the
                // location name from search view.
                val location = searchView.query.toString()

                // below line is to create a list of address
                // where we will store the list of all address.
                var addressList: List<Address>? = null

                // checking if the entered location is null or not.
                if (location != null || location == "") {
                    // on below line we are creating and initializing a geo coder.
                    val geocoder = Geocoder(this@SearchMapsActivity)
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    val address = addressList!![0]

                    // on below line we are creating a variable for our location
                    // where we will add our locations latitude and longitude.
                    val latLng = LatLng(address.latitude, address.longitude)

                    Log.e(
                        "TAG",
                        "latitude:-  " + address.latitude + " " + "longitude:- " + address.longitude
                    )


                    // on below line we are adding marker to that position.
                    addedMarker = mMap.addMarker(MarkerOptions().position(latLng).title(location))!!

                    // below line is to animate camera to that position.
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))


                }

                return false

            }


            override fun onQueryTextChange(newText: String): Boolean {
                addedMarker?.remove()
                return false

            }

        })

        // at last we calling our map fragment to update.
        mapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {


        mMap = googleMap

    }
}
