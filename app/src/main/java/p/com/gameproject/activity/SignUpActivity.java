package p.com.gameproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import p.com.gameproject.R;
import p.com.gameproject.database.DbInfo;
import p.com.gameproject.database.UserDatabase;
import p.com.gameproject.utils.ValidationUtility;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * EditText
     */
     private EditText etSignUpActivityPassword, etSignUpActivityEmail, etSignUpActivityConfirmPassword, etSignUpActivityName;

    /**
     * Textview
     */
     private TextView tvSignUpActivityalreadyUser;
    /**
     * Button
     */
     private Button btSignUpActivitySignUp;

    /**
     * User Database
     */
     private UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        setListeners();
        userDatabase = new UserDatabase(SignUpActivity.this);
    }


    /***
     * Set up view
     */
    private void initViews() {
        etSignUpActivityName = (EditText) findViewById(R.id.etSignUpActivityName);
        etSignUpActivityEmail = (EditText) findViewById(R.id.etSignUpActivityEmail);
        etSignUpActivityPassword = (EditText) findViewById(R.id.etSignUpActivityPassword);
        etSignUpActivityConfirmPassword = (EditText) findViewById(R.id.etSignUpActivityConfirmPassword);
        tvSignUpActivityalreadyUser = (TextView) findViewById(R.id.tvSignUpActivityalreadyUser);
        btSignUpActivitySignUp = (Button) findViewById(R.id.btSignUpActivitySignUp);

    }

    /**
     * Register Listeners
     */
    private void setListeners() {
        btSignUpActivitySignUp.setOnClickListener(this);
        tvSignUpActivityalreadyUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btSignUpActivitySignUp:
                if (checkValidation()) {
                    String name, email, pass;
                    name = etSignUpActivityName.getText().toString();
                    email = etSignUpActivityEmail.getText().toString();
                    pass = etSignUpActivityPassword.getText().toString();
                    if (!userDatabase.isEmailExists(email)) {
                        if(userDatabase.insertData(name, email, pass) > 0) {
                            Toast.makeText(SignUpActivity.this, "Registration is success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(SignUpActivity.this, "Registration is failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, " User Already Exits", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.tvSignUpActivityalreadyUser:
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                userDatabase.getAllACHCList();
                break;
        }
    }

    /**
     * Check validation
     */
    private boolean checkValidation() {
        if (ValidationUtility.isValidEditText(etSignUpActivityName, "Enter name") &&
                ValidationUtility.isValidEditText(etSignUpActivityEmail, "Enter email") &&
                ValidationUtility.isValidEditText(etSignUpActivityPassword, "Enter password") &&
                ValidationUtility.isValidEditText(etSignUpActivityConfirmPassword, "Enter confirm password")) {
            if (ValidationUtility.isValidEmail(SignUpActivity.this, etSignUpActivityEmail)) {
                if (ValidationUtility.isValidLength(SignUpActivity.this, etSignUpActivityPassword) &&
                        ValidationUtility.isValidLength(SignUpActivity.this, etSignUpActivityConfirmPassword)) {
                    if (ValidationUtility.isBothPasswordEqual(SignUpActivity.this, etSignUpActivityPassword, etSignUpActivityConfirmPassword)) {
                        return true;
                    }

                }
            }
        }
        return false;
    }
}
