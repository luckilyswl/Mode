package com.qingshangzuo.mvvm.secvice;

import com.qingshangzuo.mvvm.viewmodel.Contributor;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubSecvice {
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}