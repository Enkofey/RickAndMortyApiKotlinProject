package com.example.myfirstprograminkotlin.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myfirstprograminkotlin.R
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.bumptech.glide.Glide
import com.example.myfirstprograminkotlin.ui.activities.LoginActivity
import com.example.myfirstprograminkotlin.ui.activities.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.account_fragment.*


class AccountFragment : Fragment() {
    lateinit var root : View
    lateinit var gso : GoogleSignInOptions
    lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.account_fragment,container,false)
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var user : FirebaseUser? = FirebaseAuth.getInstance().currentUser

        if(user != null){
            updateUI(user,null,view)
        }

        var acct : GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(activity)
        if(acct != null){
            updateUI(null,acct,view)
        }

        //var logoutButton : Button = view.findViewById(R.id.logoutButton)

        logoutButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v:View?){
                var auth : FirebaseAuth  = FirebaseAuth.getInstance()
                auth.signOut()
                gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                googleSignInClient = GoogleSignIn.getClient(root.context,gso)
                googleSignInClient.signOut()
                var intent : Intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun updateUI(user : FirebaseUser?, acct : GoogleSignInAccount?, view : View){
        if(user!=null){
            //var emailView : TextView = view.findViewById(R.id.EmailView)
            EmailView.setText(user.email)
            //var nameView : TextView = view.findViewById(R.id.NameView)
            NameView.setText(user.displayName)
        }
        if(acct!=null){
            //var emailView : TextView = view.findViewById(R.id.EmailView)
            EmailView.setText(acct.email)
            //var nameView : TextView = view.findViewById(R.id.NameView)
            NameView.setText(acct.displayName)
            var imageView : ImageView = view.findViewById(R.id.accountImageView)
            Glide.with(root.context).load(acct.photoUrl).override(400).into(imageView)
        }
    }

}