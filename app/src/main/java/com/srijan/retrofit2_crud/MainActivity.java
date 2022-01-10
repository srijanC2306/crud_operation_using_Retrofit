package com.srijan.retrofit2_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.srijan.retrofit2_crud.remote.RetrofitApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etName , etJobName ;
    private Button btnAddUser ;
    private View progressBar ;
    private TextView tvProgress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etJobName = findViewById(R.id.etjobName);
        btnAddUser = findViewById(R.id.btnAddUser);
        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tvProgress);



        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().isEmpty()){

                    etName.setError(" Enter name");
                }
                else if (etJobName.getText().toString().isEmpty()){

                    etJobName.setError("Job Name is required");
                }
                else{

                    postData(etName.getText().toString() ,etJobName.getText().toString() );
                }
            }

            private void postData(String name, String job) {

                progressBar.setVisibility(View.VISIBLE);

               Retrofit retrofit = new Retrofit.Builder()
                       .baseUrl("https://reqres.in/api/")
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();

               RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

               DataModel model = new DataModel(name , job );

               //calling a data that will create a post and pass our model class

                Call<DataModel> call = retrofitApi.createPost(model);

                //Execution

                call.enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(@NonNull Call<DataModel> call, @NonNull Response<DataModel> response) {
                        Toast.makeText(MainActivity.this , "Data insterted Successfully", Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.GONE);

                        etName.setText("");
                        etJobName.setText("");

                        DataModel responseFromApi = response.body();

                        String responseString = "ResponseCode : " + response.code() + "\nName : " + responseFromApi.getName() + "\njob:"+responseFromApi.getJob();

                        tvProgress.setText(responseString);



                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {

                        tvProgress.setText("Error found is : " + t.getMessage());

                    }
                });
            }
        });



    }
}