package com.example.andrill_1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.andrill_1.models.BackModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "USERACTIVITY";
    TextView note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initailComponent();
        userRequest();
    }

    private void initailComponent() {
        TextView welcom=findViewById(R.id.tv_welcom);
        TextView token=findViewById(R.id.tv_token);
        note = findViewById(R.id.tv_note);

        welcom.setText("Hello dear "+getIntent().getStringExtra("name"));
//        token.setText("your token is: \n\n "+getIntent().getStringExtra("token"));

    }




    private void userRequest() {

        APIClient apiClient = new APIClient(getApplicationContext());
        APIInterFace apiService = apiClient.getClient().create(APIInterFace.class);

        BackModel backModel = new BackModel();
        backModel.action="get_contents";
        backModel.auth_token=getIntent().getStringExtra("token");
        Call<BackModel> call = apiService.backRequest(backModel);
        call.enqueue(new Callback<BackModel>() {
            @Override
            public void onResponse(Call<BackModel> call, Response<BackModel> response) {
                BackModel backResponse = response.body();
                if (backResponse.status.equals("success")) {
                    note.setText("user Content :  "+backResponse.user_notes);

                }

            }

            @Override
            public void onFailure(Call<BackModel> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "error" + t.toString());
            }
        });
    }





}
