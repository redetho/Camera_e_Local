package com.facens.cameralocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.facens.cameralocal.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    //declara as variáveis timer e tarefa
    private final Timer timer = new Timer();
    TimerTask timerTask;
    //inicia override e activity_splash
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //tempo de splash screen e após isso carrega a atividade principal
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotoMainActivity();
                    }
                });
            }
        };
        //quantidade de tempo a esperar
        timer.schedule(timerTask, 3000);
    }
    //abre o script e pagina do projeto MainActivity
    private void gotoMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}