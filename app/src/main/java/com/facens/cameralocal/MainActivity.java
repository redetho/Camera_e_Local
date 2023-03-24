package com.facens.cameralocal;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facens.cameralocal.R;

public class MainActivity extends AppCompatActivity {
    //declara as variáveis
    private ImageView imageViewFoto;
    private Button btnGeo;
    //cria a interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//conecta o botão gps e pede permissao para usar localização
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 123);
//espera o botão ser apertado
        btnGeo.setOnClickListener(new View.OnClickListener() {
            //após ser apertado, liga a aplicação gps e coleta a localização
            @Override
            public void onClick(View view) {
                GPStracker g = new GPStracker(getApplication());
                Location l = g.getLocation();
//se a localização existir, mostra a atitude e longitude
                if(l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE: " + lat +"\n LONGITUDE: " + lon, Toast.LENGTH_LONG).show();
                }
            }
        });
//verifica se o usuário permitiu ou não a utilização da câmera.
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CAMERA}, 0);
        }
//encontra onde a foto da camera devia ficar e espera o botao de foto ser apertado
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {
//inicia o método OnClick
@Override
public void onClick(View view) {
    tirarFoto();
}
        });
}
    //pede para acessar a camera do celular
    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //checa o resultado do TirarFoto
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//se a foto foi tirada com sucesso, coloca a foto no aplicativo.
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}