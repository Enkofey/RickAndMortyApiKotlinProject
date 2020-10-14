package com.example.myfirstprograminkotlin.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.myfirstprograminkotlin.R
import com.example.myfirstprograminkotlin.models.Character
import com.example.myfirstprograminkotlin.models.Result
import retrofit2.Call
import com.example.myfirstprograminkotlin.models.webservices.CharacterService
import com.example.myfirstprograminkotlin.ui.adapter.CharacterAdapter
import retrofit2.Callback
import retrofit2.Response

class CharacterFragment : Fragment() {

    lateinit var characterList : Character
    lateinit var characterAdapter : CharacterAdapter
    lateinit var characterService : CharacterService
    lateinit var listView : ListView

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.character_fragment,container,false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterService = CharacterService()
        characterList = Character()

        characterAdapter = CharacterAdapter(requireContext(),characterList)

        listView = view.findViewById(R.id.characterAdapterListView)

        var call : Call<Character> = characterService.getCharacter().getCharacter()
        //addtoBackstack
        call.enqueue(object : Callback<Character>{
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if(response.body() != null){
                    characterList.results = response.body()!!.results
                    listView.adapter = characterAdapter
                    Log.d("Character","Success")
                }
                else{
                    Log.d("Character","Fail")
                }
            }
            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d("Character","Failure")
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

                val idCharacter = characterAdapter.getItem(position) as Result
                parentFragmentManager.beginTransaction()
                    .addToBackStack("CharacterDetailFragment")
                    .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
                    .replace(R.id.fragment_container, CharacterDetailFragment.newInstance(idCharacter.id))
                    .commit()
                return true
            }

        })
    }


}