package com.example.loginripa.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginripa.activities.adapters.FoodAdapter
import com.example.loginripa.activities.data.entity.Food
import com.example.loginripa.activities.data.entity.User
import com.example.loginripa.activities.utils.Utils
import com.example.loginripa.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    companion object {
        const val USER_KEY = "USER_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        intent?.extras?.let {
            val user: User = it[USER_KEY] as User
            val texto = "Hola, ${user.username}!"
            binding.tvHola.text = texto
        }

        Utils.getFoods(5, binding.rvComidas, binding.tvMessage, ::setFoodList)

        binding.verMas.setOnClickListener {
            val i = Intent(this@HomeActivity, MorePlatesPageActivity::class.java)
            startActivity(i)
        }
    }

    private fun setFoodList(foods: List<Food>) {
        val adapter = FoodAdapter(foods, object : FoodAdapter.OnClickFood {
            override fun onClickFood(food: Food) {
                val i = Intent(this@HomeActivity, DetailsFoodActivity::class.java)
                i.putExtra(DetailsFoodActivity.FOOD_KEY, food)
                startActivity(i)
            }
        })
        binding.rvComidas.adapter = adapter
        binding.rvComidas.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,
            false
        )
    }
}