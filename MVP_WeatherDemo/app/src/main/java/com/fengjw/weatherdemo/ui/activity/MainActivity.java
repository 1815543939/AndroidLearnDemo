package com.fengjw.weatherdemo.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fengjw.weatherdemo.R;
import com.fengjw.weatherdemo.presenter.impl.WeatherPresenterImpl;
import com.fengjw.weatherdemo.ui.view.WeatherView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements WeatherView{

    @BindView(R.id.et_citynumber)
    EditText mEtCityNumber;

    @BindView(R.id.textview)
    TextView mTextView;

    private Dialog mDialog;

    private WeatherPresenterImpl mWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDialog = new ProgressDialog(this);
        mDialog.setTitle("onLoading...");
        mWeatherPresenter = new WeatherPresenterImpl(this);

    }

    @OnClick(R.id.et_btn)
    public void clickBtn(View view){
        Log.d("fengjw", "clickBtn");
        String number = null;
        number = mEtCityNumber.getText().toString();
        Log.d("fengjw", "number : " + number);
        if (number != null)
        mWeatherPresenter.getWeatherInfo(number);
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWeatherInfo(Call<ResponseBody> call, Response<ResponseBody> response) {
        String weatherInfo = response.body().toString();
        mTextView.setText(weatherInfo.toString());
    }

}
