package Classes;

public class Tel_pessoa {
    
    //Atributos
    private int pessoa;
    private String telefone;

    //Constutores
    public Tel_pessoa (int pessoa, String telefone) {
    this.pessoa = pessoa;
    this.telefone = telefone;
    }

    //Geters
    public int get_pessoa () {return pessoa;}
    public String get_telefone () {return telefone;}

    //Seters
    public void set_pessoa (int pessoa) {this.pessoa = pessoa;}
    public void set_telefone (String telefone) {this.telefone = telefone;}

}
