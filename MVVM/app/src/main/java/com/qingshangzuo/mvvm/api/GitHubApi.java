package com.qingshangzuo.mvvm.api;

import com.qingshangzuo.mvvm.secvice.GitHubSecvice;
import com.qingshangzuo.mvvm.viewmodel.Contributor;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class GitHubApi {
    public static Observable<List<Contributor>> getContributors(String owner, String repo){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        GitHubSecvice service = retrofit.create(GitHubSecvice.class);
        return service.repoContributors(owner,repo);
    }
}
