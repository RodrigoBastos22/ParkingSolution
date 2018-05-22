package br.com.parkingsolution;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class TelaLogin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
    }
    
    public void MontarTelaMenuClick(View v){
    	EditText edt01TelaLoginLogin = (EditText) findViewById(R.id.edt01TelaLoginLogin);
    	EditText edt02TelaLoginSenha = (EditText) findViewById(R.id.edt02TelaLoginSenha);
    	
    	if(edt01TelaLoginLogin.getText().toString().equals("Rbastos") && edt02TelaLoginSenha.getText().toString().equals("12345")){
    		Toast.makeText(getBaseContext(), "Logado com sucesso", Toast.LENGTH_SHORT).show();
        	Intent it = new Intent(getBaseContext(), menu.class);
        	startActivity(it);
        	
        	finish();
    	}
    	else{
    		Toast.makeText(getBaseContext(), "Usuário e Senha não condiz", Toast.LENGTH_SHORT).show();
    		edt01TelaLoginLogin.setText("");
    		edt02TelaLoginSenha.setText("");
    		edt01TelaLoginLogin.requestFocus();
    	}
    }
    
 }
