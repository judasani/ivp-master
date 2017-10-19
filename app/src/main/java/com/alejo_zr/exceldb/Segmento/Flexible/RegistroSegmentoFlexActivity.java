package com.alejo_zr.exceldb.Segmento.Flexible;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alejo_zr.exceldb.BaseDatos;
import com.alejo_zr.exceldb.R;
import com.alejo_zr.exceldb.utilidades.Utilidades;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistroSegmentoFlexActivity extends AppCompatActivity {


    private EditText campoNCalzadas, campoNCarriles, campoAnchoCarril, campoAnchoBerma, campoPRI, campoPRF, campoComentarios,campoFecha;
    private TextView tvNombre_Carretera_SegmentoFlex;
    private TextInputLayout input_camponCalzadas,input_campoNCarriles,input_campoAnchoCarril,input_campoAnchoBerma,input_campoPRI;

    private  int dia, mes, ano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_segmento_flex);


        campoNCalzadas = (EditText) findViewById(R.id.campoNCalzadasFlex);
        campoNCarriles = (EditText) findViewById(R.id.campoNCarrilesFlex);
        campoAnchoCarril = (EditText) findViewById(R.id.campoAnchoCarrilFlex);
        campoAnchoBerma = (EditText) findViewById(R.id.campoAnchoBermaFlex);
        campoPRI = (EditText) findViewById(R.id.campoPRIFlex);
        campoPRF = (EditText) findViewById(R.id.campoPRFFlex);
        campoComentarios = (EditText) findViewById(R.id.campoComentariosFlex);
        campoFecha = (EditText) findViewById(R.id.campoFechaSegmentoFlexRegistro);



        tvNombre_Carretera_SegmentoFlex = (TextView) findViewById(R.id.tvNombre_Carretera_SegmentoFlex);

        input_camponCalzadas = (TextInputLayout) findViewById(R.id.input_camponCalzadasFlex);
        input_campoNCarriles= (TextInputLayout) findViewById(R.id.input_campoNCarrilesFlex );
        input_campoAnchoCarril= (TextInputLayout) findViewById(R.id.input_campoAnchoCarrilFlex );
        input_campoAnchoBerma= (TextInputLayout) findViewById(R.id.input_campoAnchoBermaFlex);
        input_campoPRI= (TextInputLayout) findViewById(R.id.input_campoPRIFlex);


        Bundle bundle = getIntent().getExtras();
        String dato_nom = bundle.getString("nom_carretera").toString();
        tvNombre_Carretera_SegmentoFlex.setText(dato_nom);
        
        fechaActual();


    }

    private void fechaActual() {

        final Calendar c = Calendar.getInstance();
        ano = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String s = formatter.format(c.getTime());
        campoFecha.setText(s);
    }

    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnRegistroSegmentoFlex:
                verificarDatos();
                break;
            case R.id.btnFecha:
                obtenerFecha();
                break;
        }


    }

    private void obtenerFecha() {
        final Calendar c= Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,android.R.style.Theme_Holo_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                campoFecha.setText(dayOfMonth + "/" + (monthOfYear) + "/" + year);
            }
        }, dia, mes, ano);
        datePickerDialog.show();
    }

    /**Se verifica que los datos minimos sean diligenciados**/

    private void verificarDatos() {
        boolean isValid = true;
        if(campoNCalzadas.getText().toString().trim().isEmpty()){
            input_camponCalzadas.setError("Ingrese el número de calzadas");
            isValid=false;
        }else{
            input_camponCalzadas.setErrorEnabled(false);
        }
        if(campoNCarriles.getText().toString().trim().isEmpty()){
            input_campoNCarriles.setError("Ingrese el número de carriles");
            isValid=false;
        }else{
            input_campoNCarriles.setErrorEnabled(false);
        }
        if(campoAnchoCarril.getText().toString().trim().isEmpty()){
            input_campoAnchoCarril.setError("Ingrese el ancho de el carril");
            isValid=false;
        }else{
            input_campoAnchoCarril.setErrorEnabled(false);
        }
        if(campoAnchoBerma.getText().toString().trim().isEmpty()){
            input_campoAnchoBerma.setError("Ingrese el ancho de la berma");
            isValid=false;
        }else{
            input_campoAnchoBerma.setErrorEnabled(false);
        }
        if(campoPRI.getText().toString().trim().isEmpty()){
            input_campoPRI.setError("Ingrese el PRI");
            isValid=false;
        }else{
            input_campoPRI.setErrorEnabled(false);
        }


        if(isValid){
            registrarSegmento();
        }
    }

    private void registrarSegmento() {

        BaseDatos bd=new BaseDatos(this);

        SQLiteDatabase db=bd.getWritableDatabase();

        String insert="INSERT INTO "+ Utilidades.SEGMENTOFLEX.TABLA_SEGMENTO
                +" ( "+Utilidades.SEGMENTOFLEX.CAMPO_NOMBRE_CARRETERA_SEGMENTO+","+Utilidades.SEGMENTOFLEX.CAMPO_CALZADAS_SEGMENTO+","
                +Utilidades.SEGMENTOFLEX.CAMPO_CARRILES_SEGMENTO+","+Utilidades.SEGMENTOFLEX.CAMPO_ANCHO_CARRIL+","+Utilidades.SEGMENTOFLEX.CAMPO_ANCHO_BERMA+","+Utilidades.SEGMENTOFLEX.CAMPO_PRI_SEGMENTO+","
                +Utilidades.SEGMENTOFLEX.CAMPO_PRF_SEGMENTO+","+Utilidades.SEGMENTOFLEX.CAMPO_COMENTARIOS+","+Utilidades.SEGMENTOFLEX.CAMPO_FECHA+")" +
                " VALUES ('"+tvNombre_Carretera_SegmentoFlex.getText().toString()+"' , '"
                +campoNCalzadas.getText().toString()+"' , '"+campoNCarriles.getText().toString()+"' , '"+campoAnchoCarril.getText().toString()+"' , '"
                +campoAnchoBerma.getText().toString()+"' , '"+campoPRI.getText().toString()+"' , '"+campoPRF.getText().toString()+"' , '"
                +campoComentarios.getText().toString()+"' , '"+campoFecha.getText().toString()+"')";

        db.execSQL(insert);
        Toast.makeText(getApplicationContext(),R.string.regisSeg,Toast.LENGTH_SHORT).show();

        db.close();

    }
}
