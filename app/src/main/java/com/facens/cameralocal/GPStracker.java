package com.facens.cameralocal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {
    Context context;
    //carrega o contexto e declara o GPSTracker
    public GPStracker(Context c){
        context = c;
    }
    //inicia o processo de pegar a localização
    public Location getLocation(){
//verifica se o usuário deu permissão de acessar a localização
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
//se não for permitido, envia texto e nao dá retorno.
            Toast.makeText(context, "Não foi permitir", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//se estiver ligado, procura a ultima localização do celular
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
//se não enviar a localização, mesmo com permissão, pede para habilitar o gps
            Toast.makeText(context, "Por favor, habitar o GPS!", Toast.LENGTH_LONG).show();
        }
//não dá retorno pra localização se o gps estiver desligado.
        return null;
    }
    //guarda que o gps foi desligado
    @Override
    public void onProviderDisabled(@NonNull String provider) {    }
    //aguarda a localização nova
    @Override
    public void onLocationChanged(@NonNull Location location) {    }
    //atualiza conforme o gps se move/é desligado
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }

    private class ACCESS_FINE_LOCATION {
    }
}