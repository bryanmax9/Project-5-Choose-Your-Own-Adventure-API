package com.example.marsroverapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private val photosList = mutableListOf<String>()
    private var currentPhotoIndex = 0

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.nextImage)
        imageView = findViewById<ImageView>(R.id.marsPhoto)

        getMarsImageURL()

        button.setOnClickListener {
            // Load the next image on double click
            loadNextImage()
        }
    }

    private fun loadNextImage() {
        // If there are no more photos in the list, fetch new ones
        if (currentPhotoIndex >= photosList.size) {
            getMarsImageURL()
            return
        }

        val imageUrl = photosList[currentPhotoIndex]
        Glide.with(this)
            .load(imageUrl)
            .fitCenter()
            .into(imageView)

        // Increment the index or reset if we've reached the end of the list
        currentPhotoIndex = (currentPhotoIndex + 1) % photosList.size
    }

    private fun getMarsImageURL() {
        // Only fetch more photos if the list is empty
        if (photosList.isNotEmpty()) return

        val client = AsyncHttpClient()
        val apiKey = "dgTkYYl5myow4ueF5hVaEf0t9XFl8ssly5zzaQtI" // Use your API key
        val url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-6-3&api_key=$apiKey"

        client[url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val photosArray = json.jsonObject.getJSONArray("photos")
                for (i in 0 until photosArray.length()) {
                    val photoObject = photosArray.getJSONObject(i)
                    val imgSrc = photoObject.getString("img_src")
                    photosList.add(imgSrc)
                }
                // After fetching, load the first image
                loadNextImage()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Nasa Error", errorResponse)
            }
        }]
    }
}
