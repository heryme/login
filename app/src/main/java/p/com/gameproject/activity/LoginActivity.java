package p.com.gameproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import p.com.gameproject.R;
import p.com.gameproject.database.UserDatabase;
import p.com.gameproject.utils.SharePref;
import p.com.gameproject.utils.ValidationUtility;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * EditText
     */
    private EditText etLoginActivityEmail, etLoginActivityPasswod;
    /**
     * Button
     */
    private Button btntLoginActivityLogin;

    /**
     * TextView
     */
    private TextView tvLoginActivityCreateAccount;

    /**
     * Checkbox
     */
    private CheckBox cbLoginActivityShowHidePassword;

    /**
     * UserDatabase
     */
    private UserDatabase userDatabase;

    /**
     * Share preference
     */
    private SharePref sharePref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharePref = new SharePref(LoginActivity.this);
        userDatabase = new UserDatabase(LoginActivity.this);
        initViews();
        registerListener();

    }

    /**
     * Initialization view
     */
    private void initViews() {
        etLoginActivityEmail = (EditText) findViewById(R.id.etLoginActivityEmail);
        etLoginActivityPasswod = (EditText) findViewById(R.id.etLoginActivityPasswod);
        btntLoginActivityLogin = (Button) findViewById(R.id.btntLoginActivityLogin);
        tvLoginActivityCreateAccount = (TextView) findViewById(R.id.tvLoginActivityCreateAccount);
        cbLoginActivityShowHidePassword = (CheckBox) findViewById(R.id.cbLoginActivityShowHidePassword);
    }

    /**
     * Register Listener
     */
    private void registerListener() {
        btntLoginActivityLogin.setOnClickListener(this);
        tvLoginActivityCreateAccount.setOnClickListener(this);
        cbLoginActivityShowHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button,
                                         boolean isChecked) {
                if (isChecked) {
                    cbLoginActivityShowHidePassword.setText(R.string.hide_pwd);
                    etLoginActivityPasswod.setInputType(InputType.TYPE_CLASS_TEXT);
                    etLoginActivityPasswod.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());
                } else {
                    cbLoginActivityShowHidePassword.setText(R.string.show_pwd);
                    etLoginActivityPasswod.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    etLoginActivityPasswod.setTransformationMethod(PasswordTransformationMethod
                            .getInstance());

                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btntLoginActivityLogin:
                if (checkValidation()) {
                    if (userDatabase.auth(etLoginActivityEmail.getText().toString(), etLoginActivityPasswod.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        intent = new Intent(LoginActivity.this, GameActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.tvLoginActivityCreateAccount:
                intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
        }

    }

    /**
     * Check validation
     */
    private boolean checkValidation() {
        if (ValidationUtility.isValidEditText(etLoginActivityEmail, "Enter email ") &&
                ValidationUtility.isValidEditText(etLoginActivityPasswod, "Enter password")) {
            if (ValidationUtility.isValidEmail(LoginActivity.this, etLoginActivityEmail)) {
                return true;
            }
        }
        return false;
    }
}
