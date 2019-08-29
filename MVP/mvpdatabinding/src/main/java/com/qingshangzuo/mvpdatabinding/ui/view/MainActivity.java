package com.qingshangzuo.mvpdatabinding.ui.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.qingshangzuo.mvpdatabinding.R;
import com.qingshangzuo.mvpdatabinding.databinding.ActivityMainBinding;
import com.qingshangzuo.mvpdatabinding.ui.callback.ContributorView;
import com.qingshangzuo.mvpdatabinding.ui.custom.ProcessDialog;
import com.qingshangzuo.mvpdatabinding.ui.presenter.ContributorPresenter;
import com.qingshangzuo.mvpdatabinding.viewmodel.Contributor;

public class MainActivity extends MvpActivity<ContributorView, ContributorPresenter> implements ContributorView {

    private ProcessDialog dialog;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @NonNull
    @Override
    public ContributorPresenter createPresenter() {
        return new ContributorPresenter();
    }

    public void get(View view) {
        getPresenter().get("square", "retrofit");
    }

    public void change(View view) {
        if (binding.getContributor() != null) {
            binding.getContributor().setLogin("zjutkz");
        }
    }

    @Override
    public void onLoadContributorStart() {
        showProgress();
    }

    @Override
    public void onLoadContributorComplete(Contributor contributor) {
        binding.setContributor(contributor);
        dismissProgress();
    }

    public void showProgress() {
        if (dialog == null) {
            dialog = new ProcessDialog(this);
        }

        dialog.showMessage("正在加载...");
    }

    public void dismissProgress() {
        if (dialog == null) {
            dialog = new ProcessDialog(this);
        }

        dialog.dismiss();
    }
}
