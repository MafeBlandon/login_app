package com.example.login_volley_2471726

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.BuildConfig
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class loginActivity : AppCompatActivity() {
    //primero inicializamos las variables
    private lateinit var etUserLogin: EditText
    private lateinit var etPassLogin: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegistrarse: Button
//la parte logica de el boton ingresar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        //3 accedemos a las propiedades del boton
        btnLogin.setOnClickListener {//listner escucha el clcik del boton
            val user = etUserLogin.text.toString()//guardamos lo que escribio el usuario en los campos de la vista cuando le de click al boton
            val pass = etPassLogin.text.toString()

            //metodo que recibe las variables
            loginUser(user,pass)
        }
        val btn: Button = findViewById(R.id.btnRegistrarse)
        btn.setOnClickListener {
            val intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }

    }

    //4
    private fun loginUser(user: String, pass: String) {

      val url  = getString(R.string.api_login) //Api-intermidiario de mi aplicacion y base de datos

        //esta variable recibe el metodo requesque que viene de volley  que tiene ñlas propiedades de volley .newreques
        val req: RequestQueue = Volley.newRequestQueue(this) //Volley se encarga de preparar la consulta para enviar la informacion correctamente(instancia)

        //creamos otra variable que tiene un objetocomplejo  de tipo de string
        val strReq = object : StringRequest(Method.POST, url , { //el stringreques utiliza el metodo post luego una peticion post de la url

            //la repuesta por parte de la peticion

            //el trim quita los espacion it.trim(mensaje , una respuesta )
            if(it.trim().equals("Success")){//si la respuesta es igual entonces
                //me envia a la pagina de home
                val intent = Intent(this,HomeActivity::class.java) //this porque esta en el mismo paquete  que trabaja con la metodologia de java

                startActivity(intent)
                finish()//limpia en memoria
            }else{
                //si la respuesta no es success sign
                // ifica que se conecto pero el usuario no se encontro
                Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
            }
        },{
            //error en la peticion y no se conecto a php
            Toast.makeText(this,"ERROR! ${it.message}",Toast.LENGTH_LONG).show()

        }){
            //le mandamos los parametro que vamos a enviar por medio del post
            //metodo  que viene de volley (trae los parametro para reutilizar)
            override fun getParams(): MutableMap<String, String>? { // cobre escribe para enviar la informacion map = organiza la informacion de la data
                val params= HashMap<String,String>()// el map  organiza la data que estoy enviando
                params.put("user", user)
                params.put("password", pass)

                return params //retornamos los parametros
            }

        }
        req.add(strReq) //añadimos un reques (strReq)
    }
//2 inicializamos
    private fun initView(){
        etUserLogin = findViewById(R.id.etUserLogin)
        etPassLogin = findViewById(R.id.etPassLogin)
            btnLogin = findViewById(R.id.btnLogin)
    }
}