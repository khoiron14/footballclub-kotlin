package com.khoiron.footballmatchschedule.presenter

import com.google.gson.Gson
import com.khoiron.footballmatchschedule.data.api.ApiRepository
import com.khoiron.footballmatchschedule.data.api.TheSportDBApi
import com.khoiron.footballmatchschedule.data.model.Event.EventResponse
import com.khoiron.footballmatchschedule.ui.MainView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Khoiron14 on 20/11/18.
 */
class LastMatchPresenter(private val view: MainView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {
    fun getLastMatchList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastEvents()),
                EventResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }
}