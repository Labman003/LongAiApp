package com.ouzhouren.longai.view.welcome.activity.fragment.outlayer.loginlayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.presenter.RegisterViewInterface;
import com.ouzhouren.longai.presenter.UserPresenter;
import com.ouzhouren.longai.view.MainActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * 注册
 */
public class RegisterFragment extends Fragment implements RegisterViewInterface {
    private EditText phoneEt;
    private EditText passwordEt;
    private TextView fogetPSWTv;
    private Button loginIv;
    private SweetAlertDialog dlg;
    private Activity mAc;
    private UserPresenter userPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_register, null);
        findViewById(view);
        init();
        return view;
    }

    private void findViewById(View view) {
        phoneEt = (EditText) view.findViewById(R.id.et_write_phone);
        passwordEt = (EditText) view.findViewById(R.id.et_put_identify);
        loginIv = (Button) view.findViewById(R.id.btn_login);
    }

    private void init() {
        this.mAc = getActivity();
        this.userPresenter = new UserPresenter();
        this.userPresenter.setRegisterViewInterface(this);
        dlg = new SweetAlertDialog(mAc, SweetAlertDialog.PROGRESS_TYPE);
        //绑定监听器
        loginIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.register(mAc);
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
        dlg.show();
    }

    @Override
    public void dismissProgress() {
        dlg.dismiss();
    }

    @Override
    public void showRegisterFail(String error) {
        dlg.dismiss();
        dlg.changeAlertType(SweetAlertDialog.ERROR_TYPE);
        dlg.setTitleText(error);
        dlg.show();
    }

    @Override
    public void goToActivity() {
        Intent i = new Intent(mAc, MainActivity.class);
        mAc.startActivity(i);
        mAc.finish();
    }
}
