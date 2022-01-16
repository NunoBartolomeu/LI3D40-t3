package Classes;

import java.sql.Date;

public class Pessoa {
    
    //Atributos
    private int id;
    private String email;
    private String nome;
    private Date dtnascimento;
    private String noident;
    private String morada;
    private int codpostal;
    private String localidade;
    private String profissao;
    private int equipa;
    private int empresa;

    //Constutores
    public Pessoa (int id, String email, String nome, Date dtnascimento, String noident, String morada, int codpostal, String localidade, String profissao, int equipa, int empresa) {
    this.id = id;
    this.email = email;
    this.nome = nome;
    this.dtnascimento = dtnascimento;
    this.noident = noident;
    this.morada = morada;
    this.codpostal = codpostal;
    this.localidade = localidade;
    this.profissao = profissao;
    this.equipa = equipa;
    this.empresa = empresa;
    }

    //Geters
    public int get_id () {return id;}
    public String get_email () {return email;}
    public String get_nome () {return nome;}
    public Date get_dtnascimento () {return dtnascimento;}
    public String get_noident () {return noident;}
    public String get_morada () {return morada;}
    public int get_codpostal () {return codpostal;}
    public String get_localidade () {return localidade;}
    public String get_profissao () {return profissao;}
    public int get_equipa () {return equipa;}
    public int get_empresa () {return empresa;}

    //Seters
    public void set_id (int id) {this.id = id;}
    public void set_email (String email) {this.email = email;}
    public void set_nome (String nome) {this.nome = nome;}
    public void set_dtnascimento (Date dtnascimento) {this.dtnascimento = dtnascimento;}
    public void set_noident (String noident) {this.noident = noident;}
    public void set_morada (String morada) {this.morada = morada;}
    public void set_codpostal (int codpostal) {this.codpostal = codpostal;}
    public void set_localidade (String localidade) {this.localidade = localidade;}
    public void set_profissao (String profissao) {this.profissao = profissao;}
    public void set_equipa (int equipa) {this.equipa = equipa;}
    public void set_empresa (int empresa) {this.empresa = empresa;}
}
