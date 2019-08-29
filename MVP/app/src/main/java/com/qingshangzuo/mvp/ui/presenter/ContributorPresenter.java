package com.qingshangzuo.mvp.ui.presenter;



import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.qingshangzuo.mvp.api.GitHubApi;
import com.qingshangzuo.mvp.model.Contributor;
import com.qingshangzuo.mvp.ui.callback.ContributorView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ContributorPresenter extends MvpBasePresenter<ContributorView> {

    private Subscriber<Contributor> contributorSub = new Subscriber<Contributor>() {

        @Override
        public void onStart() {
            ContributorView view = getView();
            if(view != null){
                view.onLoadContributorStart();
            }
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Contributor contributor) {
            ContributorView view = getView();
            if(view != null){
                view.onLoadContributorComplete(contributor);
            }
        }
    };

    public void get(String owner,String repo){
        GitHubApi.getContributors(owner, repo)
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<List<Contributor>, Contributor>() {

                    @Override
                    public Contributor call(List<Contributor> contributors) {
                        return contributors.get(0);
                    }
                })
                .subscribe(contributorSub);
    }

    public void change(){
        ContributorView view = getView();
        if(view != null){
            view.onChangeContributorName("zjutkz");
        }
    }
}
