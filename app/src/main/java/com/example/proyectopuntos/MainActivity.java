package com.example.proyectopuntos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText x1_et,x2_et,y1_et,y2_et;
    Button btn_punto_medio,btn_pendiente,btn_cuadrante;

    List<Double> puntosApp = new ArrayList<Double>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x1_et = findViewById(R.id.x1_et);
        x2_et = findViewById(R.id.x2_et);
        y1_et = findViewById(R.id.y1_et);
        y2_et = findViewById(R.id.y2_et);
        btn_punto_medio = (Button)findViewById(R.id.btn_punto_medio);
        btn_pendiente = (Button)findViewById(R.id.btn_pendiente);
        btn_cuadrante = (Button)findViewById(R.id.btn_cuadrante);

        btn_punto_medio.setOnClickListener(this);
        btn_pendiente.setOnClickListener(this);
        btn_cuadrante.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(!verificarVacio()){
            Toast.makeText(this,"Hay campos vacíos, por favor introduzca datos." ,Toast.LENGTH_LONG).show();
        }else{
            switch (v.getId()){
                case R.id.btn_punto_medio:
                    Toast.makeText(this,"El punto medio se localiza en "+ puntoMedio(x1_et,y1_et,x2_et,y2_et) ,Toast.LENGTH_LONG).show();
                    break;
                case R.id.btn_pendiente:
                    Toast.makeText(this,"La pendiente es de "+ pendiente() ,Toast.LENGTH_LONG).show();
                    break;
                case R.id.btn_cuadrante:
                    Toast.makeText(this,"El punto "+ getCuadrante(),Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_aleatorio_item:
                asignarAleatorios(x1_et,x2_et,y1_et,y2_et);
                Toast.makeText(this, "Números aleatorios generados", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_distancia_item:
                Toast.makeText(this,"La distancia entre los puntos es de "+distancia(x1_et,y1_et,x2_et,y2_et),Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void asignarAleatorios(EditText x1, EditText x2, EditText y1, EditText y2){
        y1.setText(String.valueOf(randomFunction(-50,50)));
        y2.setText(String.valueOf(randomFunction(-50,50)));
        x1.setText(String.valueOf(randomFunction(-50,50)));
        x2.setText(String.valueOf(randomFunction(-50,50)));
    }

    public int randomFunction(int inferior, int superior){
        int numero;
        numero = (int) Math.floor(Math.random()*(superior-inferior+1)+inferior);
        return numero;
    }

    public String puntoMedio(EditText x1, EditText y1, EditText x2, EditText y2){
        List<Double> punto = new ArrayList <Double> ();
        int Vx2 = Integer.valueOf(x2.getText().toString());
        int Vx1 = Integer.valueOf(x1.getText().toString());
        int Vy1 = Integer.valueOf(y1.getText().toString());
        int Vy2 = Integer.valueOf(y2.getText().toString());
        punto.add((double) ((Vx1+Vx2)/2));
        punto.add((double) ((Vy1+Vy2)/2));
        return String.valueOf(punto);
    }

    public String pendiente() {
        Double punto;

        double x = Integer.valueOf(x2_et.getText().toString())-Integer.valueOf(x1_et.getText().toString());
        double y = Integer.valueOf(y2_et.getText().toString())-Integer.valueOf(y1_et.getText().toString());



        if (x == 0) {
            return ("No se puede hallar la pendiente para valores iguales de X");
        }
        punto = y/x;
        return String.valueOf(punto);
    }

    public String distancia(EditText x1, EditText y1, EditText x2, EditText y2){
        int Vx = Integer.valueOf(x2.getText().toString())-Integer.valueOf(x1.getText().toString());
        int Vy = Integer.valueOf(y2.getText().toString())-Integer.valueOf(y1.getText().toString());

        double distancia = Math.sqrt(Math.pow(Vx,2)+Math.pow(Vy,2));

        return  String.valueOf(distancia);
    }

    public String getCuadrante (){
        List<Integer> punto = new ArrayList<>();
        punto.add(Integer.valueOf(x1_et.getText().toString()));
        punto.add(Integer.valueOf(y1_et.getText().toString()));

        if(punto.get(0) == 0 && punto.get(1) == 0)
            return punto.toString() + " Se encuentra en el origen";

        if(punto.get(0) == 0)
        {
            if(punto.get(1) > 0) return punto.toString() + " está entre el cuadrante I y II";
            else return punto.toString() + " está entre el cuadrante III y IV ";
        }

        if (punto.get(1) == 0)
        {
            if (punto.get(0) > 0) return punto.toString() + " está entre el cuadrante I y IV";
            else return punto.toString() + " esta entre el cuadrante II y III";
        }

        if (punto.get(0) > 0) {
            if (punto.get(1) > 0)
                return punto.toString() +" está en el cuadrante I";
            else if (punto.get(1) < 0)
                return punto.toString() + " está en el cuadrante IV ";
        }

        if (punto.get(0) < 0) {
            if (punto.get(1) > 0) {
                return punto.toString() + " está en el cuadrante II";
            } else if (punto.get(1) < 0) {
                return punto.toString() + " está en el cuadrante III";
            }
        }
        return "Cuadrante no encontrado";
    }

    public boolean verificarVacio(){
        String Vx2 = x2_et.getText().toString();
        String Vx1 = x1_et.getText().toString();
        String Vy1 = y1_et.getText().toString();
        String Vy2 = y2_et.getText().toString();

        return !Vx1.isEmpty() && !Vx2.isEmpty() && !Vy1.isEmpty() && !Vy2.isEmpty();
    }

}

