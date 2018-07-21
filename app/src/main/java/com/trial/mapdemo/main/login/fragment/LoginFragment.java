package com.trial.mapdemo.main.login.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.trial.mapdemo.R;
import com.trial.mapdemo.facade.utils.OnFragmentInteractionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();
    private OnFragmentInteractionListener onFragmentInteractionListener;
    private String countryCode;
    private String phoneNumber;

    @BindView(R.id.et_country_code)
    EditText countryCodeEditText;
    @BindView(R.id.et_phone_number)
    EditText phoneNumberEditText;

    public LoginFragment() {

    }

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        else
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentInteractionListener = null;
    }

    @OnClick(R.id.button_send_otp)
    public void onSendOTP() {
        countryCode = countryCodeEditText.getText().toString();
        phoneNumber = phoneNumberEditText.getText().toString();

        boolean isError = false;
        if ("".equals(countryCode)) {
            countryCodeEditText.setError(getString(R.string.error_enter_valid_country_code));
            isError = true;
        }
        if ("".equals(phoneNumber) || phoneNumber.length() < 10) {
            phoneNumberEditText.setError(getString(R.string.error_enter_valid_phone_number));
            isError = true;
        }

        if (!isError)
            onFragmentInteractionListener.performAction(TAG, countryCode, phoneNumber);
    }
}
