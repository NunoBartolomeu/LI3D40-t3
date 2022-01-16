package Classes;

public class Competencia {
        
    //Atributos
    private int codigo;
    private String descricao;

    //Constutores
    public Competencia (int codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
    }

    //Geters
    public int get_codigo () {return codigo;}
    public String get_descricao () {return descricao;}

    //Seters
    public void set_codigo (int codigo) {this.codigo = codigo;}
    public void set_descricao (String descricao) {this.descricao = descricao;}
}

