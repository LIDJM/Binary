package com.example.poi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    val images = arrayOf(
        R.drawable.catedral,
        R.drawable.catedral,
        R.drawable.catedral,
        R.drawable.catedral,
        R.drawable.catedral,
    )

    val titles = arrayOf(
        "Arco del Triunfo",
        "Catedral de Notre Dam",
        "Museo del Louvre",
        "Palacio de Versalles",
        "Torre Eiffel"
    )

    val description = arrayOf(
        "El Arco de Triunfo celebra los ejércitos de la Revolución y el Imperio",
        "La Catedral de Notre Dam es una de las catedrales góticas más antiguas del mundo.",
        "Ven y descubre el museo de arte más grande del mundo",
        "Una verdadera obra maestra del siglo XVII ",
        "Símbolo de París y Francia en todo el mundo"
    )


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v= LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_list, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(objeto: ViewHolder, i: Int) {
        objeto.itemImage.setImageResource(images[i])
        objeto.itemTitle.text=titles[i]
        objeto.itemDescription.text=description[i]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDescription: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDescription = itemView.findViewById(R.id.item_description)

        }

    }




}