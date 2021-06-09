package com.framework.application

import android.app.Application
import com.framework.application.modal.manager.ORMDatabaseHelper
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request


class ApplicationObject : Application() {

    companion object {
        @JvmStatic private lateinit var instance: ApplicationObject
        fun getInstance() : ApplicationObject {
            return instance
        }
    }

    init {
        instance = this
    }

    private lateinit var client: OkHttpClient
    private lateinit var cookieJar: ClearableCookieJar

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    fun getHelper(): ORMDatabaseHelper? {
        return ORMDatabaseHelper.getHelper(this)
    }

    private fun getClient(): OkHttpClient {
        if (client == null) {
            cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(this))
            client = OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build()
        }
        return client
    }

    fun addRequestToQueue(request: Request, callback: Callback) {
        getClient().newCall(request).enqueue(callback)
    }

    fun cancelPendingRequests(requestTag: String): Boolean {
        var isCancelled = false
        if (client != null) {
            //When you want to cancel:
            //A) go through the queued calls and cancel if the tag matches:
            for (call in client.dispatcher.queuedCalls()) {
                if (call.request().tag() == requestTag) call.cancel()
                isCancelled = true
            }
            //B) go through the running calls and cancel if the tag matches:
            for (call in client.dispatcher.runningCalls()) {
                if (call.request().tag() == requestTag) call.cancel()
                isCancelled = true
            }
        }
        return isCancelled
    }

}