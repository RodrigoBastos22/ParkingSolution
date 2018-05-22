package br.com.parkingsolution;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class avarias_select extends Activity{
	
	private String placa;
	
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("AVARIAS", "Ao chegar");
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_avarias_select);
        
        Intent i = getIntent();
        placa = i.getStringExtra("Placa");
        Log.i("AVARIAS", "Pegou placa : " + placa);
		Log.i("AVARIAS", "Passou bundle");
	}
	
	public void onResume(){
		super.onResume();
		Log.i("AVARIAS", "onResume");
		//EditText edt01TelaConsultaAvariasPlaca = (EditText) findViewById(R.id.edt01TelaConsultaAvariasPlaca);
		
		SQLiteDatabase db = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
		
		//Log.i("AVARIAS", "SELECT * FROM T_EntradaParking WHERE placa = 'HAHAHA'; " + placa + " <<<<<");
		Cursor cursor = db.rawQuery("SELECT * FROM T_Entrada_Parking2 WHERE placa = '" + placa + "' AND condicao = '0'; ", null);
		
		//Cursor cursor = db.rawQuery("SELECT * FROM T_EntradaParking;", null);
		if(cursor.moveToFirst()){
		
		String[] ArrayFrom = {"_id", "placa", "entrada_hora", "direita_frente", "direita_meio", "direita_tras", "esquerda_frente", "esquerda_meio",
				"esquerda_tras", "frente", "traseira"};
		
		int[] ArrayTo  = {R.id.txt01Id, R.id.txt02Placa, R.id.txt11Entrada, R.id.txt04LateralDireitaDianteira, R.id.txt05LateralDireitaMeio, R.id.txt06LateralDireitaTraseira,
						  R.id.txt08LateralEsquerdaDianteira, R.id.txt09LateralEsquerdaMeio, R.id.txt10LateralEsquerdaTraseira, R.id.txt03Dianteira, R.id.txt07Traseira};
		
		android.widget.SimpleCursorAdapter ad = new android.widget.SimpleCursorAdapter(getBaseContext(), R.layout.tela_retorna_avarias,
				cursor, ArrayFrom, ArrayTo, 0);
		
		ListView lvt01AvariasSelect = (ListView)findViewById(R.id.lvt01AvariasSelect);
		
		lvt01AvariasSelect.setAdapter(ad);
		}
	}
}




	