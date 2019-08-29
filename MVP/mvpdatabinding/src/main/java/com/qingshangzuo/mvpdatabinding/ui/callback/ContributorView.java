package com.qingshangzuo.mvpdatabinding.ui.callback;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.qingshangzuo.mvpdatabinding.viewmodel.Contributor;

public interface ContributorView  extends MvpView {

    void onLoadContributorStart();
    void onLoadContributorComplete(Contributor topContributor);
}
