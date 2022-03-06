package com.snarut.multidirection

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.BuildConfig
import com.snarut.multidirection.databinding.ItemViewBinding

enum class ItemDirection {
    TopLeftHorizontal,
    TopLeftVertical,
    TopRightHorizontal,
    TopRightVertical,
    BottomLeftHorizontal,
    BottomLeftVertical,
    BottomRightHorizontal,
    BottomRightVertical
}

private const val TAG = "MultiDirectionalAdapter"

class MutiDirectionalAdapter(
    private val context: Context,
) : RecyclerView.Adapter<MutiDirectionalAdapter.ViewHolder>() {

    private val numOfColumns = 5
    private var _numOfItems = 23
    private var _direction: ItemDirection = ItemDirection.TopLeftHorizontal

    lateinit var itemsMap: List<Int>

    init {
        generateItems(_direction)
    }

    fun generateItems(direction: ItemDirection) {
        _direction = direction

        constructItemsMap()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)

        return ViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position)
    }

    override fun getItemCount(): Int {

        return itemsMap.size
    }

    //fun getNumOfColumn (numOfItems: Int, row: Int) = numOfItems/row + if (numOfItems%row > 0) 1 else 0

    fun getNumOfRow (numOfItems: Int, col: Int) = numOfItems/col + if (numOfItems%col > 0) 1 else 0

    private fun constructItemsMap() {

        val items = mutableListOf<Int>()
        for (i in 0.._numOfItems-1) {
            items.add(i)
        }

        val numRow = getNumOfRow(_numOfItems, numOfColumns)
        val numCol = numOfColumns

        when(_direction) {

            ItemDirection.TopLeftHorizontal -> {
                itemsMap = items
            }

            ItemDirection.TopLeftVertical -> {

                var index = 0
                val colMap = mutableListOf<MutableList<Int>>()
                for (col in 0..(numCol-1)) {
                    val rowMap = mutableListOf<Int>()
                    for (row in 0..(numRow-1)) {
                        if (index < _numOfItems)
                            rowMap.add(index)
                        else
                            rowMap.add(-1)
                        index++

                    }
                    colMap.add(rowMap)
                }

                val tempList = mutableListOf<Int>()

                for (row in 0..(numRow -1)) {
                    for (col in 0..(numCol-1)) {
                        val rowMap = colMap[col]
                        tempList.add(rowMap[row])
                    }
                }

                itemsMap = tempList
            }

            ItemDirection.TopRightHorizontal -> {

                var index = 0
                val rowMap = mutableListOf<MutableList<Int?>>()
                for (row in 0..(numRow-1)) {
                    val colMap = MutableList<Int?>(numCol) { null }
                    for (col in (numCol-1) downTo 0) {
                        if (index < _numOfItems)
                            colMap[col] = index
                        else
                            colMap[col] = -1
                        index++

                    }
                    rowMap.add(colMap)
                }

                val tempList = mutableListOf<Int>()

                for (row in 0..(numRow -1)) {
                    for (col in 0..(numCol-1)) {
                        val colMap = rowMap[row]
                        tempList.add(colMap[col]!!)
                    }
                }

                itemsMap = tempList
            }

            ItemDirection.TopRightVertical -> {

                var index = 0
                val colMap = MutableList<MutableList<Int>?>(numCol) { null }
                for (col in (numCol-1) downTo 0) {
                    val rowMap = mutableListOf<Int>()
                    for (row in 0..(numRow-1)) {
                        if (index < _numOfItems)
                            rowMap.add(items[index])
                        else
                            rowMap.add(-1)
                        index++

                    }
                    colMap[col] = rowMap
                }

                val tempList = mutableListOf<Int>()

                for (row in 0..(numRow -1)) {
                    for (col in 0..(numCol-1)) {
                        val rowMap = colMap[col]
                        tempList.add(rowMap!![row]!!)
                    }
                }

                itemsMap = tempList
            }

            ItemDirection.BottomLeftVertical -> {

                var index = 0
                val colMap = mutableListOf<MutableList<Int?>>()
                for (col in 0..(numCol-1)) {
                    val rowMap = MutableList<Int?>(numRow) { null }
                    for (row in (numRow-1) downTo 0) {
                        if (index < items.size)
                            rowMap[row] = (items[index])
                        else
                            rowMap[row] = (-1)
                        index++

                    }
                    colMap.add(rowMap)
                }

                val tempList = mutableListOf<Int>()

                for (row in 0..(numRow -1)) {
                    for (col in 0..(numCol-1)) {
                        val rowMap = colMap[col]
                        tempList.add(rowMap[row]!!)
                    }
                }

                itemsMap = tempList
            }

            ItemDirection.BottomRightVertical -> {

                var index = 0
                val colMap = MutableList<MutableList<Int?>?>(numCol) { null }
                for (col in (numCol-1) downTo 0) {
                    val rowMap = MutableList<Int?>(numRow) { null }
                    for (row in (numRow-1) downTo 0) {
                        if (index < items.size)
                            rowMap[row] = (items[index])
                        else
                            rowMap[row] = (-1)
                        index++

                    }
                    colMap[col] = rowMap
                }

                val tempList = mutableListOf<Int>()

                for (row in 0..(numRow -1)) {
                    for (col in 0..(numCol-1)) {
                        val rowMap = colMap[col]
                        tempList.add(rowMap!![row]!!)
                    }
                }

                itemsMap = tempList
            }

            ItemDirection.BottomLeftHorizontal -> {

                var index = 0
                val rowMap = MutableList<MutableList<Int?>?>(numRow) { null }
                for (row in (numRow-1) downTo 0) {
                    val colMap = MutableList<Int?>(numCol) { null }
                    for (col in 0..(numCol-1)) {
                        if (index < items.size)
                            colMap[col] = items[index]
                        else
                            colMap[col] = (-1)
                        index++

                    }
                    rowMap[row] = colMap
                }

                val tempList = mutableListOf<Int>()

                for (row in 0..(numRow -1)) {
                    for (col in 0..(numCol-1)) {
                        val colMap = rowMap[row]
                        tempList.add(colMap!![col]!!)
                    }
                }

                itemsMap = tempList
            }

            ItemDirection.BottomRightHorizontal -> {

                var index = 0
                val rowMap = MutableList<MutableList<Int?>?>(numRow) { null }
                for (row in (numRow-1) downTo 0) {
                    val colMap = MutableList<Int?>(numCol) { null }
                    for (col in (numCol-1) downTo 0) {
                        if (index < items.size)
                            colMap[col] = items[index]
                        else
                            colMap[col] = -1
                        index++

                    }
                    rowMap[row] = colMap
                }

                val tempList = mutableListOf<Int>()

                for (row in 0..(numRow -1)) {
                    for (col in 0..(numCol-1)) {
                        val colMap = rowMap[row]
                        tempList.add(colMap!![col]!!)
                    }
                }

                itemsMap = tempList
            }
        }

        if(BuildConfig.DEBUG) {
            for (item in itemsMap)
                Log.d(TAG, item.toString())
        }
    }

    inner class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = itemsMap[position]
            val textView = binding.textView
            if (item >= 0) { // normal item
                textView.text = item.toString()
                binding.root.visibility = View.VISIBLE
            } else { // placeholder item, display as empty space
                binding.root.visibility = View.INVISIBLE
            }
        }
    }

}



