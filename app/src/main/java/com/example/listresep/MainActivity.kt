package com.example.listresep

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listresep.adapter.RecipeAdapter
import com.example.listresep.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: RecipeAdapter
    private var page : Int = 1

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        val viewModel : MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null) {
                recipeProgress.visibility = View.VISIBLE
                recyclerAdapter.setRecipeList(it)
                recyclerAdapter.notifyDataSetChanged()
                recipeProgress.visibility = View.INVISIBLE
            }else{
                Toast.makeText(this, "Error getting List", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeApiCall(page)


        recyclerviewRecipe.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE){
                    viewModel.getLiveDataObserver().observe(this@MainActivity, Observer {
                        if(it != null) {
                            recipeProgress.visibility = View.VISIBLE
                            recyclerAdapter.addRecipeList(it)
                            recyclerAdapter.notifyDataSetChanged()
                            recipeProgress.visibility = View.INVISIBLE
                        }else{
                            Toast.makeText(this@MainActivity, "Error getting List", Toast.LENGTH_LONG).show()
                        }
                    })
                    page++
                    viewModel.makeApiCall(page)
                }
            }
        })
    }

    private fun initRecyclerView(){
        recyclerviewRecipe.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = RecipeAdapter(this)
        recyclerviewRecipe.adapter = recyclerAdapter
    }

}