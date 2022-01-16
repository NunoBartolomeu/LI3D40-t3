package Classes;

public class Tel_empresa {

    //Atributos
    private int empresa;
    private String telefone;

    //Constutores
    public Tel_empresa (int empresa, String telefone) {
    this.empresa = empresa;
    this.telefone = telefone;
    }

    //Geters
    public int get_empresa () {return empresa;}
    public String get_telefone () {return telefone;}

    //Seters
    public void set_empresa (int empresa) {this.empresa = empresa;}
    public void set_telefone (String telefone) {this.telefone = telefone;}
    
}
