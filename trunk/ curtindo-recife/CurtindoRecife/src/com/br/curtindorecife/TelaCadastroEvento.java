package com.br.curtindorecife;


import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;

public class TelaCadastroEvento extends Activity implements OnClickListener {

	Button btnCriar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_cadastro_evento);
		
		navegacao();
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_cadastro_evento, menu);
		return true;
	}
	
	public void navegacao(){
		btnCriar = (Button)findViewById(R.id.btnCriar);
		btnCriar.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnCriar){
			// 1. Instantiate an AlertDialog.Builder with its constructor
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			// 2. Chain together various setter methods to set the dialog characteristics
			builder.setMessage("Evento cadastrado com sucesso")
			       .setTitle("Sucesso!");

			// 3. Get the AlertDialog from create()
			AlertDialog dialog = builder.create();
			dialog.show();
			Intent intent = new Intent(TelaCadastroEvento.this, TelaPrincipal.class);
			startActivity(intent);
			
			
		}
		
	}

}