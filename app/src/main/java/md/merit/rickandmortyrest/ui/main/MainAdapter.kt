package md.merit.rickandmortyrest.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lo_item.view.*
import md.merit.rickandmortyrest.R
import md.merit.rickandmortyrest.models.characters.Result
import md.merit.rickandmortyrest.ui.info.DisplayActivity


class MainAdapter(var items: ArrayList<Result>, var context: Context) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.imgMain
        var characterName = itemView.tvName
        var episode = itemView.tvEpisode
        var location = itemView.tvLocation
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.lo_item, parent, false)
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = items[position]
        holder.characterName.text = character.name
        holder.episode.text = character.location.name
        holder.location.text = character.location.name

        Picasso.with(context)
            .load(
                StringBuilder(character.image)
                    .toString()
            )
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val gItem = items[position]
            val gId = gItem.id
            val gName = gItem.name
            val gLocation = gItem.location.name
            val gEpisode = gItem.episode[0]
            val gStatus = gItem.status
            val gImage = gItem.image

            val intent = Intent(context, DisplayActivity::class.java)
            intent.putExtra("iId", gId)
            intent.putExtra("iName", gName)
            intent.putExtra("iLocation", gLocation)
            intent.putExtra("iEpisode", gEpisode)
            intent.putExtra("iStatus", gStatus)
            intent.putExtra("iImage", gImage)

            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = items.size

    fun updateData(persModels: ArrayList<Result>) {
        this.items = persModels
        //      notifyDataSetChanged()
        notifyItemInserted(items.size - 1)
    }
}