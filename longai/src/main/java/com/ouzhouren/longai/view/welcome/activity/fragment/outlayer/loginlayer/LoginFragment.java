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
import com.ouzhouren.longai.constant.UserType;
import com.ouzhouren.longai.model.PicBusinessImp;
import com.ouzhouren.longai.model.PicModelInterface;
import com.ouzhouren.longai.model.Picture;
import com.ouzhouren.longai.model.User;
import com.ouzhouren.longai.presenter.LoginViewInterface;
import com.ouzhouren.longai.presenter.UserPresenter;
import com.ouzhouren.longai.view.MainActivity;

import java.util.List;

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
                PicBusinessImp picBusinessImp = new PicBusinessImp();
                User user = new User();
                user.setUserId(19);
                user.setGender("男");
                user.setNickname("采萝莉的小蘑菇");
                user.setEmail("23423432@qq.com");
                user.setBiography("诚实可靠");
                user.setPhone(1233443322);
                user.setType(UserType.NORMAL);
                picBusinessImp.getAlbumPics(1, 1, 3, new PicModelInterface.GetAlbumCallBack() {
                    @Override
                    public void onSuccess(List<Picture> pictures) {

                    }

                    @Override
                    public void onFail() {

                    }
                }, mAc);
               // userPresenter.login(mAc);
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
