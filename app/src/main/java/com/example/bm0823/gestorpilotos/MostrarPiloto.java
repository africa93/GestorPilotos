package com.example.bm0823.gestorpilotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MostrarPiloto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_piloto);

        Bundle bundle = this.getIntent().getExtras();
        ImageView ivPiloto = (ImageView) findViewById(R.id.pilotoImagen);
        if (bundle.getString("IMAGENNURL") != null) {
            TareaCargarImagen tarea = new TareaCargarImagen();
            ivPiloto.setTag(bundle.getString("IMAGENNURL"));
            tarea.execute(ivPiloto);
        }

        TextView textViewID = (TextView) findViewById(R.id.MpilotoID);
        textViewID.setText(String.format("%d", bundle.getInt("ID")));

        TextView textViewNombre = (TextView) findViewById(R.id.MpilotoNombre);
        textViewNombre.setText(bundle.getString("NOMBRE"));

        TextView textViewDorsal = (TextView) findViewById(R.id.MpilotoDorsal);
        textViewDorsal.setText(String.format("%d", bundle.getInt("DORSAL")));

        TextView textViewMoto = (TextView) findViewById(R.id.MpilotoMoto);
        textViewMoto.setText(bundle.getString("MOTO"));

        CheckBox cbActivo = (CheckBox) findViewById(R.id.MpilotoActivo);
        cbActivo.setClickable(false);
        cbActivo.setFocusable(false);
        cbActivo.setFocusableInTouchMode(false);
        cbActivo.setChecked(bundle.getBoolean("ACTIVO"));


        setResult(RESULT_OK);

    }


    private class TareaCargarImagen extends AsyncTask<ImageView, Void, Bitmap> {

        ImageView imageView = null;

        @Override
        protected Bitmap doInBackground(ImageView... imageViews) {
            this.imageView = imageViews[0];
            return download_Image((String) imageView.getTag());
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }

        private Bitmap download_Image(String url) {
            Bitmap bmp = null;

            try {
                URL ulrn = new URL(url);
                HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
                InputStream is = con.getInputStream();
                bmp = BitmapFactory.decodeStream(is);
                if (bmp != null)
                    return bmp;

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
            return bmp;
        }
    }

}
