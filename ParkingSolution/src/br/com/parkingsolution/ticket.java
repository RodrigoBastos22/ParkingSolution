package br.com.parkingsolution;

import java.util.zip.Inflater;

import br.com.parkingsolution.R.layout;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ticket extends Activity{
	 private String placaRegistrar;
	 
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket);
        
        Intent chamaTicket = getIntent();
        placaRegistrar = chamaTicket.getStringExtra("PlacaRegistrar2");
		String condicao = "0";
        
        SQLiteDatabase db = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
        
        Log.i("Ticket", "SELECT * FROM T_Entrada_Parking2 WHERE placa = '" + placaRegistrar + "' and condicao = '" + condicao + "'");
		
        
        Cursor cursor = db.rawQuery("SELECT * FROM T_Entrada_Parking2 WHERE placa = ? and condicao = ?; ", new String[]{String.valueOf(placaRegistrar), String.valueOf(condicao)});
		
        
        if(cursor.moveToFirst()){
        	
        	try{
        		
		        String Horaentrada = cursor.getString(cursor.getColumnIndex("entrada_hora")).trim();
		        String placa = cursor.getString(cursor.getColumnIndex("placa")).trim();
		        
		        Log.i("Placa", placa);
		        Log.i("Hora", Horaentrada);
		        
				TextView txt02TelaTicketHorario = (TextView) findViewById(R.id.txt02TelaTicketHorario);
		        TextView txt01TelaTicketPlaca = ((TextView) findViewById(R.id.txt01TelaTicketPlaca));
		
		        
		        
		        txt01TelaTicketPlaca.setText(placa);
		        txt02TelaTicketHorario.setText(Horaentrada);
        	}
        	catch(Exception ex){
        		
        	}
        }
       
	}
    public void MontaTelaRegistro2Click(View v){
    	Intent it = new Intent(getBaseContext(), registrar.class);
 		startActivity(it);
 		finish();
    };
	
}
