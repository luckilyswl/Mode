package com.qingshangzuo.mvc.service;

import com.qingshangzuo.mvc.model.Contributor;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;



public interface GitHubService {
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}