package md.merit.rickandmortyrest.remote

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import md.merit.rickandmortyrest.data.DBHandler
import md.merit.rickandmortyrest.models.characters.Character
import md.merit.rickandmortyrest.models.characters.Model
import md.merit.rickandmortyrest.models.characters.Result
import md.merit.rickandmortyrest.models.episodes.Episode
import okhttp3.*
import java.io.IOException

class NetworkClient(val context: Context) {
    internal lateinit var client: OkHttpClient
    internal lateinit var request: Request
    lateinit var dbHandler: DBHandler


    fun getData() {
        dbHandler = DBHandler(context, null, null, 1)
        var i = 0
        while (i < 34) {

            client = OkHttpClient()
            request = Request.Builder()
                .url(String.format("https://rickandmortyapi.com/api/character/?page=%d", i))
                .build()
            i++
            CoroutineScope(Dispatchers.IO).launch {

                client.newCall(request)
                    .enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            Log.d("ERROR", e.toString())
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val body = response.body!!.string()
                            val gson = GsonBuilder().create()
                            //Log.d("REST", body)
                            val obiectul = gson.fromJson(body, Model::class.java)
                            for (item in obiectul.results) {
                                val character = Character()
                                character.id = item.id
                                character.name = item.name
                                character.location = item.location.name
                                character.episode = item.episode[0]
                                character.status = item.status
                                character.image = item.image
                                dbHandler.addCharacter(context, character)
                            }
                        }
                    })
            }
        }
    }

    fun getFirstEpisode() {
        dbHandler = DBHandler(context, null, null, 1)
        var i = 0
        while (i < 3) {
            client = OkHttpClient()
            request = Request.Builder()
                .url(String.format("https://rickandmortyapi.com/api/episode/?page=%d", i))
                .build()
            i++
            CoroutineScope(Dispatchers.Default).launch {
                client.newCall(request)
                    .enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            Log.d("ERROR", e.toString())
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val body = response.body!!.string()
                            val gson = GsonBuilder().create()
                            // Log.d("REST", body)
                            val obiectul = gson.fromJson(body, Episode::class.java)
                            for (item in obiectul.results) {
                                var episode = md.merit.rickandmortyrest.models.episodes.Result()
                                episode.id = item.id
                                episode.name = item.name
                                episode.url = item.url
                                dbHandler.addEpisode(episode)
                            }
                        }
                    })
            }
        }
    }
}