package com.trial.mapdemo.main.login.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.trial.mapdemo.R;
import com.trial.mapdemo.facade.callback.GenericTextWatcherCallback;
import com.trial.mapdemo.facade.utils.GenericTextWatcher;
import com.trial.mapdemo.facade.utils.OnFragmentInteractionListener;
import com.trial.mapdemo.facade.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OTPValidationFragment extends Fragment {
    public static final String TAG = OTPValidationFragment.class.getSimpleName();
    private OnFragmentInteractionListener onFragmentInteractionListener;

    private GenericTextWatcherCallback genericTextWatcherCallback = new GenericTextWatcherCallback() {
        @Override
        public void getUpdate(String text, View view) {
            if (text.length() == 1) {
                int nextIndex = viewList.indexOf(view) + 1;
                if (nextIndex < viewList.size())
                    viewList.get(nextIndex).requestFocus();
                else
                    Utils.hideKeyboard(getActivity());
            }
        }
    };

    private List<EditText> viewList;
    @BindView(R.id.et_otp_char_1)
    EditText char1EditText;
    @BindView(R.id.et_otp_char_2)
    EditText char2EditText;
    @BindView(R.id.et_otp_char_3)
    EditText char3EditText;
    @BindView(R.id.et_otp_char_4)
    EditText char4EditText;
    @BindView(R.id.et_otp_char_5)
    EditText char5EditText;
    @BindView(R.id.et_otp_char_6)
    EditText char6EditText;

    public OTPValidationFragment() {

    }

    public static OTPValidationFragment getInstance() {
        return new OTPValidationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_otp_validation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        viewList = new ArrayList<>();
        viewList.add(char1EditText);
        viewList.add(char2EditText);
        viewList.add(char3EditText);
        viewList.add(char4EditText);
        viewList.add(char5EditText);
        viewList.add(char6EditText);

        GenericTextWatcher genericTextWatcher1 = new GenericTextWatcher(char1EditText, genericTextWatcherCallback);

        GenericTextWatcher genericTextWatcher2 = new GenericTextWatcher(char2EditText, genericTextWatcherCallback);

        GenericTextWatcher genericTextWatcher3 = new GenericTextWatcher(char3EditText, genericTextWatcherCallback);

        GenericTextWatcher genericTextWatcher4 = new GenericTextWatcher(char4EditText, genericTextWatcherCallback);

        GenericTextWatcher genericTextWatcher5 = new GenericTextWatcher(char5EditText, genericTextWatcherCallback);

        GenericTextWatcher genericTextWatcher6 = new GenericTextWatcher(char6EditText, genericTextWatcherCallback);

        char1EditText.addTextChangedListener(genericTextWatcher1);
        char2EditText.addTextChangedListener(genericTextWatcher2);
        char3EditText.addTextChangedListener(genericTextWatcher3);
        char4EditText.addTextChangedListener(genericTextWatcher4);
        char5EditText.addTextChangedListener(genericTextWatcher5);
        char6EditText.addTextChangedListener(genericTextWatcher6);
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

    @OnClick(R.id.button_validate_otp)
    public void validateOtp() {
        boolean isError = false;
        for (EditText editText : viewList) {
            if ("".equals(editText.getText().toString())) {
                isError = true;
                editText.setError("");
            }
        }
        if (!isError)
            onFragmentInteractionListener.performAction(TAG, null);
    }
}
