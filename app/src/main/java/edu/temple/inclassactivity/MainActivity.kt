package edu.temple.inclassactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Fetch images into IntArray called imageArray
        val typedArray = resources.obtainTypedArray(R.array.image_ids)
        val imageArray = IntArray(typedArray.length()) {typedArray.getResourceId(it, 0)}
        typedArray.recycle()

        mainViewModel.setImageIds(imageArray)

        // Attach an instance of ImageDisplayFragment using factory method
        //Passes in imageArray as its argument
        //Adds fragment to the fragment container view using a fragment transaction
        //Then commits it
        //val imageDisplayFragment = ImageDisplayFragment.newInstance(imageArray)
        if(supportFragmentManager.findFragmentById(R.id.fragmentContainerView) !is ImageDisplayFragment) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, ImageDisplayFragment())
                .commit()
        }

    }
}