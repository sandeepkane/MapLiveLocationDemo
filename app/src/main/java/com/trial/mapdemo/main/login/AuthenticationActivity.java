package com.trial.mapdemo.main.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.trial.mapdemo.R;
import com.trial.mapdemo.facade.utils.OnFragmentInteractionListener;
import com.trial.mapdemo.main.login.fragment.LoginFragment;
import com.trial.mapdemo.main.login.fragment.OTPValidationFragment;
import com.trial.mapdemo.main.map.MapsActivity;

import butterknife.ButterKnife;

public class AuthenticationActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        goToFragment(LoginFragment.getInstance(), LoginFragment.TAG);
    }

    @Override
    public void performAction(String source, Object... data) {
        switch (source) {
            case "LoginFragment":
                goToFragment(OTPValidationFragment.getInstance(), OTPValidationFragment.TAG);
                break;
            case "OTPValidationFragment":
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void goToFragment(Fragment fragment, String fragmentTag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(fragmentTag);
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }
}
