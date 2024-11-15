package com.example.evaluacion3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ModeloRemedios> Lista_remedios;

    public CustomAdapter(Context context, ArrayList<ModeloRemedios> Lista_remedios) {
        this.context = context;
        this.Lista_remedios = Lista_remedios;
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
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        // Set the data for each view
        holder.Nombre.setText(Lista_remedios.get(position).getNombre());
        holder.Cantidad.setText(Lista_remedios.get(position).getCantidad());
        holder.Fecha_Vencimiento.setText(Lista_remedios.get(position).getFechaVencimiento());
        holder.mg.setText(Lista_remedios.get(position).getMg());
        holder.Presentacion.setText(Lista_remedios.get(position).getCategoria());
        holder.Descripcion.setText(Lista_remedios.get(position).getDescripcion());

        return convertView;
    }

    private static class ViewHolder {
        protected TextView Nombre, Cantidad, Fecha_Vencimiento, mg, Presentacion, Descripcion;
    }
}