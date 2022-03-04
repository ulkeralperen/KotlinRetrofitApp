package com.alperen.rickandmortykotlinfragment.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alperen.rickandmortykotlinfragment.Adapter.RecyclerViewAdapter
import com.alperen.rickandmortykotlinfragment.Model.Result
import com.alperen.rickandmortykotlinfragment.Model.rickModel
import com.alperen.rickandmortykotlinfragment.R
import com.alperen.rickandmortykotlinfragment.Service.rickAPI
import kotlinx.android.synthetic.main.fragment_first.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FirstFragment : Fragment() {



    private val BASE_URL="https://rickandmortyapi.com/api/"
    private var rickModels: rickModel?=null
    private var recyclerViewAdapter:RecyclerViewAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        var layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(context)
        recyclerview.layoutManager=layoutManager

        loadData()

    }
    private fun loadData(){
        var retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service=retrofit.create(rickAPI::class.java)
        val call=service.getData()

        call.enqueue(object : Callback<rickModel> {
            override fun onResponse(call: Call<rickModel>, response: Response<rickModel>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        rickModels =it
                       recyclerViewAdapter=RecyclerViewAdapter(it,this@FirstFragment)
                        recyclerview.adapter=recyclerViewAdapter
                        /*

                        println("Name: ${it.results[1].name}")
                        println("Gender: ${it.results[1].gender}")
                        println("Url: ${it.results[1].url}")
                        */
                    }
                }
            }

            override fun onFailure(call: Call<rickModel>, t: Throwable) {
                println(t.localizedMessage)
            }

        })
    }


    fun onItemClick(rickList: Result, view: View){

       val action=FirstFragmentDirections.actionFirstFragmentToSecondFragment(rickList.id,rickList.name,rickList.status,rickList.species,rickList.type,rickList.gender,rickList.image,rickList.url,rickList.created)
        Navigation.findNavController(view).navigate(action)


        Toast.makeText(context,"Tıklandı: ${rickList.name}",Toast.LENGTH_LONG).show()
    }


}