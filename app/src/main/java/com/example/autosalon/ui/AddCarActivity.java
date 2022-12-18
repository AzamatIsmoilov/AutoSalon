package com.example.autosalon.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.room.Room;

import com.example.autosalon.R;
import com.example.autosalon.database.CarDatabase;
import com.example.autosalon.models.CarDao;
import com.example.autosalon.models.CarModel;

public class AddCarActivity extends AppCompatActivity {
    EditText CarName, CarColor, CarPrice, CarAge;
    AppCompatButton MashinaQushish, MashinaKurish;
    CarDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        init();
    }

    public void init() {
        CarName = findViewById(R.id.addCarName);
        CarColor = findViewById(R.id.addCarColor);
        CarPrice = findViewById(R.id.addCarPrice);
        CarAge = findViewById(R.id.addCarAge);
        MashinaQushish = findViewById(R.id.MashinaQushish);
        MashinaKurish = findViewById(R.id.MashinaKurish);
        database = Room.databaseBuilder(getApplicationContext(), CarDatabase.class, "learning_database").build();
        MashinaQushish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new MalumotSaqlashAsyncTask().execute();
            }
        });
        MashinaKurish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddCarActivity.this,ShowCarActivity.class);
                startActivity(intent);
            }
        });
    }

    private class MalumotSaqlashAsyncTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(AddCarActivity.this, "Malumotlar yuklanmoqda ", "Iltimos hhamma malumotlar yuklanishini kuting !");
        }

        @Override
        protected String doInBackground(String... params) {
            CarDao carDao = database.carDao();
            CarModel carModel = new CarModel();
            carModel.setCarName(CarName.getText().toString());
            carModel.setCarColor(CarColor.getText().toString());
            carModel.setCarPrice(CarPrice.getText().toString());
            carModel.setCarAge(CarAge.getText().toString());
            carDao.insertAll(carModel);
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            CarName.setText("");
            CarColor.setText("");
            CarPrice.setText("");
            CarAge.setText("");
        }


    }

}
