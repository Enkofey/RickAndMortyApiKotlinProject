package com.example.myfirstprograminkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.myfirstprograminkotlin.R
import com.example.myfirstprograminkotlin.models.Episode
import com.example.myfirstprograminkotlin.models.Result
import com.example.myfirstprograminkotlin.models.ResultEpisode
import com.example.myfirstprograminkotlin.models.webservices.EpisodeService
import com.example.myfirstprograminkotlin.ui.adapter.EpisodeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeFragment : Fragment () {

    lateinit var episodeList : Episode
    lateinit var episodeAdapter : EpisodeAdapter
    lateinit var episodeService : EpisodeService
    lateinit var listView : ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.episode_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        episodeService = EpisodeService()
        episodeList = Episode()

        episodeAdapter = EpisodeAdapter(requireContext(),episodeList)

        listView = view.findViewById(R.id.episodeAdapterListView)
        var call : Call<Episode> = episodeService.getEpisode().getEpisode()

        call.enqueue(object : Callback<Episode>{

            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                if(response.body() != null){
                    episodeList.results = response.body()!!.results
                    listView.adapter = episodeAdapter
                    Log.d("Episode","Success")
                }
                else{
                    Log.d("Episode","Fail")
                }
            }

            override fun onFailure(call: Call<Episode>, t: Throwable) {
                Log.d("Episode","Failure")
                Log.d("except",t.toString())
            }

        })

        listView.setOnItemLongClickListener(object : AdapterView.OnItemLongClickListener{
            override fun onItemLongClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ): Boolean {

                val idEpisode = episodeAdapter.getItem(position) as ResultEpisode
                parentFragmentManager.beginTransaction()
                    .addToBackStack("CharacterDetailFragment")
                    .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
                    .replace(R.id.fragment_container, EpisodeDetailFragment.newInstance(idEpisode.id))
                    .commit()
                return true
            }

        })

    }
}