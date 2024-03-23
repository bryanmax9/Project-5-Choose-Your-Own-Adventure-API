package com.example.marsroverapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

data class MarsPhoto(val imageUrl: String, val description: String)

class MainActivity : AppCompatActivity() {

    private val photosList = mutableListOf<MarsPhoto>()
    private var currentPhotoIndex = 0

    private lateinit var imageView: ImageView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.nextImage)
        imageView = findViewById<ImageView>(R.id.marsPhoto)
        descriptionTextView = findViewById<TextView>(R.id.imageDescription)

        getMarsImageURL()

        button.setOnClickListener {
            loadNextImage()
        }
    }

    private fun loadNextImage() {
        if (currentPhotoIndex >= photosList.size) {
            getMarsImageURL()
            return
        }

        val marsPhoto = photosList[currentPhotoIndex]
        Glide.with(this)
            .load(marsPhoto.imageUrl)
            .fitCenter()
            .into(imageView)

        descriptionTextView.text = marsPhoto.description

        currentPhotoIndex = (currentPhotoIndex + 1) % photosList.size
    }

    private fun getMarsImageURL() {
        // Only fetch more photos if the list is empty
        if (photosList.isNotEmpty()) return

        val client = AsyncHttpClient()
        val apiKey = "dgTkYYl5myow4ueF5hVaEf0t9XFl8ssly5zzaQtI" // Replace with your actual API key
        val url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-6-3&api_key=$apiKey"

        client[url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("API Response", json.toString())
                try {
                    val photosArray = json.jsonObject.getJSONArray("photos")
                    for (i in 0 until photosArray.length()) {
                        val photoObject = photosArray.getJSONObject(i)
                        if (photoObject.has("img_src")) {
                            val imgSrc = photoObject.getString("img_src")
                            val cameraObject = photoObject.getJSONObject("camera")
                            val description = cameraObject.getString("full_name")
                            photosList.add(MarsPhoto(imgSrc, description))
                        }
                    }
                    // After fetching, load the first image
                    loadNextImage()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Log.e("JSON Parsing Error", "Error: " + e.localizedMessage)
                }
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

