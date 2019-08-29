package com.qingshangzuo.mvpdatabinding.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.qingshangzuo.mvpdatabinding.BR;

public class Contributor  extends BaseObservable {

    private String login;
    private int contributions;

    @Bindable
    public String getLogin(){
        return login;
    }

    @Bindable
    public int getContributions(){
        return contributions;
    }

    public void setLogin(String login){
        this.login = login;
        notifyPropertyChanged(BR.login);   // BR 导入不了（不管是不是自己手动添加），但是添加了就运行（不要管他是否还是红色）
    }

    public void setContributions(int contributions){
        this.contributions = contributions;
        notifyPropertyChanged(BR.contributions);
    }

    @Override
    public String toString() {
        return login + ", " + contributions;
    }
}
