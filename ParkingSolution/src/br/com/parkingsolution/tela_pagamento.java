package br.com.parkingsolution;
import br.com.parkingsolution.R;
import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class tela_pagamento extends Activity{
	private String PagamentoValor;
	private String id;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_saida);
        

    	TextView txt01TelaPagamentoValor = (TextView) findViewById(R.id.txt01TelaPagamentoValor);
    	RadioButton rdb01TelaPagamentoDinheiro = (RadioButton) findViewById(R.id.rdb01TelaPagamentoDinheiro);
    	RadioButton rdb02TelaPagamentoCredito = (RadioButton) findViewById(R.id.rdb02TelaPagamentoCredito);
    	RadioButton rdb03TelaPagamentoDebito = (RadioButton) findViewById(R.id.rdb03TelaPagamentoDebito);
    	String debito = "Débito";
    	String credito = "Crédito";
    	String dinheiro = "Dinheiro";
		
        Intent chamaPagamento = getIntent();
        PagamentoValor = chamaPagamento.getStringExtra("pagar");
        
        id = chamaPagamento.getStringExtra("id");
        
        String condicao = "0";
        
        Log.i("id", "id2" + id);
        
        txt01TelaPagamentoValor.setText(PagamentoValor);
        
        SQLiteDatabase db = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
        
        Log.i("AVARIAS", "SELECT * FROM T_Saida_Parking2 WHERE placa = " + id + "");
		
        Cursor cursor = db.rawQuery("SELECT * FROM T_Saida_Parking5 WHERE id = ? and condicao = ?; ", new String[]{String.valueOf(id), String.valueOf(condicao)});
        Log.i("cursor", "select passou");
		
		if(cursor.moveToFirst()){
			try{
				
			SQLiteDatabase db2 = openOrCreateDatabase("ParkingSolution.db", Context.MODE_PRIVATE, null);
			ContentValues cvt2 = new ContentValues();
			
			
			Log.v("Inserindo dados", "verifica");
			
			if(rdb01TelaPagamentoDinheiro.isSelected()){
				cvt2.put("forma_pagamento", dinheiro);
			}
			else if(rdb02TelaPagamentoCredito.isSelected()){
				cvt2.put("forma_pagamento", credito);
			}
			else if(rdb03TelaPagamentoDebito.isSelected()){
				cvt2.put("forma_pagamento", debito);
			}
			Log.v("Coletei Dados", "Verificacao");
	
			if(db2.update("T_Saida_Parking50", cvt2, "_id = ?", new String[]{String.valueOf(id)}) > 0){
				Toast.makeText(getBaseContext(), "Pagamento registrado", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(getBaseContext(), "Erro no registro de pagamento", Toast.LENGTH_SHORT).show();
			}
			db.close();
		}
		catch (Exception ex){
			Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}
	
	public void MontaTelaPagamento(View v){
		Intent chamaRegistrar = new Intent(getBaseContext(), registrar.class);
    	startActivity(chamaRegistrar);
	}
}
