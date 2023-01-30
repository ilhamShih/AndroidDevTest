package com.android.devtest.presentation.adapter


import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.devtest.R
import com.android.devtest.damain.json.Raitings
import com.bumptech.glide.Glide


class AdapterRaitingsIthems(context: Context) :
    ListAdapter<Raitings, ViewHolder>(DiffCallback()) {

    var mContext: Context = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_server_ithem, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val path: Uri =
            Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_PICTURES + "/Shihzamanapp/${position + 1}.png")
        Glide.with(mContext).load(path).into(holder.imageView)
        holder.imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        holder.titleTextView.text = getItem(position).title
        holder.titleTextView2.text = getItem(position).title
        holder.titleTextView3.text = getItem(position).title
    }

}