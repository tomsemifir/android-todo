package com.example.double_todolist

import android.app.VoiceInteractor
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.double_todolist.http.GoogleHttpClient

class MainActivity : AppCompatActivity() {

    companion object {
        var todos = arrayListOf<TodoItem>(
            TodoItem("Faire la vaisselle", true),
            TodoItem("Faire du pain", false)
        )
    }

    var adapter = TodoAdapter(this, todos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listView = findViewById<ListView>(R.id.todo_list_view)
        listView.adapter = adapter
    }

    override fun onRestart() {
        super.onRestart()
        adapter.notifyDataSetChanged()
    }

    fun goToAddTodo(view : View) {
        var intent = Intent(this, AddTodoActivity::class.java)
        startActivity(intent)
    }

    fun goToMap(view : View) {
        var intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}