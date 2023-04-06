package com.example.submission

import android.content.Intent
import android.media.Image
import android.os.Binder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import com.example.submission.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_DOG = "extra_dog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)

        supportActionBar?.setHomeButtonEnabled(true);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val dog = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DOG, Dog::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DOG)
        }

        if (dog != null) {
            title = dog.name

            binding.tvName.text = dog.name
            binding.tvGender.text = dog.gender
            binding.tvDescription.text = dog.description
            binding.tvBreed.text = dog.breed
            binding.tvActivityLevel.text = dog.activityLevel
            binding.tvPersonality.text = dog.personality
            binding.tvSize.text = dog.size
            val dogAgeText = dog.age.toString() + " years old"
            binding.tvAge.text = dogAgeText
            binding.imgDog.setImageResource(dog.photo)

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "I love this dog named ${dog.name}!")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)

            binding.actionShare.setOnClickListener {
                startActivity(shareIntent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}