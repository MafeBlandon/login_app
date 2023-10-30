package com.example.login_volley_2471726

import android.app.DownloadManager.Request
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etApellido: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegistro: Button
    private lateinit var btnIniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        
        //otra forma de capturar la informacion
        btnRegistro.setOnClickListener { 
            val names: String = etNombre.text.toString()
            val lastName: String = etApellido.text.toString()
            val email: String = etCorreo.text.toString()
            val phone: String = etTelefono.text.toString()
            val user: String = etUsuario.text.toString()
            val pass: String = etPassword.text.toString()
            
            insertUser(names,lastName,email,phone,user,pass)
            
        }
        val btn: Button = findViewById(R.id.btnIniciar)
        btn.setOnClickListener {
            val intent: Intent = Intent(this,loginActivity::class.java)
            startActivity(intent)

        }
            
       
    }

    private fun insertUser(names: String, lastName: String, email: String, phone: String, user: String, pass: String) {

        val url  = getString(R.string.api_insert)
        val req: RequestQueue = Volley.newRequestQueue(this)//para las peticiones http
        val strReq: StringRequest = object : StringRequest(Method.POST,url,{
               Toast.makeText(this,it.trim(), Toast.LENGTH_SHORT).show()//el trim elimina los espacios
            clearText()
        },{
            Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): MutableMap<String, String>? {
                val params= HashMap<String,String>()// el map  organiza la data que estoy enviando
                params.put("names", names )
                params.put("last_name", lastName )
                params.put("email", email )
                params.put("phone", phone )
                params.put("user", user )
                params.put("password", pass )
                return params
            }

        }
        req.add(strReq)
    }
    private fun clearText(){
        etNombre.setText("")
        etApellido.setText("")
        etCorreo.setText("")
        etTelefono.setText("")
        etUsuario.setText("")
        etPassword.setText("")
        etNombre.requestFocus()
    }

    private fun initView(){

        etNombre = findViewById(R.id.etName)
        etApellido= findViewById(R.id.etLast_name)
        etCorreo = findViewById(R.id.etEmail)
        etTelefono = findViewById(R.id.etPhone)
        etUsuario = findViewById(R.id.etUser)
        etPassword = findViewById(R.id.etPassword)
        btnRegistro = findViewById(R.id.btnRegistrar)


    }
}