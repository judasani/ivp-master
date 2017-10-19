package com.alejo_zr.exceldb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alejo_zr.exceldb.Carretera.ConsultarCarreteraActivity;
import com.alejo_zr.exceldb.Carretera.RegistroCarreteraActivity;
import com.alejo_zr.exceldb.utilidades.Utilidades;

import java.io.File;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseDatos conn = new BaseDatos(this);
        //conn.insertData();

    }

    public void onClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnRegistroCarretera:
                intent = new Intent(MainActivity.this,RegistroCarreteraActivity.class);
                break;
            case R.id.btnConsultarCarretera:
                intent = new Intent(MainActivity.this,ConsultarCarreteraActivity.class);
                break;

            case R.id.btnExportar:
                exportar();
                break;
            case R.id.btnManual:
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.alejo_zr.manual");
                startActivity(launchIntent);
                break;


        }
        if(intent != null){
            startActivity(intent);
        }

    }

    private void exportar() {

        Toast.makeText(getApplicationContext(),"SI RECONOCE EL BOTON",Toast.LENGTH_SHORT).show();
        BaseDatos baseDatos = new BaseDatos(this);
        //conn.insertData();
        final Cursor cursor = baseDatos.getroad();
        final Cursor cursor1 = baseDatos.getSegmento();
        //final Cursor cursor2 = baseDatos.getroad();

        File sd = Environment.getExternalStorageDirectory();
        String csvFile = "prueba3.xls";

        File directory = new File(sd.getAbsolutePath());
        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        Toast.makeText(getApplicationContext(),"ANTES DEL TRY",Toast.LENGTH_SHORT).show();
        try {
            //Toast.makeText(getApplicationContext(),"ENTRA AL TRY",Toast.LENGTH_SHORT).show();
            //file path
            File file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet("Carreteras", 0);
            WritableSheet sheet1 = workbook.createSheet("Segmentos", 1);


            //Hoja Carreteras
            sheet.addCell(new Label(0, 0, "ID"));
            sheet.addCell(new Label(1, 0, "Nom. Carretera")); // column and row
            sheet.addCell(new Label(2, 0, "Cod. Carretera"));
            sheet.addCell(new Label(3, 0, "Territorial"));
            sheet.addCell(new Label(4, 0, "Admon"));
            sheet.addCell(new Label(5, 0, "Levantado por"));

            //Hoja Segmentos
            sheet1.addCell(new Label(0, 0, "ID"));
            sheet1.addCell(new Label(1, 0, "ID Car."));
            sheet1.addCell(new Label(2, 0, "Tipo Pav.")); // column and row
            sheet1.addCell(new Label(3, 0, "N° Calzadas"));
            sheet1.addCell(new Label(4, 0, "N° Carriles"));
            sheet1.addCell(new Label(5, 0, "Ancho carril(m)"));
            sheet1.addCell(new Label(6, 0, "Ancho berma(m)"));
            sheet1.addCell(new Label(7, 0, "PRI"));
            sheet1.addCell(new Label(8, 0, "PRF"));
            sheet1.addCell(new Label(9, 0, "Comentarios"));

            if (cursor.moveToNext()) {

                do {

                    int i = cursor.getPosition();
                    int il =i+1;
                    //Campos Carreteras
                    String id = cursor.getString(cursor.getColumnIndex(Utilidades.CARRETERA.CAMPO_ID_CARRETERA));
                    String nombre = cursor.getString(cursor.getColumnIndex(Utilidades.CARRETERA.CAMPO_NOMBRE_CARRETERA));
                    String codCarretera = cursor.getString(cursor.getColumnIndex(Utilidades.CARRETERA.CAMPO_CODIGO_CARRETERA));
                    String territorial = cursor.getString(cursor.getColumnIndex(Utilidades.CARRETERA.CAMPO_TERRITO_CARRETERA));
                    String admon= cursor.getString(cursor.getColumnIndex(Utilidades.CARRETERA.CAMPO_ADMON_CARRETERA));
                    String levantado = cursor.getString(cursor.getColumnIndex(Utilidades.CARRETERA.CAMPO_LEVANTADO_CARRETERA));


                    //Se Llenan las casillas Carreteras
                    sheet.addCell(new Label(0, il, id));
                    sheet.addCell(new Label(1, il, nombre));
                    sheet.addCell(new Label(2, il, codCarretera));
                    sheet.addCell(new Label(3, il, territorial));
                    sheet.addCell(new Label(4, il, admon));
                    sheet.addCell(new Label(5, il, levantado));
                } while (cursor.moveToNext());

            }
            //Toast.makeText(getApplicationContext(),"Lleno carreteras",Toast.LENGTH_SHORT).show();

            int ifs = 1;

            if (cursor1.moveToNext()) {
                //Toast.makeText(getApplicationContext(),"Entro al IF "+ifs,Toast.LENGTH_SHORT).show();
                do {
                    //Toast.makeText(getApplicationContext(),"Entro al do "+ifs,Toast.LENGTH_SHORT).show();
                    int is = cursor1.getPosition();
                    int ils =is+1;


                    //Toast.makeText(getApplicationContext(),"Va a crear campos segmentos ",Toast.LENGTH_SHORT).show();

                    //Campos Segmentos
                    String id_seg = cursor1.getString(cursor1.getColumnIndex(Utilidades.SEGMENTOFLEX.CAMPO_ID_SEGMENTO));
                    String id_seg_car = cursor1.getString(cursor1.getColumnIndex(Utilidades.SEGMENTOFLEX.CAMPO_NOMBRE_CARRETERA_SEGMENTO));

                    String nCalzadas = cursor1.getString(cursor1.getColumnIndex(Utilidades.SEGMENTOFLEX.CAMPO_CALZADAS_SEGMENTO));
                    String nCarriles = cursor1.getString(cursor1.getColumnIndex(Utilidades.SEGMENTOFLEX.CAMPO_CARRILES_SEGMENTO));
                    String anchoCarril = cursor1.getString(cursor1.getColumnIndex(Utilidades.SEGMENTOFLEX.CAMPO_ANCHO_CARRIL));
                    String anchoBerma = cursor1.getString(cursor1.getColumnIndex(Utilidades.SEGMENTOFLEX.CAMPO_ANCHO_BERMA));
                    String pri = cursor1.getString(cursor1.getColumnIndex(Utilidades.SEGMENTOFLEX.CAMPO_PRI_SEGMENTO));
                    String prf = cursor1.getString(cursor1.getColumnIndex(Utilidades.SEGMENTOFLEX.CAMPO_PRF_SEGMENTO));
                    String comentarios = cursor1.getString(cursor1.getColumnIndex(Utilidades.SEGMENTOFLEX.CAMPO_COMENTARIOS));


                    //Toast.makeText(getApplicationContext(),"Va a llenar campos segmentos",Toast.LENGTH_SHORT).show();

                    //Se Llenan las casillas Segmentos
                    sheet1.addCell(new Label(0, ils, id_seg));
                    sheet1.addCell(new Label(1, ils, id_seg_car));

                    sheet1.addCell(new Label(2, ils, nCalzadas));
                    sheet1.addCell(new Label(3, ils, nCarriles));
                    sheet1.addCell(new Label(4, ils, anchoCarril));
                    sheet1.addCell(new Label(5, ils, anchoBerma));
                    sheet1.addCell(new Label(6, ils, pri));
                    sheet1.addCell(new Label(7, ils, prf));
                    sheet1.addCell(new Label(8, ils, comentarios));
                    //Toast.makeText(getApplicationContext(),"Sale del do "+ifs,Toast.LENGTH_SHORT).show();


                    ifs = ifs+1;


                } while (cursor1.moveToNext());

            }
            //Toast.makeText(getApplicationContext(),"DESPUES IF",Toast.LENGTH_SHORT).show();
            //closing cursor
            cursor.close();
            workbook.write();
            workbook.close();
            //Toast.makeText(getApplicationContext(),"ANTES TOAST EXCEL",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplication(), "Data Exported in a Excel Sheet", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Entra al CATCH",Toast.LENGTH_SHORT).show();
        }

    }
}
