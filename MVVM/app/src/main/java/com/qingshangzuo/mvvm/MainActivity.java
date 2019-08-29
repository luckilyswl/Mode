package com.qingshangzuo.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qingshangzuo.mvvm.api.GitHubApi;
import com.qingshangzuo.mvvm.custom.ProcessDialog;
import com.qingshangzuo.mvvm.databinding.ActivityMainBinding;
import com.qingshangzuo.mvvm.viewmodel.Contributor;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;



public class MainActivity extends AppCompatActivity {

    private ProcessDialog dialog;
    private ActivityMainBinding binding;

    private Subscriber<Contributor> contributorSub = new Subscriber<Contributor>() {

        @Override
        public void onStart() {
            showProgress();
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Contributor contributor) {
            binding.setContributor(contributor);

            dismissProgress();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void get(View view){
        getContributors("square", "retrofit");
    }

    public void change(View view){
        if(binding.getContributor() != null){
            binding.getContributor().setLogin("zjutkz");
        }
    }

    public void showProgress(){
        if(dialog == null){
            dialog = new ProcessDialog(this);
        }

        dialog.showMessage("正在加载...");
    }

    public void dismissProgress(){
        if(dialog == null){
            dialog = new ProcessDialog(this);
        }

        dialog.dismiss();
    }

    public void getContributors(String owner,String repo){
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
}
