package com.example.applistfruit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var listFruit = mutableListOf<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvListFruit)
        tvTitle = findViewById(R.id.textViewTitle)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(listFruit,this)
        recyclerView.adapter = adapter


        adapter.onItemClickListener = { fruit ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("fruit", fruit)
            startActivity(intent)
        }

        getFruit()
    }

    private fun getFruit() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getAllFruit("all")
            val response = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    val fruit = response
                    fruit?.forEach {
                        listFruit.add(it)
                    }
                    tvTitle.text = "Listado de Frutas"

                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = "https://fruityvice.com/api/fruit/"
    }
}