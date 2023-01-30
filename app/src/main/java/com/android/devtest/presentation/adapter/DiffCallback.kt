package com.android.devtest.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.devtest.damain.json.Raitings

class DiffCallback : DiffUtil.ItemCallback<Raitings>() {
    override fun areItemsTheSame(oldItem: Raitings, newItem: Raitings): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Raitings, newItem: Raitings): Boolean {
       return oldItem == newItem
    }
}