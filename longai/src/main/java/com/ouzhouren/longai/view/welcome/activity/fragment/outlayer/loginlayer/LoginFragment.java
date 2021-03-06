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

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.presenter.LoginViewInterface;
import com.ouzhouren.longai.presenter.UserPresenter;
import com.ouzhouren.longai.view.MainActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * 登录
 */
public class LoginFragment extends Fragment implements LoginViewInterface {
    private EditText phoneEt;
    private EditText passwordEt;
    private TextView fogetPSWTv;
    private ImageView loginIv;
    private SweetAlertDialog dlg;
    private Activity mAc;
    private UserPresenter userPresenter;
    private MyLogger logger = MyLogger.benLog();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mAc = getActivity();
        View view = inflater.inflate(R.layout.fragment_new_login, null);
        findViewById(view);
        init();
        return view;
    }

    private void findViewById(View view) {
        phoneEt = (EditText) view.findViewById(R.id.login_et_phone);
        passwordEt = (EditText) view.findViewById(R.id.login_et_psw);
        fogetPSWTv = (TextView) view.findViewById(R.id.login_tv_forgetpsw);
        loginIv = (ImageView) view.findViewById(R.id.login_iv_login);
    }

    private void init() {
        this.mAc = getActivity();
        this.userPresenter = new UserPresenter();
        this.userPresenter.setLoginViewInterface(this);
        dlg = new SweetAlertDialog(mAc, SweetAlertDialog.PROGRESS_TYPE);
        dlg.setTitleText("努力登录中...");
        //绑定监听器
        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && phoneEt.getText().length() > 0) {
                    showLoginButton();
                } else {
                    hideLoginButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loginIv.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
//                UserBusinessImp userBusinessImp = new UserBusinessImp();
//                userBusinessImp.getUsersByGens(423, new UserModelInterface.GetUsersByGensCallBack() {
//                    @Override
//                    public void onSuccess(List<User> users) {
//
//                    }
//
//                    @Override
//                    public void onFail(String e) {
//
//                    }
//                }, mAc);

                userPresenter.login(mAc);
            }
        });
    }


    @Override
    public String getUserName() {
        return phoneEt.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return passwordEt.getText().toString().trim();
    }

    @Override
    public void showProgress() {
        dlg.dismiss();
        dlg = new SweetAlertDialog(mAc, SweetAlertDialog.PROGRESS_TYPE);
        dlg.setTitleText("努力登录中...");
        dlg.show();
    }

    @Override
    public void dismissProgress() {
        dlg.dismiss();
    }

    @Override
    public void showLoginFail(String error) {
        dlg.dismiss();
        dlg = new SweetAlertDialog(mAc, SweetAlertDialog.ERROR_TYPE);
        dlg.setTitleText(error);
        dlg.show();
    }

    @Override
    public void goToActivity() {
        Intent i = new Intent(mAc, MainActivity.class);
        mAc.startActivity(i);
        mAc.finish();
    }

    @Override
    public void hideLoginButton() {
        fogetPSWTv.setVisibility(View.VISIBLE);
        loginIv.setVisibility(View.GONE);
    }

    @Override
    public void showLoginButton() {
        fogetPSWTv.setVisibility(View.GONE);
        loginIv.setVisibility(View.VISIBLE);
    }
}
