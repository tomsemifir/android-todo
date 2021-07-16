package com.example.double_todolist.http

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.OkHttpClient
import okhttp3.Request


class GoogleHttpClient(context: Context) {

    val queue = Volley.newRequestQueue(context)
    var url = "https://www.google.com"

    fun getGooglePage(onSuccess : Response.Listener<String>, onError : Response.ErrorListener?) {
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET,
            url,
            onSuccess,
            onError
        )

        queue.add(stringRequest)
    }

}