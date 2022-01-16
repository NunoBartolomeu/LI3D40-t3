package Classes;

public class Empresa {
    
    //Atributos
    private int id;
    private String url;
    private int nipc;
    private String nome;
    private String morada;
    private int codpostal;
    private String localidade;

    //Constutores
    public Empresa (int id, String url, int nipc, String nome, String morada, int codpostal, String localidade) {
    this.id = id;
    this.url = url;
    this.nipc = nipc;
    this.nome = nome;
    this.morada = morada;
    this.codpostal = codpostal;
    this.localidade = localidade;
    }

    //Geters
    public int get_id () {return id;}
    public String get_url () {return url;}
    public int get_nipc () {return nipc;}
    public String get_nome () {return nome;}
    public String get_morada () {return morada;}
    public int get_codpostal () {return codpostal;}
    public String get_localidade () {return localidade;}

    //Seters
    public void set_id (int id) {this.id = id;}
    public void set_url (String url) {this.url = url;}
    public void set_nipc (int nipc) {this.nipc = nipc;}
    public void set_nome (String nome) {this.nome = nome;}
    public void set_morada (String morada) {this.morada = morada;}
    public void set_codpostal (int codpostal) {this.codpostal = codpostal;}
    public void set_localidade (String localidade) {this.localidade = localidade;}
}
