package com.example.swipeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.swipeview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: SavedAddressAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvLocations.adapter = SavedAddressAdapter(this)
    }
    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        if (adapter != null) {
            adapter!!.saveStates(outState)
        }
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (adapter != null) {
            adapter!!.restoreStates(savedInstanceState)
        }
    }
}