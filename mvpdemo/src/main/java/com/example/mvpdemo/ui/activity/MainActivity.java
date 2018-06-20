package com.example.mvpdemo.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.model.entity.WeatherInfo;
import com.example.mvpdemo.presenter.impl.WeatherPresenterImpl;
import com.example.mvpdemo.ui.view.WeatherView;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements WeatherView {
    @Bind(R.id.et_citynumber)
    EditText mEtCitynumber;
    @Bind(R.id.textview)
    TextView mTextview;
    Dialog dialog;
    private WeatherPresenterImpl mWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dialog = new ProgressDialog(this);
        dialog.setTitle("正在加载中");
        mWeatherPresenter = new WeatherPresenterImpl(this);
    }
    public void click(View view){
        String number = mEtCitynumber.getText().toString();
        mWeatherPresenter.getWeatherInfo(number);
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWeatherInfo(Call<WeatherInfo> call, Response<WeatherInfo> response) {
        WeatherInfo weatherInfo = response.body();
        mTextview.setText(weatherInfo.toString());
    }
}
