package com.example.double_todolist.http

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

class HttpClient(var context : Context, var baseUrl : String) {

    val queue = Volley.newRequestQueue(context)

    fun onGet(path : String, onSuccess : Response.Listener<String>, onError : Response.ErrorListener?) {
        this.send(Request.Method.GET, path, onSuccess, onError)
    }

    inline fun <reified T> onPost(path : String, body : T, onSuccess : Response.Listener<T>, onError : Response.ErrorListener?) {
        this.sendBody<T>(Request.Method.POST, path, body, onSuccess, onError)
    }

    inline fun <reified T> onPatch(path : String, body : T, onSuccess : Response.Listener<T>, onError : Response.ErrorListener?) {
        this.sendBody<T>(Request.Method.PATCH, path, body, onSuccess, onError)
    }

    inline fun <reified T> onPut(path : String, body : T, onSuccess : Response.Listener<T>, onError : Response.ErrorListener?) {
        this.sendBody<T>(Request.Method.PUT, path, body, onSuccess, onError)
    }

    fun onDelete(path : String, onSuccess : Response.Listener<String>, onError : Response.ErrorListener?) {
        this.send(Request.Method.DELETE, path, onSuccess, onError)
    }

    inline fun <reified T> sendBody(method : Int, path : String, body : T, onSuccess : Response.Listener<T>, onError : Response.ErrorListener?) {
        var url = baseUrl + path
        var jsonBody = Gson().toJson(body)
        val stringRequest = JsonObjectRequest(method, url, JSONObject(jsonBody),
            {response -> onSuccess.onResponse(Gson().fromJson<T>(response.toString(), T::class.java))}
            , onError)
        queue.add(stringRequest)
    }

    fun send(method : Int, path : String, onSuccess : Response.Listener<String>, onError : Response.ErrorListener?) {
        var url = baseUrl + path
        val stringRequest = StringRequest(method, url, onSuccess, onError)
        queue.add(stringRequest)
    }
}