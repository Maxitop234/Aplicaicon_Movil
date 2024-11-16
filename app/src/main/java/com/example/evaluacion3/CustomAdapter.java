package com.example.evaluacion3;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ModeloRemedios> Lista_remedios;
    private SimpleDateFormat dateFormat;

    public CustomAdapter(Context context, ArrayList<ModeloRemedios> Lista_remedios) {
        this.context = context;
        this.Lista_remedios = Lista_remedios;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    }

    @Override
    public int getCount() {
        return Lista_remedios.size();
    }

    @Override
    public Object getItem(int position) {
        return Lista_remedios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lista_items, null, true);

            holder.Nombre = convertView.findViewById(R.id.Nombre);
            holder.Cantidad = convertView.findViewById(R.id.Cantidad);
            holder.Fecha_Vencimiento = convertView.findViewById(R.id.Fecha_Vencimiento);
            holder.mg = convertView.findViewById(R.id.mg);
            holder.Presentacion = convertView.findViewById(R.id.Presentacion);
            holder.Descripcion = convertView.findViewById(R.id.Descripcion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Conseguir los datos para mostrar
        ModeloRemedios remedio = Lista_remedios.get(position);
        holder.Nombre.setText("Nombre: " + remedio.getNombre());
        holder.Cantidad.setText("Cantidad: " + String.valueOf(remedio.getCantidad()));
        holder.Fecha_Vencimiento.setText(remedio.getFechaVencimiento());
        holder.mg.setText("Mg: " + String.valueOf(remedio.getMg()));
        holder.Presentacion.setText("Categoria: " + remedio.getCategoria());
        holder.Descripcion.setText("Descripcion: " + remedio.getDescripcion());

        // Logear la fecha
        Log.d("CustomAdapter", "FechaVencimiento String: " + remedio.getFechaVencimiento());

        try {
            // Conseguir el resultado de la fecha
            Date expiryDate = dateFormat.parse(remedio.getFechaVencimiento());

            if (expiryDate != null) {
                // Log the parsed date
                Log.d("CustomAdapter", "Parsed Date: " + expiryDate.toString());


                if (isExpiringSoon(expiryDate)) {
                    holder.Nombre.setTextColor(Color.RED);
                    holder.Fecha_Vencimiento.setTextColor(Color.RED); // Marcar en rojo
                } else {
                    holder.Fecha_Vencimiento.setTextColor(Color.WHITE);
                    holder.Nombre.setTextColor(Color.WHITE);
                }
            } else {
                Log.e("CustomAdapter", "Parsed date is null");
                holder.Fecha_Vencimiento.setTextColor(Color.GRAY);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("CustomAdapter", "Error parsing date: " + remedio.getFechaVencimiento());
            holder.Fecha_Vencimiento.setTextColor(Color.GRAY); // en caso de erro rGRIS
        }

        return convertView;
    }

    private boolean isExpiringSoon(Date expiryDate) {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Calcular tres meses
        calendar.add(Calendar.MONTH, 3);
        Date threeMonthsLater = calendar.getTime();

        // ver si la fecha de expiracion esta dentro de tres meses
        return expiryDate.after(currentDate) && expiryDate.before(threeMonthsLater);
    }

    private static class ViewHolder {
        protected TextView Nombre, Cantidad, Fecha_Vencimiento, mg, Presentacion, Descripcion;
    }
}