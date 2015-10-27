package com.example.bm0823.gestorpilotos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class PilotoAdapter extends ArrayAdapter<Piloto>{

    private ArrayList<Piloto> pilotos;
    private Context context;

    public PilotoAdapter(Context context, ArrayList<Piloto> pilotos) {
        super(context, R.layout.layout_listado_pilotos, pilotos);
        this.pilotos = pilotos;
        this.context = context;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_listado_pilotos, null);
        }
        Piloto piloto = pilotos.get(position);

        if(piloto != null){
            TextView tvID = (TextView) convertView.findViewById(R.id.pilotoId);
            tvID.setText(String.format("%d", piloto.get_id()));

            TextView tvNombre = (TextView) convertView.findViewById(R.id.pilotoNombre);
            tvNombre.setText(piloto.get_nombre());

            TextView tvDorsal = (TextView) convertView.findViewById(R.id.pilotoDorsal);
            tvDorsal.setText(String.format("%d", piloto.get_dorsal()));

            TextView tvMoto = (TextView) convertView.findViewById(R.id.pilotoMoto);
            tvMoto.setText(piloto.get_moto());

            CheckBox cbActivo = (CheckBox) convertView.findViewById(R.id.pilotoActivo);
            cbActivo.setClickable(false);
            cbActivo.setFocusable(false);
            cbActivo.setFocusableInTouchMode(false);
            cbActivo.setChecked(piloto.is_activo());
        }
        return convertView;
    }
}
