package com.example.swipeview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.swipeview.databinding.ItemSavedAddressesBinding


class SavedAddressAdapter(private val context: Context) :
    RecyclerView.Adapter<SavedAddressAdapter.SavedAddressHolder>() {
    private val binderHelper = ViewBinderHelper()

    inner class SavedAddressHolder(
        val itemView: View,
        val swipeLayout: SwipeRevealLayout,
        private val frontLayout: View,
        private val deleteLayout: View,
        private val textView: TextView,
        private val address: TextView
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data) {
            deleteLayout.setOnClickListener {
                mDataSet.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
            textView.text = data.name
            address.text = data.address
            frontLayout.setOnClickListener {
                val displayText = "$data clicked"
                Toast.makeText(context, displayText, Toast.LENGTH_SHORT).show()
                Log.d("RecyclerAdapter", displayText)
            }
        }
    }

    data class Data(val name: String, val address: String)

    private val mDataSet =
        arrayListOf(Data("dummy1", "asa"), Data("dummy1", "asa"), Data("dummy1", "asa"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedAddressHolder {
        val binding =
            ItemSavedAddressesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedAddressHolder(
            itemView = binding.root.rootView,
            swipeLayout = binding.root,
            frontLayout = binding.frontLayout,
            deleteLayout = binding.deleteLayout,
            textView = binding.tvName,
            address = binding.tvAddress
        )
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: SavedAddressHolder, position: Int) {
        if (0 <= position && position < mDataSet.size) {
            val data = mDataSet[position]

            // Use ViewBindHelper to restore and save the open/close state of the SwipeRevealView
            // put an unique string id as value, can be any string which uniquely define the data
            binderHelper.bind(holder.swipeLayout, data.address)

            // Bind your data here
            holder.bind(data)
        }
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in [android.app.Activity.onSaveInstanceState]
     */
    fun saveStates(outState: Bundle?) {
        binderHelper.saveStates(outState)
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in [android.app.Activity.onRestoreInstanceState]
     */
    fun restoreStates(inState: Bundle?) {
        binderHelper.restoreStates(inState)
    }
}