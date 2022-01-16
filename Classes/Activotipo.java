package Classes;

public class Activotipo {
    //Atributos
    private int id;
    private String descricao;
    
    //Constutores
    public Activotipo (int id, String descricao) {
    this.id = id;
    this.descricao = descricao;
    }
    
    //Geters
    public int get_id () {return id;}
    public String get_descricao () {return descricao;}
    
    //Seters
    public void set_id (int id) {this.id = id;}
    public void set_descricao (String descricao) {this.descricao = descricao;}
}