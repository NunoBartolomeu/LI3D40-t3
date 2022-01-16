package Classes;

public class Comp_pessoa{
    //Atributos
    private int pessoa;
    private int competencia;

    //Constutores
    public Comp_pessoa (int pessoa, int competencia) {
    this.pessoa = pessoa;
    this.competencia = competencia;
    }

    //Geters
    public int get_pessoa () {return pessoa;}
    public int get_competencia () {return competencia;}

    //Seters
    public void set_pessoa (int pessoa) {this.pessoa = pessoa;}
    public void set_competencia (int competencia) {this.competencia = competencia;}
}