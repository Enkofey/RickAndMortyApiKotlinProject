package com.example.myfirstprograminkotlin.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstprograminkotlin.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth
    lateinit var mGoogleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var decorView : View = window.decorView

        mAuth = FirebaseAuth.getInstance()

        var user : FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if(user != null){
            var intent : Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        signinButton.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                signinAccountWithEmail(emailPlainText.text.toString(),passwordPlainText.text.toString())
            }
        })
        registerButton.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                createAccountWithEmail(emailPlainText.text.toString(),passwordPlainText.text.toString())
            }
        })

        var account : GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        if(account!=null){
            var intent : Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        var gso : GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)
        sign_in_button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                when (v?.id){
                    R.id.sign_in_button->GoogleSignIn()
                }
            }

        })
    }


    fun GoogleSignIn(){
        var signinIntent : Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signinIntent,100)
    }

    fun createAccountWithEmail(email : String, password : String){
        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    var user : FirebaseUser? = mAuth.currentUser
                    var intent : Intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Creation failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun signinAccountWithEmail(email : String, password : String){
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
               if(task.isSuccessful){
                   var user : FirebaseUser? = mAuth.currentUser
                   var intent : Intent = Intent(this,MainActivity::class.java)
                   startActivity(intent)
               }else{
                   Toast.makeText(this, "Connection failed", Toast.LENGTH_SHORT).show()
               }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==100){
            var task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    fun handleSignInResult(completedTask : Task<GoogleSignInAccount>){
        try{
            var account : GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            var intent : Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }catch (e:ApiException){
            Log.w("Fail","signInResult:failed code= ${e.statusCode}")
        }
    }

}