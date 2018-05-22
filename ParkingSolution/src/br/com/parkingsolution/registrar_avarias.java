package br.com.parkingsolution;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class registrar_avarias extends Activity{
	private String placaRegistrar;
	private String condicao = "0";
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_avarias);
        
        Intent chamaAvarias = getIntent();
        placaRegistrar = chamaAvarias.getStringExtra("PlacaRegistrar");
    }
    
    public void btn01TelaAvariasSalvar(View v){
    	 CheckBox chkFrente = (CheckBox) findViewById(R.id.chkFrente);
         CheckBox chkDireitaFrente = (CheckBox) findViewById(R.id.chkDireitaFrente);
         CheckBox chkDireitaMeio = (CheckBox) findViewById(R.id.chkDireitaMeio);
         CheckBox chkDireitaFundo = (CheckBox) findViewById(R.id.chkDireitaFundo);
         CheckBox chkFundo = (CheckBox) findViewById(R.id.chkFundo);
         CheckBox chkEsquerdaFrente = (CheckBox) findViewById(R.id.chkEsquerdaFrente);
         CheckBox chkEsquerdaMeio = (CheckBox) findViewById(R.id.chkEsquerdaMeio);
         CheckBox chkEsquerdaFundo = (CheckBox) findViewById(R.id.chkEsquerdaFundo);
         
         String Dianteira = "";
         String Traseira = "";
         String LateralDireitaDianteira = "";
         String LateralDireitaMeio = "";
         String LateralDireitaTraseira = "";
         String LateralEsquerdaDianteira = "";
         String LateralEsquerdaMeio = "";
         String LateralEsquerdaTraseira = "";
         
 		if(chkFrente.isChecked()){
 			Dianteira = "Avariado";
 		}
 		else{
 			Dianteira = "Normal";
 		}
 		
 		if(chkDireitaFrente.isChecked()){
 			LateralDireitaDianteira = "Avariado";
 		}
 		else{
 			LateralDireitaDianteira = "Normal";
 		}
 		
 		if(chkDireitaMeio.isChecked()){
 			LateralDireitaMeio = "Avariado";
 		}
 		else{
 			LateralDireitaMeio = "Normal";
 		}
 		
 		if(chkDireitaFundo.isChecked()){
 			LateralDireitaTraseira = "Avariado";
 		}
 		else{
 			LateralDireitaTraseira = "Normal";
 		}
 		
 		if(chkFundo.isChecked()){
 			Traseira = "Avariado";
 		}
 		else{
 			Traseira = "Normal";
 		}
 		
 		if(chkEsquerdaFrente.isChecked()){
 			LateralEsquerdaDianteira = "Avariado";
 		}
 		else{
 			LateralEsquerdaDianteira = "Normal";
 		}
 		
 		if(chkEsquerdaMeio.isChecked()){
 			LateralEsquerdaMeio = "Avariado";
 		}
 		else{
 			LateralEsquerdaMeio = "Normal";
 		}
 		
 		if(chkEsquerdaFundo.isChecked()){
 			LateralEsquerdaTraseira = "Avariado";
 		}
 		else{
 			LateralEsquerdaTraseira = "Normal";
 		}	
 		try{
 		
 			SQLiteDatabase db = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
    		ContentValues cvt = new ContentValues();
    		//Log.v("Inserindo dados",null);
    		cvt.put("placa" , placaRegistrar);
    		cvt.put("entrada_hora", getDateTime());
    		cvt.put("direita_frente", LateralDireitaDianteira);
    		cvt.put("direita_meio", LateralDireitaMeio);
    		cvt.put("direita_tras", LateralDireitaTraseira);
    		cvt.put("esquerda_frente", LateralEsquerdaDianteira);
    		cvt.put("esquerda_meio", LateralEsquerdaMeio);
    		cvt.put("esquerda_tras", LateralEsquerdaTraseira);
    		cvt.put("frente", Dianteira);
    		cvt.put("traseira", Traseira);
    		cvt.put("condicao", condicao);
    		//Log.v("Coletei Dados",null);
    		
    		if(db.insert("T_Entrada_Parking2", "_id", cvt) > 0){
    			Toast.makeText(getBaseContext(), "Registro de entrada confirmado", Toast.LENGTH_SHORT).show();
    		}
    		else{
    			Toast.makeText(getBaseContext(), "Erro no registro de cadastro", Toast.LENGTH_SHORT).show();
    		}
    		db.close();
 		}
 		catch(Exception ex){
 			Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
 		}
 		
 		Log.i("TEla", "Ticket");
 		
		
		Intent chamaTicket = new Intent(getBaseContext(), ticket.class);
        
        chamaTicket.putExtra("PlacaRegistrar2", placaRegistrar.toUpperCase().trim());
 		
        startActivity(chamaTicket);
        
 		finish();
 		

 		Log.i("TEla", "Ticket  foi");
    }
    
    	private String getDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}




	/*SQLiteDatabase db = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);				
ContentValues cvt = new ContentValues();
Log.i("UPDATE", "Tentando UPDATE");

cvt.put("placa", placaRegistrar);
cvt.put("entrada_hora", getDateTime());
cvt.put("direita_frente", LateralDireitaDianteira);
cvt.put("direita_meio", LateralDireitaMeio);
cvt.put("direita_tras", LateralDireitaTraseira);
cvt.put("esquerda_frente", LateralEsquerdaDianteira);
cvt.put("esquerda_meio", LateralEsquerdaMeio);
cvt.put("esquerda_tras", LateralEsquerdaTraseira);
cvt.put("frente", Dianteira);
cvt.put("traseira", Traseira);


Log.i("INSERIR", "Inseriu UPDATE");

Log.i("ID", "Antes de pegar Id ");
if(db.update("T_EntradaParking", cvt, "_id = ?", new String[] {String.valueOf(max)}) > 0){
	Toast.makeText(getBaseContext(), "Registro de entrada confirmado", Toast.LENGTH_SHORT).show();
	Log.i("Pego", "Pegou ID");
}
else{
	Toast.makeText(getBaseContext(), "Erro no registro de cadastro", Toast.LENGTH_SHORT).show();
}
db.close();*/