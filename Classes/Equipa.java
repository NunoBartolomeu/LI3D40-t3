package Classes;

public class Equipa {
    //Atributos
    private int codigo;
    private String localizacao;
    private int responsavel;

    //Constutores
    public Equipa (int codigo, String localizacao, int responsavel) {
    this.codigo = codigo;
    this.localizacao = localizacao;
    this.responsavel = responsavel;
    }

    //Geters
    public int get_codigo () {return codigo;}
    public String get_localizacao () {return localizacao;}
    public int get_responsavel () {return responsavel;}

    //Seters
    public void set_codigo (int codigo) {this.codigo = codigo;}
    public void set_localizacao (String localizacao) {this.localizacao = localizacao;}
    public void set_responsavel (int responsavel) {this.responsavel = responsavel;}
}
