package com.example.usuario.salvadorcronops;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView ini, det, tot;
Button inicio, parar, reset, fin;
MiCronometro cronometro= null;
MiCronometro2 cronometro2= null;
Boolean activo=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini=findViewById(R.id.ini);
        det=findViewById(R.id.det);
        tot=findViewById(R.id.tot);
        inicio=findViewById(R.id.inicio);
        parar=findViewById(R.id.parar);
        reset=findViewById(R.id.reset);
        fin=findViewById(R.id.fin);

        //Ponemos las propiedades de los botones al iniciar
        parar.setEnabled(false);
        fin.setEnabled(false);
        reset.setVisibility(View.GONE);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cambiamos las propiedades y llamamos al hilo
                parar.setEnabled(true);
                fin.setEnabled(true);
                if (cronometro==null) {
                    cronometro = new MiCronometro();
                    cronometro.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }else{
                    activo=true;
                }
            }
        });

        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Detenemos el hilo
                if (cronometro!=null) {
                    cronometro.parar();
                    parar.setText("Reanudar");
                }else{
                    parar.setText("Parar");
                }
                if (cronometro2==null) {
                    cronometro2 = new MiCronometro2();
                    cronometro2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }else{
                    activo=true;
                }
            }
        });

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset.setVisibility(View.VISIBLE);
                fin.setText("Terminar");

                //Llamamos a la segunda pantalla
                fin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        res(v);
                    }
                });
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicio.setText("Iniciar");
                ini.setText("00:00");
                det.setText("00:00");
                tot.setText("00:00");
            }
        });
    }

    private class MiCronometro extends AsyncTask<String, String, String> {
        int contador = 0;
        boolean activo = true;
        @Override
        protected String doInBackground(String... strings) {

            while (true) {
                while (activo) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    contador++;
                    publishProgress("" + contador);
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            int segundos = Integer.parseInt(values[0]);
            ini.setText(""+ (segundos/60)+":" +(segundos%60));
            tot.setText(""+ (segundos/60)+":" +(segundos%60));
        }

        public void parar(){
            activo = !activo;
        }
    }

    private class MiCronometro2 extends AsyncTask<String, String, String> {
        int contador = 0;
        boolean activo = true;
        @Override
        protected String doInBackground(String... strings) {

            while (true) {
                while (activo) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    contador++;
                    publishProgress("" + contador);
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            int segundos = Integer.parseInt(values[0]);
            det.setText(""+ (segundos/60)+":" +(segundos%60));
            tot.setText(""+ (segundos/60)+":" +(segundos%60));
        }

        public void parar(){
            activo = !activo;
        }
    }

    public void res(View view) {
        Intent i = new Intent(this, resultados.class );
        startActivity(i);
    }
}