package com.example.iwm_games_kotlin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_fragment_view.*

class FragmentView(private val game: Game) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        description.text = game.description;
        name.text = game.name;
        Picasso.get().load(this.game.img).into(img);

        super.onViewCreated(view, savedInstanceState)
    }

}
