package com.example.loginripa.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat.*
import com.bumptech.glide.Glide
import com.example.loginripa.R
import com.example.loginripa.activities.data.entity.Food
import com.example.loginripa.activities.utils.Utils.setToolBar
import com.example.loginripa.databinding.ActivityDetailsFoodBinding


class DetailsFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsFoodBinding

    companion object {
        const val FOOD_KEY = "FOOD_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsFoodBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setToolBar(this@DetailsFoodActivity, binding.tbToolbar)

        val food: Food
        intent?.extras?.let {
            food = it[FOOD_KEY] as Food
            Glide
                .with(this@DetailsFoodActivity)
                .load(food.image)
                .into(binding.imgComidaDetail)
            binding.tbToolbar.title = food.title
            binding.tvNombreComidaDetail.text = food.title
            binding.tvDescripcionComidaDetail.text = fromHtml(
                food.summary,
                FROM_HTML_MODE_LEGACY
            )

            if (!food.glutenFree) {
                binding.tvApto.visibility = View.GONE
                binding.vectorCircleDetail.visibility = View.GONE
                binding.tickCircleDetail.visibility = View.GONE
            }
            val cadena = getString(R.string.precio_comida)
            binding.tvPrecioComidaDetail.text = cadena.format(food.pricePerServing.toString())

            var text = ""
            food.extendedIngredients.forEach {
                text += "- ${it.original}\n"
            }
            binding.tvIngredientes.text = text
        }
    }
}