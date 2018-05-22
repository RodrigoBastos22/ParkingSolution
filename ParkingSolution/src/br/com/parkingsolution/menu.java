package br.com.parkingsolution;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class menu extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_menu);
        
        
        SQLiteDatabase db = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
        
        StringBuilder sqlParking = new StringBuilder();
        sqlParking.append("CREATE  TABLE IF NOT EXISTS [T_Entrada_Parking2] (");
        sqlParking.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlParking.append("placa VARCHAR(10), ");
        sqlParking.append("entrada_hora VARCHAR(30), ");
        sqlParking.append("direita_frente VARCHAR(20), ");
        sqlParking.append("direita_meio VARCHAR(20), ");
        sqlParking.append("direita_tras VARCHAR(20), ");
        sqlParking.append("esquerda_frente VARCHAR(20), ");
        sqlParking.append("esquerda_meio VARCHAR(20), ");
        sqlParking.append("esquerda_tras VARCHAR(20), ");
        sqlParking.append("frente VARCHAR(20), ");
        sqlParking.append("traseira VARCHAR(20), ");
        sqlParking.append("condicao VARCHAR(30)");
        sqlParking.append(");");
        db.execSQL(sqlParking.toString());
        
        
        SQLiteDatabase db2 = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
        
        StringBuilder sqlParking2 = new StringBuilder();
        sqlParking2.append("CREATE  TABLE IF NOT EXISTS [T_Saida_Parking5] (");
        sqlParking2.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlParking2.append("placa VARCHAR(10), ");
        sqlParking2.append("entrada_hora VARCHAR(30), ");
        sqlParking2.append("saida_hora VARCHAR(30), ");
        sqlParking2.append("valor VARCHAR(20), ");
        sqlParking2.append("forma_pagamento VARCHAR(20), ");
        sqlParking2.append("condicao VARCHAR(20)");
        sqlParking2.append(");");
        db2.execSQL(sqlParking2.toString());
        
    }
     
    public void MontaTelaRegistroClick(View v){
    	Intent chamaRegistrar = new Intent(getBaseContext(), registrar.class);
    	startActivity(chamaRegistrar);
    };
    
    public void MontaTelaConsultaAvariasClick(View v){
    	Intent it = new Intent(getBaseContext(), avarias.class);
    	startActivity(it);
    };
    
    
   
}