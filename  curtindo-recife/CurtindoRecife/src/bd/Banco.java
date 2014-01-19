package bd;

import java.util.ArrayList;

import dominio.Evento;
import dominio.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco{
	
	public  Banco(Context context){
		this.context = context;
		
	}
	
	private final String nomeTabela1 = "tabelaUsuarios";
	private final String nomeTabela2 ="tabelaEventos";
	private final String nomeTabela3 ="tabelaMeusEventos";
	private final String nomeBanco = "CurtindoRecifeDB";
	private final int versaoBD = 1;
	private final Context context;
	private BDhelper bdHelper;
	private SQLiteDatabase bancoDados;
	private Cursor cursor;
	
	
	class BDhelper extends SQLiteOpenHelper{

		public BDhelper(Context context) {
			super(context, nomeBanco, null, versaoBD);
			// TODO Auto-generated constructor stb
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
				//TABELA DE USU�RIOS (tabelaUsuarios)
				String sql = "CREATE TABLE IF NOT EXISTS "+nomeTabela1+" (_id INTEGER PRIMARY KEY, nome TEXT, dataNascimento TEXT, email TEXT, senha TEXT, sexo TEXT, eventoFavorito1 TEXT, eventoFavorito2 TEXT, eventoFavorito3 TEXT)";
				db.execSQL(sql);
				//TABELA DE EVENTOS (tabelaEventos)
				String sqlEvento = "CREATE TABLE IF NOT EXISTS "+nomeTabela2+" (_id INTEGER PRIMARY KEY, nome TEXT, endereco TEXT, numero TEXT, preco TEXT,data TEXT, hora TEXT, telefone TEXT, descricao TEXT, tipo TEXT, idOwner INTEGER, simboras INTEGER, idImagem INTEGER)";
				db.execSQL(sqlEvento);
				//TABELA DOS EVENTOS CRIADOS E QUE O USU�RIO DEU SIMBORA (tabelMeusEventos)
				String sqlMeusEventos = "CREATE TABLE IF NOT EXISTS "+nomeTabela3+" (_id INTEGER PRIMARY KEY, idUsuario INTEGER, idEvento INTEGER)";
				db.execSQL(sqlMeusEventos);
				
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
				String sql ="DROP TABLE IF EXISTS "+nomeTabela1;
				db.execSQL(sql);
				
				String sql2 = "DROP TABLE IF EXISTS "+ nomeTabela2;
				db.execSQL(sql2);
				
				String sql3 = "DROP TABLE IF EXISTS "+ nomeTabela3;
				db.execSQL(sql3);
			
				onCreate(db);
	
		}
		
		
	}
	public Banco openBd(){
		bdHelper= new BDhelper(context);
		bancoDados = bdHelper.getWritableDatabase();
		System.out.println(this+" "+"------------Open Bd--------");
		return this;
	}
	public void closeBd(){
		bancoDados.close();
	}
	 
	public void inserirUsuario(String nome, String dataDeNascimento, String email, String senha, String sexo, String eventoFavorito1, String eventoFavorito2, String eventoFavorito3){
		
		ContentValues valores = new ContentValues();
		
		valores.put("nome", nome);
		valores.put("dataNascimento", dataDeNascimento);
		valores.put("email", email);
		valores.put("senha", senha);
		valores.put("sexo", sexo);
		valores.put("eventoFavorito1", eventoFavorito1);
		valores.put("eventoFavorito2", eventoFavorito2);
		valores.put("eventoFavorito3", eventoFavorito3);
		
		bancoDados.insert(nomeTabela1, null, valores);	
	}
	public void darSimbora(int id) {
		try {
			openBd();
			
			String sqlSimboras = "SELECT simboras FROM tabelaEventos WHERE _id LIKE '"+id+"'";
			cursor = bancoDados.rawQuery(sqlSimboras,null);
			cursor.moveToFirst();
			
			int oldSimbora = cursor.getInt(cursor.getColumnIndex("simboras"));
			int newSimbora = oldSimbora + 1;
			
			String sql = "UPDATE tabelaEventos SET simboras = '"+newSimbora+"' WHERE _id LIKE '"+id+"'";
			bancoDados.execSQL(sql);
			
			String sqlMeusEventos="INSERT INTO tabelaMeusEventos (idUsuario, idEvento) VALUES ('"+Usuario.getId()+"','"+id+"')";
			bancoDados.execSQL(sqlMeusEventos);
			
		} catch (Exception erro) {
			// TODO: handle exception
			System.out.println(erro);
		}finally{
			closeBd();
		}
	}
		
	public Usuario getUsuario(int id){
		
		try {
			openBd();
			
			String sql = "SELECT * from tabelaUsuarios WHERE _id LIKE '"+id+"'";
			cursor = bancoDados.rawQuery(sql,null);
			cursor.moveToFirst();
			
			Usuario usuario = new Usuario();
			usuario.setSexo(cursor.getString(cursor.getColumnIndex("sexo")));
			usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
			usuario.setDataDeNascimento((cursor.getString(cursor.getColumnIndex("dataNascimento"))));
			usuario.setEmail((cursor.getString(cursor.getColumnIndex("email"))));
			usuario.setEventoFavorito1((cursor.getString(cursor.getColumnIndex("eventoFavorito1"))));
			usuario.setEventoFavorito2((cursor.getString(cursor.getColumnIndex("eventoFavorito2"))));
			usuario.setEventoFavorito3((cursor.getString(cursor.getColumnIndex("eventoFavorito3"))));
			usuario.setSenha((cursor.getString(cursor.getColumnIndex("senha"))));
			
			System.out.println(usuario.getEventoFavorito1());
			return usuario;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		finally{
			closeBd();
		}
		
		return null;
			
	}
	
	public void setMeusEventos(int idUsuario){
		try {
			openBd();
			String sql = "SELECT * FROM tabelaMeusEventos WHERE idUsuario LIKE '"+idUsuario+"' ";
			cursor = bancoDados.rawQuery(sql, null);
			
			cursor.moveToFirst();
			for(int i=0;i<cursor.getCount();i++){			
				Evento.addListaEventos(retornaEvento(cursor.getInt(cursor.getColumnIndex("idEvento"))));
				if(i!=cursor.getCount()-1){
					cursor.moveToNext();
				}		
			}
		} catch (Exception erro) {
			System.out.println(erro);
			
		}finally{
			closeBd();
		}	
	}
	
	public Evento retornaEvento(int idEvento){
		try {
			openBd();
			String sql = "SELECT * FROM tabelaEventos WHERE _id LIKE '"+idEvento+"' ";
			Cursor cursor2 = bancoDados.rawQuery(sql, null);
			cursor2.moveToFirst();
			
			Evento evento = new Evento();
			evento.setData(cursor2.getString(cursor2.getColumnIndex("data")));
			evento.setDescricao(cursor2.getString(cursor2.getColumnIndex("descricao")));
			evento.setEndereco(cursor2.getString(cursor2.getColumnIndex("endereco")));
			evento.setHora(cursor2.getString(cursor2.getColumnIndex("hora")));
			evento.setId(cursor2.getInt(cursor2.getColumnIndex("_id")));
			evento.setIdOwner(cursor2.getInt(cursor2.getColumnIndex("idOwner")));
			evento.setImage(cursor2.getInt(cursor2.getColumnIndex("idImagem")));
			evento.setNome(cursor2.getString(cursor2.getColumnIndex("nome")));
			evento.setNumero(cursor2.getString(cursor2.getColumnIndex("numero")));
			evento.setPreco(cursor2.getString(cursor2.getColumnIndex("preco")));
			evento.setSimboras(cursor2.getInt(cursor2.getColumnIndex("simboras")));
			evento.setTelefone(cursor2.getString(cursor2.getColumnIndex("telefone")));
			evento.setTipoDeEvento(cursor2.getString(cursor2.getColumnIndex("tipo")));
			
			return evento;
			
		} catch (Exception erro) {
			System.out.println(erro);
			
		}finally{
			closeBd();
		}	
			return null;
	}
	/*public void getTabelaUsuarios() {
		String [] columns = {"nome", "dataNascimento", "email", "senha", "sexo", "eventoFavorito1", "eventoFavorito2", "eventoFavorito3"};
		Cursor cursor = bancoDados.query(nomeTabela1, columns, null, null, null, null, null, null);
		
		ArrayList<String> result = new ArrayList<String>();
		
		for (cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
			
		}
	}*/
}