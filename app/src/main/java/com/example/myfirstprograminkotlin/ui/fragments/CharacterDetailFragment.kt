package com.example.myfirstprograminkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myfirstprograminkotlin.R
import com.example.myfirstprograminkotlin.models.Character
import com.example.myfirstprograminkotlin.models.Result
import com.example.myfirstprograminkotlin.models.webservices.CharacterService
import kotlinx.android.synthetic.main.character_detail_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailFragment : Fragment() {

    lateinit var characterService : CharacterService
    var character : Result? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.character_detail_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterService = CharacterService()
        character = Result()
        var call : Call<Result> = characterService.getCharacter().getCharacterDetail(arguments?.getInt("arg_id"))
        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if(response.body() != null){
                    character = response.body()
                    characterDetailNameTextView.setText(character?.name)
                    characterDetailGenderTextView.setText(character?.gender)
                    characterDetailLocationTextView.setText(character?.location?.name)
                    characterDetailOriginTextView.setText(character?.origin?.name)
                    characterDetailSpeciesTextView.setText(character?.species)
                    characterDetailTypeTextView.setText(character?.type)
                    characterDetailStatusTextView.setText(character?.status)
                    Glide.with(requireContext()).load(character?.image).override(400).into(characterDetailImageView)
                    Log.d("Detail","Success")
                }
                else{
                    Log.d("Detail","Fail")
                }
            }
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("Detail","Failure")
                Log.d("except",t.toString())
            }
        })
    }

    companion object{

        const val ARG_ID = "arg_id"

        fun newInstance(id : Int): CharacterDetailFragment {
            val args = Bundle()
            args.putInt(ARG_ID, id)
            val fragment = CharacterDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}