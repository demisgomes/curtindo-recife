package dominio;

import android.database.Cursor;

public class Estabelecimento {
	
	public Estabelecimento(){	
	}
	
	public Estabelecimento(Cursor cursor){
		setId(cursor.getInt(cursor.getColumnIndex("id")));
		setIdOwner(cursor.getInt(cursor.getColumnIndex("idOwner")));
		setImage(cursor.getInt(cursor.getColumnIndex("image")));
		setNome(cursor.getString(cursor.getColumnIndex("nome")));
		setData(cursor.getString(cursor.getColumnIndex("data")));
		setHoraInicio(cursor.getString(cursor.getColumnIndex("horaInicio")));
		setHoraTermino(cursor.getString(cursor.getColumnIndex("horaTermino")));
		setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
		setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
		setCnpj(cursor.getString(cursor.getColumnIndex("cnpj")));
		setSimboras(cursor.getInt(cursor.getColumnIndex("simboras")));
		setPreco(cursor.getString(cursor.getColumnIndex("preco")));
		setNumero(cursor.getString(cursor.getColumnIndex("numero")));
		setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
		setPrioridade(cursor.getInt(cursor.getColumnIndex("prioridade")));
		setRanking(cursor.getInt(cursor.getColumnIndex("ranking")));
	}
	
	public Estabelecimento(int idOwner,String nome, String endereco, String numero, String preco, String data, String horaInicio,String horaTermino, String telefone, String descricao, String tipo, int imagem, int prioridade){
		setIdOwner(idOwner);
		setNome(nome);
		setData(data);
		setHoraInicio(horaInicio);
		setHoraTermino(horaTermino);
		setDescricao(descricao);
		setTipo(tipo);
		setPreco(preco);
		setNumero(numero);
		setEndereco(endereco);
		setPrioridade(prioridade);
		setRanking(0);
		setSimboras(0);
	}
	
	private int ranking;
	private int id;
	private int image;
	private String nome;
	private String data;
	/////Hor�rio de funcionamento///
	private String horaInicio;
	private String horaTermino;
	//---------------------------///
	private String descricao;
	private String tipo;
	private int idOwner;
	private String cnpj;
	private int simboras;
	private String preco;
	private String numero;
	private String endereco;
	private int prioridade;
	private static String atual;
	
	
	
	
	
	public static String getAtual() {
		return atual;
	}

	public static void setAtual(String atual) {
		Estabelecimento.atual = atual;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraTermino() {
		return horaTermino;
	}
	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getIdOwner() {
		return idOwner;
	}
	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public int getSimboras() {
		return simboras;
	}
	public void setSimboras(int simboras) {
		this.simboras = simboras;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	
	
}