package md.merit.rickandmortyrest.ui.main

import android.util.Log
import kotlinx.coroutines.*
import md.merit.rickandmortyrest.listeners.CharacterListener
import md.merit.rickandmortyrest.models.characters.CharacterModel
import md.merit.rickandmortyrest.models.characters.Result
import md.merit.rickandmortyrest.remote.RetrofitInstance

class MainPresenter {

    fun getCharacters(listener: CharacterListener, page: Int){

        var data: CharacterModel
        MainScope().launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance().api.getCharacterList(page)
                if (response.isSuccessful){
                    data = response.body()!!
                    Log.d("CharacterList:", data.results.toString())
                    withContext(Dispatchers.Main){
                        listener.getCharacterList(data.results as ArrayList<Result>)
                    }
                }
                else {
                    Log.d("error", "Nu merge" + response.code().toString())
                    listener.getCharacterError(response.code().toString())
                }}catch (e: java.net.SocketTimeoutException){
                listener.getCharacterError("!internet" + e.message.toString())
                Log.e("NoInternet", "No Internet Connection: ${e.message}")
            }catch (e:Exception){
                listener.getCharacterError(e.message.toString())
                Log.e("error", "${e.message}")
            }
        }
    }
}