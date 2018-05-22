package br.com.parkingsolution;

import java.nio.charset.CodingErrorAction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.xml.sax.Parser;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class registrar extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.tela_registro_entrada_saida);
    }
   
    public void MontaTelaAvariasClick(View v){
        EditText edt01TelaRegistrarPlaca = (EditText) findViewById(R.id.edt01PLACA);
        CheckBox chk01TelaRegistraMercosul = (CheckBox) findViewById(R.id.chk01TelaRegistraMercosul);
        //Log.v("Antes IF",null);
        if(edt01TelaRegistrarPlaca.getText().toString().trim().length() <= 0){	
        	//Log.v("Validacao Tamanho",null);
        	edt01TelaRegistrarPlaca.setError("Preencha o campo Placa!");
        	edt01TelaRegistrarPlaca.requestFocus();
        }
        else{
			        
			SQLiteDatabase db = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
			
			String placa = "";
    		placa = edt01TelaRegistrarPlaca.getText().toString();
    		String condicao = "0";
    		String valorPagar = "";
    		String id = "";
					
			Log.i("AVARIAS", "SELECT * FROM T_Entrada_Parking2 WHERE placa = '" + placa + "' and condicao = '" + condicao + "'");
			
			Cursor cursor = db.rawQuery("SELECT * FROM T_Entrada_Parking2 WHERE placa = ? and condicao = ?; ", new String[]{String.valueOf(placa), String.valueOf(condicao)});
			
			
			if(cursor.moveToFirst()){
				//Log.i("Saida", "cursor.moveToFirst()");
				//Toast.makeText(getBaseContext(), "Veículo já existe", Toast.LENGTH_SHORT).show();
				try{
						String horaEntrada = cursor.getString(cursor.getColumnIndex("entrada_hora")).trim();
						String horaSaida = getDateTime().toString();
						
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						
						Calendar cal = Calendar.getInstance();
						Calendar calFinal = Calendar.getInstance();
						
						cal.setTime(dateFormat.parse(horaEntrada));
						calFinal.setTime(dateFormat.parse(horaSaida));
						
						long millis = calFinal.getTimeInMillis() - cal.getTimeInMillis();
						
						Log.i("Millis", "millis" + millis);
						
						long segundos = millis / 1000;

						Log.i("Segundo", "segundos" + segundos);
						
						long minutos = segundos / 60;

						Log.i("Minuto", "minuto" + minutos);
						
						double valorPagar2 = minutos * 0.25;
						
						valorPagar = String.valueOf(valorPagar2);
						
						Log.i("ValorPagar", "valor2" + valorPagar);
						
						Log.i("ValorPagar", "valor" + valorPagar2);
						
						SQLiteDatabase db3 = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
						ContentValues cvt = new ContentValues();
			    		//Log.v("Inserindo dados",null);
			    		cvt.put("placa" , placa);
			    		cvt.put("entrada_hora", horaEntrada);
			    		cvt.put("saida_hora", horaSaida);
			    		cvt.put("valor", valorPagar2);
			    		cvt.put("forma_pagamento", "0");
			    		cvt.put("condicao", condicao);
			    		
			    		Log.i("Registrar", "Insert");
			    		
			    		if(db3.insert("T_Saida_Parking5", "_id", cvt) > 0){
			    			Toast.makeText(getBaseContext(), "Registro de saída confirmado", Toast.LENGTH_SHORT).show();
			    		}
			    		else{
			    			Toast.makeText(getBaseContext(), "Erro no registro de cadastro", Toast.LENGTH_SHORT).show();
			    		}
			    		db3.close();
			 		}
			 		catch(Exception ex){
			 			Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
			 		}
						id = cursor.getString(cursor.getColumnIndex("_id")).trim();
						
						int codigo = Integer.parseInt(id);
						
						SQLiteDatabase db2 = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
			    		ContentValues cvt2 = new ContentValues();
			    		//Log.v("Inserindo dados",null);
			    		cvt2.put("condicao", "1");
			    		Log.v("Coletei Dados",null);
			    		
			    		edt01TelaRegistrarPlaca.setText("");
	    		
			    		if(db2.update("T_Entrada_Parking2", cvt2, "_id = ?", new String[]{String.valueOf(codigo)}) > 0){
			   	
			    		}
				    		db2.close();
			
					    	Intent chamaPagamento = new Intent(getBaseContext(), tela_pagamento.class);
							
					    	Log.i("AVARIAS", "Antes de putExtra : " + valorPagar.toString().trim());
					    	chamaPagamento.putExtra("pagar", valorPagar.toString().trim());
					    	
					    	Log.i("AVARIAS", "Antes de putExtra 2");
					    	
					    	chamaPagamento.putExtra("id", placa.toUpperCase().trim());
					    	
							startActivity(chamaPagamento);
							    	
							finish();
						}
						else{
			
				    	Intent chamaAvarias = new Intent(getBaseContext(), registrar_avarias.class);
				        
				        chamaAvarias.putExtra("PlacaRegistrar", placa.toUpperCase().trim());
						    	
						startActivity(chamaAvarias);
						    	
						finish();
						}
						db.close();
					}
    }
			private String getDateTime(){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			return dateFormat.format(date);
		}
	}