package br.unibh.sdm.apponlybank.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import br.unibh.sdm.apponlybank.R;

public class SplashActivity extends AppCompatActivity {

    Integer tempoEspera = 1000*10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        trocarTela();
    }

    private void trocarTela() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent trocarDeTela = new Intent(SplashActivity.this, ListaClienteActivity.class);
                startActivity(trocarDeTela);
                finish();
            }
        }, tempoEspera);

    }

}