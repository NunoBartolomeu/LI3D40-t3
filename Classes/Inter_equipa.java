package Classes;

public class Inter_equipa {
    //Atributos
    private int intervencao;
    private int equipa;

    //Constutores
    public Inter_equipa (int intervencao, int equipa) {
    this.intervencao = intervencao;
    this.equipa = equipa;
    }

    //Geters
    public int get_intervencao () {return intervencao;}
    public int get_equipa () {return equipa;}

    //Seters
    public void set_intervencao (int intervencao) {this.intervencao = intervencao;}
    public void set_equipa (int equipa) {this.equipa = equipa;}
}
