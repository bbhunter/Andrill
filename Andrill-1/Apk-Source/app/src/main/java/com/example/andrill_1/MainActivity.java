package com.example.andrill_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrill_1.models.LoginResponse;
import com.example.andrill_1.models.UserModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MAIN_LOG    :   ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initailComponent();


    }

    private void initailComponent() {
        Button goBtn = findViewById(R.id.go_btn);
        final EditText userNameET = findViewById(R.id.et_username);
        final EditText passwordET = findViewById(R.id.et_password);


        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userNameET.getText().toString().isEmpty() && !passwordET.getText().toString().isEmpty())
                    userRequest(userNameET.getText().toString(), passwordET.getText().toString());
            }
        });

    }


    private void userRequest(String username, String password) {

        APIClient apiClient = new APIClient(getApplicationContext());
        APIInterFace apiService = apiClient.getClient().create(APIInterFace.class);

        UserModel userModel = new UserModel(username, password);
        Call<LoginResponse> call = apiService.login(userModel);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (loginResponse.status.equals("success") && !loginResponse.login_token.isEmpty()) {
                        Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
                    if(!loginResponse.user_id.equals("1")){
                        Intent userActivity = new Intent(getApplicationContext(), UserActivity.class);
                        userActivity.putExtra("token", loginResponse.login_token);
                        userActivity.putExtra("user_id", loginResponse.user_id);
                        userActivity.putExtra("name", loginResponse.display_name);
                        startActivity(userActivity);
                    }else {
                        Intent adminActivity = new Intent(getApplicationContext(), AdminActivity.class);
                        adminActivity.putExtra("token", loginResponse.login_token);
                        adminActivity.putExtra("user_id", loginResponse.user_id);
                        adminActivity.putExtra("name", loginResponse.display_name);
                        startActivity(adminActivity);

                    }

                }else{
                    Toast.makeText(getApplicationContext(),"FAILURE",Toast.LENGTH_SHORT).show();

                }
                Log.d(TAG, response.body().login_token);
                Log.d(TAG, response.body().status);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "error" + t.toString());
            }
        });
    }

}
