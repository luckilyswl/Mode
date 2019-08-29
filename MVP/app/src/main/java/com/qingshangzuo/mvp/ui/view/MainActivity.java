package com.qingshangzuo.mvp.ui.view;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.qingshangzuo.mvp.R;
import com.qingshangzuo.mvp.model.Contributor;
import com.qingshangzuo.mvp.ui.callback.ContributorView;
import com.qingshangzuo.mvp.ui.custom.ProcessDialog;
import com.qingshangzuo.mvp.ui.presenter.ContributorPresenter;


public class MainActivity extends MvpActivity<ContributorView, ContributorPresenter> implements ContributorView {

    private ProcessDialog dialog;

    private TextView topContributor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topContributor = (TextView)findViewById(R.id.top_contributor);
    }

    @Override
    public ContributorPresenter createPresenter() {
        return new ContributorPresenter();
    }

    public void get(View view){
        getPresenter().get("square", "retrofit");
    }

    public void change(View view){
        getPresenter().change();
    }

    @Override
    public void onLoadContributorStart() {
        showProgress();
    }

    @Override
    public void onLoadContributorComplete(Contributor contributor) {

        topContributor.setText(contributor.toString());

        dismissProgress();
    }

    @Override
    public void onChangeContributorName(String name) {
        topContributor.setText(name);
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
