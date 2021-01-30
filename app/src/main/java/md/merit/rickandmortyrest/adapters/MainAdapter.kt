package md.merit.rickandmortyrest.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lo_item.view.*
import md.merit.rickandmortyrest.R
import md.merit.rickandmortyrest.models.characters.Character
import md.merit.rickandmortyrest.ui.DisplayActivity


class MainAdapter(var items: ArrayList<Character>, var context: Context) :
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
        holder.episode.text = character.episode
        holder.location.text = character.location

        Picasso.with(context)
            .load(
                StringBuilder(character.image)
                    .toString()
            )
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val gItem = items[position]
            var gId = gItem.id
            var gName = gItem.name
            var gEpisode = gItem.episode
            var gLocation = gItem.location
            var gStatus = gItem.status
            var gImage = gItem.image

            val intent = Intent(context, DisplayActivity::class.java)
            intent.putExtra("iId", gId)
            intent.putExtra("iName", gName)
            intent.putExtra("iEpisode", gEpisode)
            intent.putExtra("iLocation", gLocation)
            intent.putExtra("iStatus", gStatus)
            intent.putExtra("iImage", gImage)

            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = items.size

    fun updateData(persModels: ArrayList<Character>) {
        this.items = persModels
        //      notifyDataSetChanged()
        notifyItemInserted(items.size - 1)
    }
}