package com.qingshangzuo.mvc.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingshangzuo.mvc.R;

public class ProcessDialog extends Dialog {

    private ImageView mLoadingImg;
    private TextView mMessageTV;

    public ProcessDialog( Context context) {
        super(context, R.style.transparent_Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_dialog);

        mLoadingImg = findViewById(R.id.loadingimg);
        mMessageTV = findViewById(R.id.messagetv);
    }


    //当没有消息时只展示菊花
    public void showMessage(String message){
        try{
            super.show();
            if(!TextUtils.isEmpty(message)){
                mMessageTV.setText(message);
                mMessageTV.setVisibility(View.VISIBLE);
            }else {
                mMessageTV.setText("");
                mMessageTV.setVisibility(View.GONE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.rotate);
        mLoadingImg.startAnimation(animation);
    }
}
