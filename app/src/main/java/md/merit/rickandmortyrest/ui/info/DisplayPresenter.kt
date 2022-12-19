package md.merit.rickandmortyrest.ui.info

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import md.merit.rickandmortyrest.listeners.EpisodeListener
import md.merit.rickandmortyrest.models.episodes.EpisodeModel
import md.merit.rickandmortyrest.remote.RetrofitInstance

class DisplayPresenter {

    fun getEpisodes(listener: EpisodeListener, page: Int){

        var data: EpisodeModel
        MainScope().launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance().api.getEpisodesList(page)
                if (response.isSuccessful){
                    data = response.body()!!
                    Log.d("EpisodeList:", data.results.toString())
                    withContext(Dispatchers.Main){
                        listener.getEpisodes(data.results as ArrayList<md.merit.rickandmortyrest.models.episodes.Result>)
                    }
                }
                else {
                    Log.d("error", "Nu merge" + response.code().toString())
                    listener.getEpisodeError(response.code().toString())
                }
            }catch (e: java.net.SocketTimeoutException){
                listener.getEpisodeError("!internet" + e.message.toString())
                Log.e("NoInternet", "No Internet Connection: ${e.message}")
            }catch (e:Exception){
                listener.getEpisodeError(e.message.toString())
                Log.e("error", "${e.message}")
            }
        }
    }
}