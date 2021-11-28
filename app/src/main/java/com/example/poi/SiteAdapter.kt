package com.example.poi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class SiteAdapter(
    private val mSites: ArrayList<Site>,
    private val context: Context,
    private val onClick: (Site) -> Unit
) : RecyclerView.Adapter<SiteAdapter.SiteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_list, parent, false)
        return SiteViewHolder(view)
    }

    override fun onBindViewHolder(holderSite: SiteViewHolder, position: Int) {
        val site = mSites[position]
        holderSite.bind(site = site)
    }

    override fun getItemCount(): Int {
        return mSites.size
    }

    inner class SiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nameLabel: TextView = itemView.findViewById(R.id.item_title)
        private var descripcionLabel: TextView = itemView.findViewById(R.id.item_description)
        private var puntuacionLabel: RatingBar = itemView.findViewById(R.id.ratingBar2)
        private var imageView: ImageView = itemView.findViewById(R.id.item_image)
        private var currentSite: Site? = null

        init {
            itemView.setOnClickListener {
                currentSite?.let {
                    onClick(it)
                }
            }
        }

        /* Bind Contact name and image. */
        fun bind(site: Site) {
            currentSite = site

            nameLabel.text = site.Nombre
            descripcionLabel.text = site.Descripcion
            puntuacionLabel.numStars = 5
            Glide.with(context)
                .load(site.ImgURL)
                .into(imageView)
        }
    }

}