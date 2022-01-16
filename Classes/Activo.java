package Classes;

import java.sql.Date;

public class Activo {
    //Atributos
    private String id;
    private String nome;
    private int estado;
    private Date dtaquisicao;
    private String modelo;
    private String marca;
    private String localizacao;
    private String idactivotopo;
    private int tipo;
    private int empresa;
    private int pessoa;    
    
    //Construtores
    public Activo(String id, String nome, int estado, Date dtaquisicao, String modelo, String marca, String localizacao, String idactivotopo, int tipo, int empresa, int pessoa){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.dtaquisicao = dtaquisicao;
        this.modelo = modelo;
        this.marca = marca;
        this.localizacao = localizacao;
        this.idactivotopo = idactivotopo;
        this.tipo = tipo;
        this.empresa = empresa;
        this.pessoa = pessoa;
    }

    //Geters
    public String get_id() {return id;}
    public String get_nome() {return nome;}
    public int get_estado() {return estado;}
    public Date get_dtaquisicao() {return dtaquisicao;}
    public String get_modelo() {return modelo;}
    public String get_marca() {return marca;}
    public String get_localizacao() {return localizacao;}
    public String get_idactivotop() {return idactivotopo;}
    public int get_tipo() {return tipo;}
    public int get_empresa() {return empresa;}
    public int get_pessoa() {return pessoa;}

    //Seters
    public void set_id(String id) {this.id = id;}
    public void set_nome(String nome) {this.nome = nome;}
    public void set_estado(int estado) {this.estado = estado;}
    public void set_modelo(String modelo) {this.modelo = modelo;}
    public void set_marca(String marca) {this.marca = marca;}
    public void set_localizacao(String localizacao) {this.localizacao = localizacao;}
    public void set_idactivotop(String idactivotopo) {this.idactivotopo = idactivotopo;}
    public void set_tipo(int tipo) {this.tipo = tipo;}
    public void get_empresa(int empresa) {this.empresa = empresa;}
    public void get_pessoa(int pessoa) {this.pessoa = pessoa;}

    public static void main(String[] args) {
        Date julio = new Date();
        Activo activo = new Activo("f1887", "Ferrari 1887", 0, julio, "87", "Ferrari", "Italy", "f0000", 5, 879, 2);
        System.out.println(activo.get_dtaquisicao()); 
        activo.set_id("42");
        System.out.println(activo.get_id());
    }
}