package com.snarut.multidirection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import com.snarut.multidirection.databinding.ActivityMainBinding
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var rcvAdapter: MutiDirectionalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)



        val direction = ItemDirection.TopRightVertical

        // Setup RecyclerView
        val recyclerView = binding.recyclerView
        rcvAdapter = MutiDirectionalAdapter(this)

        recyclerView.adapter = rcvAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 5)
        recyclerView.addItemDecoration(GridLayoutDecoration(5, 30, false))

        val spinner = binding.spinnerDirection

        ArrayAdapter.createFromResource(
            this,
            R.array.direction_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.buttonConfirm.setOnClickListener {
            var selectedDirection = ItemDirection.TopLeftHorizontal
            when (spinner.selectedItem.toString()) {
                "TopLeftHorizontal" -> {
                    selectedDirection = ItemDirection.TopLeftHorizontal
                }
                "TopLeftVertical" -> {
                    selectedDirection = ItemDirection.TopLeftVertical
                }
                "TopRightHorizontal" -> {
                    selectedDirection = ItemDirection.TopRightHorizontal
                }
                "TopRightVertical" -> {
                    selectedDirection = ItemDirection.TopRightVertical
                }
                "BottomLeftHorizontal" -> {
                    selectedDirection = ItemDirection.BottomLeftHorizontal
                }
                "BottomLeftVertical" -> {
                    selectedDirection = ItemDirection.BottomLeftVertical
                }
                "BottomRightHorizontal" -> {
                    selectedDirection = ItemDirection.BottomRightHorizontal
                }
                "BottomRightVertical" -> {
                    selectedDirection = ItemDirection.BottomRightVertical
                }
            }
            rcvAdapter.generateItems(selectedDirection)
        }


        setContentView(binding.root)
    }
}