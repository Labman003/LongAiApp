package com.ouzhouren.longai.view.welcome.activity.fragment.outlayer.loginlayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ouzhouren.longai.view.MainActivity;
import com.ouzhouren.longai.R;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * 登录
 */
public class LoginFragment extends Fragment {
    private EditText phoneEt;
    private EditText passwordEt;
    private TextView fogetPSWTv;
    private ImageView loginIv;
    private Activity mAc;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mAc = getActivity();
        View view = inflater.inflate(R.layout.fragment_new_login, null);
        phoneEt = (EditText) view.findViewById(R.id.login_et_phone);
        passwordEt = (EditText) view.findViewById(R.id.login_et_psw);
        fogetPSWTv = (TextView) view.findViewById(R.id.login_tv_forgetpsw);
        loginIv = (ImageView) view.findViewById(R.id.login_iv_login);
        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && phoneEt.getText().length() > 0) {
                    fogetPSWTv.setVisibility(View.GONE);
                    loginIv.setVisibility(View.VISIBLE);
                } else {
                    fogetPSWTv.setVisibility(View.VISIBLE);
                    loginIv.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loginIv.setOnClickListener(new View.OnClickListener() {
            SweetAlertDialog dlg;

            @Override
            public void onClick(View v) {
                Intent i = new Intent(mAc, MainActivity.class);
                mAc.startActivity(i);
                mAc.finish();
//                dlg = new SweetAlertDialog(mAc, SweetAlertDialog.PROGRESS_TYPE);
//                dlg.show();
//                String url = "192.168.253.5:8080/longai";
//                StringRequest req = new StringRequest(url).addUrlPrifix("http://").addUrlSuffix("/login?name=usertom&&pwd=123");
//                LiteHttpUtil.getLiteHttp(mAc).executeAsync(req.setHttpListener(new HttpListener<String>() {
//                    @Override
//                    public void onSuccess(String s, Response<String> response) {
//                        // 成功：主线程回调，反馈一个string
//                        dlg
//                                .setTitleText("登陆成功!")
//                                .setContentText(s + response.resToString())
//                                .changeAlertType(SweetAlertDialog.NORMAL_TYPE);
//                        Intent i = new Intent(mAc, MainActivity.class);
//                        mAc.startActivity(i);
//                        mAc.finish();
//
//                    }
//
//                    @Override
//                    public void onFailure(HttpException e, Response<String> response) {
//                        // 失败：主线程回调，反馈异常
//                        //  logger.i("faile exception:" + e + "----response:" + response);
//                    }
//                }));
            }
        });
     /*   TextView tv_down = (TextView)view.findViewById(R.id.tv_down);
        tv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRankMe();
            }
        });*/
        return view;
    }
}
