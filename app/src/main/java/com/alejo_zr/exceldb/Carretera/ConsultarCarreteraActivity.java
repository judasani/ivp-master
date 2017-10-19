package com.alejo_zr.exceldb.Carretera;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alejo_zr.exceldb.BaseDatos;
import com.alejo_zr.exceldb.R;
import com.alejo_zr.exceldb.entidades.Carretera;
import com.alejo_zr.exceldb.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultarCarreteraActivity extends AppCompatActivity {

    private ListView listViewCarreteras;
    private ArrayList<String> listaInformacion;
    private ArrayList<Carretera> listaCarreteras;

    BaseDatos baseDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_carretera);


        baseDatos=new BaseDatos(this);

        listViewCarreteras= (ListView) findViewById(R.id.listViewCarretera);

        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewCarreteras.setAdapter(adaptador);

        listViewCarreteras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Carretera carretera=listaCarreteras.get(pos);

                Intent intent=new Intent(ConsultarCarreteraActivity.this,CarreteraActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("carretera",carretera);

                intent.putExtras(bundle);
                startActivity(intent);


            }
        });

    }

    protected void onStart() {
        super.onStart();
        baseDatos=new BaseDatos(this);

        listViewCarreteras= (ListView) findViewById(R.id.listViewCarretera);

        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewCarreteras.setAdapter(adaptador);

        listViewCarreteras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Carretera carretera=listaCarreteras.get(pos);

                Intent intent=new Intent(ConsultarCarreteraActivity.this,CarreteraActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("carretera",carretera);

                intent.putExtras(bundle);
                startActivity(intent);


            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=baseDatos.getReadableDatabase();

        Carretera carretera=null;
        listaCarreteras= new ArrayList<Carretera>();
        //select * from carretera
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.CARRETERA.TABLA_CARRETERA,null);

        while (cursor.moveToNext()){
            carretera=new Carretera();
            carretera.setId(cursor.getInt(0));
            carretera.setNombreCarretera(cursor.getString(1));
            carretera.setCodCarretera(cursor.getString(2));
            carretera.setTerritorial(cursor.getString(3));
            carretera.setAdmon(cursor.getString(4));
            carretera.setLevantado(cursor.getString(5));

            listaCarreteras.add(carretera);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaCarreteras.size();i++){
            listaInformacion.add(listaCarreteras.get(i).getNombreCarretera()+" - "+listaCarreteras.get(i).getCodCarretera()+" - "
                    +listaCarreteras.get(i).getTerritorial());
        }

    }

}



