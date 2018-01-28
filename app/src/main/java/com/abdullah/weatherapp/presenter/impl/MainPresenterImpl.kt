package com.abdullah.weatherapp.presenter.impl

import com.abdullah.weatherapp.WeatherApp
import com.abdullah.weatherapp.domain.interactor.CurrentWeatherInteractor
import com.abdullah.weatherapp.model.entity.Location
import com.abdullah.weatherapp.presenter.MainPresenter
import com.abdullah.weatherapp.view.MainView
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by abdullah on 24.1.2018.
 */
class MainPresenterImpl : MainPresenter {

    private var view: MainView? = null
    private var compositeSubscription: CompositeSubscription? = null
    private val interactor: CurrentWeatherInteractor

    constructor(currentWeatherInteractor: CurrentWeatherInteractor) {
        this.interactor = currentWeatherInteractor
    }

    override fun attachView(view: MainView) {
        this.view = view
        compositeSubscription = CompositeSubscription()
    }

    override fun detachView() {
        if (this.view != null) {
            view = null
            compositeSubscription?.unsubscribe()
            compositeSubscription = null
        }
    }


    override fun getLastWeatherStateByLocation(location: Location) {
        compositeSubscription?.add(interactor.getCurrentWeatherServiceByLocation(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Action1 { result -> view?.fillWeatherResult(result) }))
    }
}