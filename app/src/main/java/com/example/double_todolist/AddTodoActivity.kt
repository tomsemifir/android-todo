package com.example.double_todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AddTodoActivity : AppCompatActivity() {
    var position  = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        position = intent.getIntExtra("position", -1)

        if(position != -1) {
            var name = MainActivity.todos[position].name
            findViewById<EditText>(R.id.add_todo_name).setText(name)
            findViewById<Button>(R.id.add_todo_button).text = "Modifier"
            findViewById<TextView>(R.id.description).text = "Modifer tâche :"
        }
    }

    fun addTodo(view : View) {
        var todoName = findViewById<EditText>(R.id.add_todo_name).text.toString()
        if(position == -1) {
            MainActivity.todos.add(TodoItem(todoName, false))
        } else {
            MainActivity.todos[position].name = todoName
        }

        finish()
    }
}