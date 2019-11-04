package com.kks.trainingapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.kks.trainingapp.R;
import com.kks.trainingapp.common.BaseActivity;
import com.kks.trainingapp.custom_control.MyanButton;
import com.kks.trainingapp.custom_control.MyanEditText;
import com.kks.trainingapp.custom_control.MyanProgressDialog;
import com.kks.trainingapp.interactor.LoginInteractor;
import com.kks.trainingapp.mvp.presenter.LoginPresenterImpl;
import com.kks.trainingapp.mvp.view.LoginView;
import com.kks.trainingapp.util.ServiceHelper;
import com.kks.trainingapp.util.SharePreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kaungkhantsoe on 2019-10-18.
 **/
public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.et_password)
    MyanEditText etPassword;

    @BindView(R.id.et_user_name)
    MyanEditText etUsername;

    @BindView(R.id.btn_login)
    MyanButton btnLogin;
    @BindView(R.id.im_show_hide_password)
    ImageView imShowHidePassword;

    private LoginPresenterImpl mPresenter;

    private MyanProgressDialog mDialog;

    private ServiceHelper.ApiService mService;

    private SharePreferenceHelper mSharePreferenceHelper;

    private static final String TAG = "LoginActivity";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }




    @Override
    protected void setUpContents(Bundle savedInstanceState) {

        setupToolbar(false);
        setupToolbarText(getString(R.string.login));

        init();
    }

    private void init() {

        mService = ServiceHelper.getClient(this);

        mDialog = new MyanProgressDialog(this);

        mSharePreferenceHelper = new SharePreferenceHelper(this);

        mPresenter = new LoginPresenterImpl(new LoginInteractor(this.mService));



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClickLogin(etUsername.getMyanmarText(),
                        etPassword.getMyanmarText());
            }
        });


        mPresenter.onAttachView(this);

        mPresenter.onUIReady();
    }

    public void ShowHidePass(View view){

        if(view.getId()==R.id.im_show_hide_password){

            if(etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.icons8_eye_24);

                //Show Password
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.icons8_closed_eye_24);

                //Hide Password
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onTerminate();
    }


    @Override
    public void saveLoginData(String sessionId) {
        Log.e(TAG, "saveLoginData: " + sessionId );
        mSharePreferenceHelper.setLogin(sessionId);
    }

    @Override
    public void onLoginComplete() {
        this.startActivity(MainActivity.getMainActivityIntent(this));
        this.finish();
    }

    @Override
    public void checkLogin() {
        if (mSharePreferenceHelper.isLogin()) {
            onLoginComplete();
        }
    }

    @Override
    public void showLoading() {

        if (!isFinishing()) {
            mDialog.showDialog();
        }
    }

    @Override
    public void hideLoading() {

        if (!isFinishing()) {
            mDialog.hideDialog();
        }
    }

    @Override
    public void showToastMsg(String msg) {

        this.hideLoading();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogMsg(String title,String msg) {

        this.hideLoading();
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(getString(R.string.ok), null).show();
    }

    @Override
    public Context context() {
        return this;
    }
}
