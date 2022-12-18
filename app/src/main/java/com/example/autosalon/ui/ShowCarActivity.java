package com.example.autosalon.ui;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.autosalon.R;
import com.example.autosalon.adapters.CarsItemAdapter;
import com.example.autosalon.database.CarDatabase;
import com.example.autosalon.models.CarDao;
import com.example.autosalon.models.CarModel;
import com.example.autosalon.utils.CarDataUpdateDiolog;

import java.util.ArrayList;
import java.util.List;

public class ShowCarActivity extends AppCompatActivity implements CarsItemAdapter.CarInterface, CarDataUpdateDiolog.ClickDiolog {
    RecyclerView recyclerView;
    CarsItemAdapter carsItemAdapter;
    List<CarModel> casrList = new ArrayList<>();
    CarDatabase database;
    int DeleteCarId=-1;
    private CarModel updateCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car);
        init();
    }

    public void init() {
        recyclerView = findViewById(R.id.carsRecyclearView);
        carsItemAdapter = new CarsItemAdapter(this, casrList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(carsItemAdapter);
        database = Room.databaseBuilder(getApplicationContext(), CarDatabase.class, "learning_database").build();
        new UchirishAsyncTask().execute();
    }

    @Override
    public void deleteCar(int carId) {
this.DeleteCarId=carId;
new UchirishAsyncTask().execute();
    }

    @Override
    public void updateCar(CarModel carModel) {
        CarDataUpdateDiolog cdd=new CarDataUpdateDiolog(this, carModel,this);
        cdd.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void updateCarDio(CarModel carModel) {
        this.updateCar=carModel;
        new MashinaUzgartirishAsyncTask().execute();
    }

    private class MalumotKorishAsyncTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ShowCarActivity.this, "Kuting!  ", "malumotlar yuklanmoqda!");
        }

        @Override
        protected String doInBackground(String... params) {
            CarDao carDao = database.carDao();
            casrList.clear();
            casrList.addAll(carDao.getAll());

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            carsItemAdapter.notifyDataSetChanged();
        }


    }private class MashinaUzgartirishAsyncTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ShowCarActivity.this, "Kuting", "malomotlar yuklanmoqda");
        }

        @Override
        protected String doInBackground(String... params) {
            CarDao carDao = database.carDao();

            carDao.updateCar(updateCar.getCarId(),updateCar.getCarName(),updateCar.getCarColor(),updateCar.getCarPrice());
            return "";
        }


        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            new MalumotKorishAsyncTask().execute();
        }


    }
    private class UchirishAsyncTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ShowCarActivity.this, "Kuting!  ", "malumotlar yuklanmoqda!");
        }

        @Override
        protected String doInBackground(String... params) {
            CarDao carDao = database.carDao();
           carDao.deleteById(DeleteCarId);

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();

            new MalumotKorishAsyncTask().execute();
        }


    }
}