package com.example.autosalon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.autosalon.R;
import com.example.autosalon.models.CarModel;

import java.util.List;

public class CarsItemAdapter extends RecyclerView.Adapter<CarsItemAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<CarModel> carModels;
    private final CarInterface carInterface;

    public CarsItemAdapter(Context context, List<CarModel> carModelList, CarInterface carInterface) {
        this.carModels = carModelList;
        this.inflater = LayoutInflater.from(context);
        this.carInterface = carInterface;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cars_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CarModel carModel = carModels.get(position);
        holder.carName.setText(carModel.getCarName());
        holder.carPrice.setText("Narxi-"+carModel.getCarPrice()+"$");
        holder.carAge.setText("Yili-"+ carModel.getCarAge());
        holder.carColor.setText("Rangi-"+carModel.getCarColor());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                carInterface.deleteCar(carModel.getCarId());
            }
        }); holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                carInterface.updateCar(carModel);
            }
        });


    }

    @Override
    public int getItemCount() {
        return carModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView carName, carPrice, carAge, carColor;
final ImageView delete, update;
        ViewHolder(View view) {
            super(view);
            carName = view.findViewById(R.id.namemashina);
            delete = view.findViewById(R.id.deletecar);
            update = view.findViewById(R.id.updateCar);
            carPrice = view.findViewById(R.id.pricemashina);
            carAge = view.findViewById(R.id.agemashina);
            carColor = view.findViewById(R.id.colormashina);


        }
    }
    public interface CarInterface {
        void deleteCar(int carId);
        void updateCar( CarModel carModel);
    }
}