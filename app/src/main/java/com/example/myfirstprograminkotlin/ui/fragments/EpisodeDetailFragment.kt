package com.example.myfirstprograminkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myfirstprograminkotlin.R
import com.example.myfirstprograminkotlin.models.ResultEpisode
import com.example.myfirstprograminkotlin.models.webservices.EpisodeService
import kotlinx.android.synthetic.main.character_detail_fragment.*
import kotlinx.android.synthetic.main.episode_detail_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeDetailFragment : Fragment(){

    lateinit var episodeService : EpisodeService
    var episode : ResultEpisode? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.episode_detail_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        episodeService = EpisodeService()
        episode = ResultEpisode()
        var call : Call<ResultEpisode> = episodeService.getEpisode().getEpisodeDetail(arguments?.getInt("arg_id"))
        call.enqueue(object : Callback<ResultEpisode> {
            override fun onResponse(call: Call<ResultEpisode>, response: Response<ResultEpisode>) {
                if(response.body() != null){
                    episode = response.body()

                    nameEpisodeDetailTextView.setText(episode?.name)
                    airDateEpisodeDetailTextView.setText(episode?.airDate)
                    episodeEpisodeDetailTextView.setText(episode?.episode)

                    Log.d("Detail","Success")
                }
                else{
                    Log.d("Detail","Fail")
                }
            }
            override fun onFailure(call: Call<ResultEpisode>, t: Throwable) {
                Log.d("Detail","Failure")
                Log.d("except",t.toString())
            }
        })
    }

    companion object{

        val ARG_ID = "arg_id"

        fun newInstance(id: Int): EpisodeDetailFragment {

            val args = Bundle()
            args.putInt(ARG_ID, id)
            val fragment = EpisodeDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}