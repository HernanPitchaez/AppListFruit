package com.example.applistfruit

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var tvNombre: TextView
    private lateinit var tvCalorias: TextView
    private lateinit var tvGrasas: TextView
    private lateinit var tvAzucares: TextView
    private lateinit var tvCarbohidratos: TextView
    private lateinit var tvProteinas: TextView
    private lateinit var ivFruta: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val fruit = intent.getParcelableExtra<Fruit>("fruit")

        ivFruta = findViewById(R.id.ivFruta)
        tvNombre = findViewById(R.id.tvNombre)
        tvCalorias = findViewById(R.id.tvCalorias)
        tvGrasas = findViewById(R.id.tvGrasas)
        tvAzucares = findViewById(R.id.tvAzucares)
        tvCarbohidratos = findViewById(R.id.tvCarbohidratos)
        tvProteinas = findViewById(R.id.tvProteinas)

        tvNombre.text = fruit?.name
        tvCalorias.text = "Calorias: " + fruit?.nutritions?.calories.toString()
        tvGrasas.text = "Grasas: " + fruit?.nutritions?.fat.toString()
        tvAzucares.text = "Azucares: " + fruit?.nutritions?.sugar.toString()
        tvCarbohidratos.text = "Carbohidratos: " + fruit?.nutritions?.carbohydrates.toString()
        tvProteinas.text = "Proteinas: " + fruit?.nutritions?.protein.toString()

        Glide.with(this).load(fruit?.image).into(ivFruta)
    }
}