package br.com.parkingsolution;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class avarias extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_pesquisa_avarias);
    }
    
    public void MontaTelaSelectAvarias(View v){
    			EditText edt01TelaConsultaAvariasPlaca = (EditText) findViewById(R.id.edt01TelaConsultaAvariasPlaca);
    			
    			 if(edt01TelaConsultaAvariasPlaca.getText().toString().trim().length() <= 0){	
    		        	//Log.v("Validacao Tamanho",null);
    				 edt01TelaConsultaAvariasPlaca.setError("Preencha o campo Placa!");
    				 edt01TelaConsultaAvariasPlaca.requestFocus();
    		        }
    		        else{
    		   
    			String placa = edt01TelaConsultaAvariasPlaca.getText().toString().trim();
    			
				SQLiteDatabase db = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
				
				Log.i("AVARIAS Select", "SELECT * FROM T_Entrada_Parking2 WHERE placa = '" + placa + "' and condicao = '0'");
				Cursor cursor = db.rawQuery("SELECT * FROM T_Entrada_Parking2 WHERE placa = '" + placa + "' AND condicao = '0'; ", null);
				
				//Cursor cursor = db.rawQuery("SELECT * FROM T_EntradaParking;", null);
				if(cursor.moveToFirst()){
						Log.i("Avr", "cursor.moveToFirst()"); 
					
						Intent it = new Intent(getBaseContext(), avarias_select.class);
				    	Log.i("AVARIAS", "Antes de pegar EditText");
				    	Log.i("AVARIAS", "Antes de putExtra : " + edt01TelaConsultaAvariasPlaca.getText());
				    	it.putExtra("Placa", edt01TelaConsultaAvariasPlaca.getText().toString());
				    	Log.i("AVARIAS", "Antes de partir");
				    	startActivity(it);
				    	
				    	db.close();
				}
				else{
					Toast.makeText(getBaseContext(), "Veículo não existe", Toast.LENGTH_SHORT).show();
					edt01TelaConsultaAvariasPlaca.setText("");
				}
    		}
    };
}
