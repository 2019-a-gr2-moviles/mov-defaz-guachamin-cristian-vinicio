package com.example.a05_layouts_recyclerview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_ir_a_recycler_view.setOnClickListener {
            irARecyclerView()
        }

    }

    private fun irARecyclerView(){
        val intent = Intent(
            this,
            ReciclerViewActivity::class.java
        )
        startActivity(intent)
    }
}
