package com.example.submission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var rvDogs: RecyclerView
    private val list = ArrayList<Dog>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)

        title = "Choose Your Doggy"

        rvDogs = binding.rvDogs
        rvDogs.setHasFixedSize(true)

        list.addAll(getListDogs())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveToAboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveToAboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListDogs(): ArrayList<Dog> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataGender = resources.getStringArray(R.array.data_gender)
        val dataAge = resources.getIntArray(R.array.data_age)
        val dataBreed = resources.getStringArray(R.array.data_breed)
        val dataSize = resources.getStringArray(R.array.data_size)
        val dataPersonality = resources.getStringArray(R.array.data_personality)
        val dataActivityLevel = resources.getStringArray(R.array.data_activity_level)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listDogs = ArrayList<Dog>()

        for (i in dataName.indices) {
            val dog = Dog(dataName[i], dataAge[i], dataBreed[i], dataGender[i], dataActivityLevel[i], dataSize[i], dataPersonality[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listDogs.add(dog)
        }

        return listDogs
    }

    private fun showRecyclerList() {
        rvDogs.layoutManager = LinearLayoutManager(this)
        rvDogs.adapter = ListDogAdapter(list)
    }
}