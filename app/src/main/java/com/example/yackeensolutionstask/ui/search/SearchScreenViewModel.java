package com.example.yackeensolutionstask.ui.search;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yackeensolutionstask.db.MainDatabase;
import com.example.yackeensolutionstask.db.RecipeDao;
import com.example.yackeensolutionstask.db.RecipeEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchScreenViewModel extends ViewModel {

    private Observable<List<RecipeEntity>> observable;
    private Observer<List<RecipeEntity>> observer;
    RecipeDao recipeDao;
    public MutableLiveData<List<RecipeEntity>> searchLiveData = new MutableLiveData<>();


    public void getSearchDate(String query, Context context) {
        recipeDao = MainDatabase.Companion.getMainDatabase(context).recipeDao();

        observable = recipeDao.searchRecipe(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observer = new Observer<List<RecipeEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<RecipeEntity> itemEntities) {

                searchLiveData.setValue(itemEntities);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "Recipes onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("Tag", "Recipes : onComplete get research data ");

            }
        };
        observable.subscribe(observer);


    }
}
