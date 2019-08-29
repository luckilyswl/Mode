package com.qingshangzuo.mvc;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.qingshangzuo.mvc.api.GitHubApi;
import com.qingshangzuo.mvc.custom.ProcessDialog;
import com.qingshangzuo.mvc.model.Contributor;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ProcessDialog dialog;
    private Contributor contributor = new Contributor();

    private TextView topContributor;

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
            MainActivity.this.contributor = contributor;
            topContributor.setText(contributor.login);
            dismissProgress();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topContributor = (TextView)findViewById(R.id.top_contributor);
    }

    public void get(View view){
        getTopContributor("square", "retrofit");//进行网络请求
    }

    public void change(View view){
        contributor.login = "GITHUB";
        topContributor.setText(contributor.login);
    }

    public void getTopContributor(String owner,String repo){
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
}