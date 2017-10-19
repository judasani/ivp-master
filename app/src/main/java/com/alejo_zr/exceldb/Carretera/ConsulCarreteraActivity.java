package com.alejo_zr.exceldb.Carretera;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alejo_zr.exceldb.BaseDatos;
import com.alejo_zr.exceldb.R;
import com.alejo_zr.exceldb.adaptadores.ListaCarreterasAdapter;
import com.alejo_zr.exceldb.entidades.Carretera;
import com.alejo_zr.exceldb.utilidades.Utilidades;

import java.io.Serializable;
import java.util.ArrayList;

public class ConsulCarreteraActivity extends AppCompatActivity {

    ArrayList<Carretera> listaCarretera;
    RecyclerView recyclerViewCarreteras;

    BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consul_carretera);

        baseDatos = new BaseDatos(this);

        listaCarretera = new ArrayList<>();

        recyclerViewCarreteras  = (RecyclerView) findViewById(R.id.recyclerCarreteras);
        recyclerViewCarreteras.setLayoutManager(new LinearLayoutManager(this));

        consultarListaCarreteras();

        ListaCarreterasAdapter adapter = new ListaCarreterasAdapter(listaCarretera);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),listaCarretera.get(recyclerViewCarreteras.getChildAdapterPosition(view)).
                        getNombreCarretera(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ConsulCarreteraActivity.this,CarreteraActivity.class);

                Carretera carretera = listaCarretera.get(recyclerViewCarreteras.getChildAdapterPosition(view));
                Bundle bundle=new Bundle();
                bundle.putSerializable("carretera_id", (Serializable) carretera);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        recyclerViewCarreteras.setAdapter(adapter);

    }

    private void consultarListaCarreteras() {

        SQLiteDatabase db = baseDatos.getReadableDatabase();

        Carretera carretera = null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.CARRETERA.TABLA_CARRETERA,null);

        while (cursor.moveToNext()){
            carretera = new Carretera();
            carretera.setId(cursor.getInt(0));
            carretera.setNombreCarretera(cursor.getString(1));
            carretera.setCodCarretera(cursor.getString(2));
            carretera.setTerritorial(cursor.getColumnName(3));
            carretera.setAdmon(cursor.getString(4));
            carretera.setLevantado(cursor.getString(5));

            listaCarretera.add(carretera);
        }

    }
}
