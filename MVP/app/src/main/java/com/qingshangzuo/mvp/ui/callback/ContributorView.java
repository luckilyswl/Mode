package com.qingshangzuo.mvp.ui.callback;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.qingshangzuo.mvp.model.Contributor;

public interface ContributorView extends MvpView {
    void onLoadContributorStart();
    void onLoadContributorComplete(Contributor topContributor);
    void onChangeContributorName(String name);
}
