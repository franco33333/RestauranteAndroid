package com.example.loginripa.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginripa.R
import com.example.loginripa.activities.data.entity.Food

class FoodAdapter(private var foods: List<Food>, var onClickFood: OnClickFood) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodAdapter.ViewHolder, position: Int) {
        holder.onBind(foods[position])
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var tvNombreComida: TextView = view.findViewById<TextView>(R.id.tvNombreComida)
        private var tvDescripcionComida: TextView = view.findViewById<TextView>(R.id.tvDescripcionComida)
        private var tvPrecioComida: TextView = view.findViewById<TextView>(R.id.tvPrecioComida)
        private var btVerDetalle: Button = view.findViewById<Button>(R.id.btVerDetalle)
        private var tvApto: TextView = view.findViewById<TextView>(R.id.tvApto)
        private var imgComida: ImageView = view.findViewById<ImageView>(R.id.imgComida)

        fun onBind(food: Food) {
            tvNombreComida.text = food.title
            tvDescripcionComida.text = food.summary

            val cadena = tvPrecioComida.context.getString(R.string.precio_comida)
            tvPrecioComida.text = cadena.format(food.pricePerServing.toString())
            if (!food.glutenFree) {
                tvApto.visibility = View.GONE
            }

            btVerDetalle.setOnClickListener {
                onClickFood.onClickFood(food)
            }

            Glide
                .with(view.context)
                .load(food.image)
                .placeholder(R.drawable.donas)
                .into(imgComida)
        }
    }

    interface OnClickFood {
        fun onClickFood(food: Food)
    }
}