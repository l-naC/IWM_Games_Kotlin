package com.example.iwm_games_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_item.view.*

class GamesAdapter(private val communication: AdapterInterface) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ViewHolder(holder.itemView).bind(communication.games[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(game: Game){
            itemView.setOnClickListener {
                this@GamesAdapter.communication.open(game);
            }

            itemView.name.text = game.name
            Picasso.get().load(game.img).into(itemView.image);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesAdapter.ViewHolder {
        val element = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(element)
    }

    override fun getItemCount() = this.communication.games.size
}
