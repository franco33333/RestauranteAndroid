package com.example.loginripa.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginripa.activities.adapters.FoodAdapterMorePlates
import com.example.loginripa.activities.data.entity.Food
import com.example.loginripa.activities.utils.Utils
import com.example.loginripa.activities.utils.Utils.setToolBar
import com.example.loginripa.databinding.ActivityMorePlatesPageBinding

class MorePlatesPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMorePlatesPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMorePlatesPageBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setToolBar(this@MorePlatesPageActivity, binding.tbToolbar)

        Utils.getFoods(5, binding.rvComidas, binding.tvMessage, ::setFoodList)
    }

    private fun setFoodList(foods: List<Food>) {
        val adapter = FoodAdapterMorePlates(foods, object : FoodAdapterMorePlates.OnClickFood {
            override fun onClickFood(food: Food) {
                val i = Intent(this@MorePlatesPageActivity, DetailsFoodActivity::class.java)
                i.putExtra(DetailsFoodActivity.FOOD_KEY, food)
                startActivity(i)
            }
        })
        binding.rvComidas.adapter = adapter
        binding.rvComidas.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL,
            false
        )
    }
}