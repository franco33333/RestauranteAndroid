package com.example.loginripa.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginripa.R
import com.example.loginripa.activities.data.entity.Food

class FoodAdapterMorePlates(var foods: List<Food>, var onClickFood: OnClickFood) :
    RecyclerView.Adapter<FoodAdapterMorePlates.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodAdapterMorePlates.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_more_plates, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodAdapterMorePlates.ViewHolder, position: Int) {
        holder.onBind(foods[position])
    }

    override fun getItemCount(): Int {
        return foods.size;
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var tvNombreComida = view.findViewById<TextView>(R.id.tvNombreComidaMore)
        var tvPrecioComida = view.findViewById<TextView>(R.id.tvPrecioComidaMore)
        var tvApto = view.findViewById<TextView>(R.id.tvAptoMore)
        var imgComidaMore = view.findViewById<ImageView>(R.id.imgComidaMore)
        var tvAptoMore = view.findViewById<TextView>(R.id.tvAptoMore)

        fun onBind(food: Food) {
            tvNombreComida.text = food.title
            tvPrecioComida.text = "$${food.pricePerServing}"
            if (!food.glutenFree) {
                tvApto.visibility = View.GONE
                tvAptoMore.visibility = View.GONE
            }


            itemView.setOnClickListener {
                onClickFood.onClickFood(food)
            }

            Glide
                .with(view.context)
                .load(food.image)
                .into(imgComidaMore)
        }
    }

    interface OnClickFood {
        fun onClickFood(food: Food)
    }
}