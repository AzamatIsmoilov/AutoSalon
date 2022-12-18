package com.example.autosalon.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.autosalon.R;
import com.example.autosalon.models.CarModel;


public class CarDataUpdateDiolog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public EditText carName, carColor, carPrice;
    public ClickDiolog diologInterface;
    CarModel carModelOld;

    public CarDataUpdateDiolog(Activity a, CarModel carModelOld, ClickDiolog diologInterface) {
        super(a);
        this.c = a;
        this.carModelOld = carModelOld;

        this.diologInterface = diologInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.car_update_dialog);
        yes = findViewById(R.id.btn_yes);
        no = findViewById(R.id.btn_no);
        carColor = findViewById(R.id.carColor);
        carName = findViewById(R.id.name);

        carPrice = findViewById(R.id.price);
        carColor.setText(carModelOld.getCarColor());
        carName.setText(carModelOld.getCarName());
        carPrice.setText(carModelOld.getCarPrice());
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                CarModel carModel = new CarModel();
                carModel.setCarColor(carColor.getText().toString());
                carModel.setCarName(carName.getText().toString());
                carModel.setCarPrice(carPrice.getText().toString());
                carModel.setCarId(carModelOld.getCarId());
                diologInterface.updateCarDio(carModel);

                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    public interface ClickDiolog {
        void updateCarDio(CarModel carModel);
    }
}