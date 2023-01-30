package com.android.devtest.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.devtest.R

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView
    var titleTextView: TextView
    var titleTextView2: TextView
    var titleTextView3: TextView

    init {
        imageView = itemView.findViewById(R.id.image)
        titleTextView = itemView.findViewById(R.id.text_title_1)
        titleTextView2 = itemView.findViewById(R.id.text_title_2)
        titleTextView3 = itemView.findViewById(R.id.text_title_3)

    }
}