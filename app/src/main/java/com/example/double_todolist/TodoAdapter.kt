package com.example.double_todolist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class TodoAdapter(val context : Context, val todos : ArrayList<TodoItem>) : BaseAdapter() {
    override fun getCount(): Int {
        return this.todos.size
    }

    override fun getItem(position: Int): TodoItem {
        return this.todos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View

        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.todo_item_layout, null)
        } else {
            view = convertView
        }

        var item : TodoItem = this.getItem(position)

        view.findViewById<TextView>(R.id.todo_item_name).text = (item.name)

        var itemState = view.findViewById<CheckBox>(R.id.todo_item_state)
        itemState.isChecked = getItem(position).state
        itemState.setOnClickListener({view : View -> setTodoState(position, itemState.isChecked)})

        var itemEdit = view.findViewById<ImageView>(R.id.edit_button)
        itemEdit.setOnClickListener({view : View -> editTodo(position)})

        return view
    }

    fun setTodoState(position: Int, state : Boolean) {
        this.todos[position].state = state
    }

    fun editTodo(position: Int) {
        //println(position)
        var intent = Intent(context, AddTodoActivity::class.java)
        intent.putExtra("position", position)
        context.startActivity(intent)
    }
}