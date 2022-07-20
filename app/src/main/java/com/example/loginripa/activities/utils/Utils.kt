package com.example.loginripa.activities.utils

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.loginripa.R
import com.example.loginripa.activities.data.entity.Food
import com.example.loginripa.activities.network.ApiClient
import com.example.loginripa.activities.network.responses.FoodResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Objeto para funciones que se repiten en el código
 */
object Utils {

    /**
     * Muestra mensaje de error para cuando un recycler view no obtiene sus listados
     * @param recyclerView: El RecyclerView que va a ser ocultado
     * @param textViewMessage: El TextView donde se muestra el mensaje de error
     */
    fun showErrorMessage(recyclerView: RecyclerView, textViewMessage: TextView) {
        recyclerView.visibility = View.GONE
        textViewMessage.visibility = View.VISIBLE
        textViewMessage.text = textViewMessage.context.getString(R.string.error_comidas)
    }

    /**
     * Esconde mensaje de error
     * @param recyclerView: El RecyclerView que va a ser mostrado
     * @param textViewMessage: El TextView que va a ser ocultado
     */
    fun hideErrorMessage(recyclerView: RecyclerView, textViewMessage: TextView) {
        recyclerView.visibility = View.VISIBLE
        textViewMessage.visibility = View.GONE
    }

    /**
     * Obtiene la lista de comidas desde la API y las setea en el RecyclerView
     * @param resultQuantity: Cantidad de comidas a obtener
     * @param recyclerView: RecyclerView donde se setean las comidas
     * @param textViewMessage: TextView donde se mostrarán los errores
     * @param setFoodList: Funcion/Accion a realizar con la lista de resultados
     */
    fun getFoods(
        resultQuantity: Int, recyclerView: RecyclerView, textViewMessage: TextView,
        setFoodList: (foodList: List<Food>) -> Unit
    ) {
        val service = ApiClient.getServiceClient()
        val call = service.getFoods(resultQuantity)
        val callback = object : Callback<FoodResponse> {
            override fun onResponse(
                call: Call<FoodResponse>,
                response: Response<FoodResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        hideErrorMessage(recyclerView, textViewMessage)
                        val foodList = it.recipes
                        setFoodList(foodList)
                    }
                } else {
                    showErrorMessage(recyclerView, textViewMessage)
                }
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                //Log
                t.printStackTrace()

                showErrorMessage(recyclerView, textViewMessage)
            }
        }
        call.enqueue(callback)
    }

    /**
     * Setea la visibilidad y funcionalidad de la toolbar
     * @param appCompatActivity: Activity donde mostrar la toolbar
     * @param toolbar: Toolbar a mostrar
     */
    fun setToolBar(appCompatActivity: AppCompatActivity, toolbar: androidx.appcompat.widget.Toolbar) {
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            appCompatActivity.onBackPressed()
        }
    }
}