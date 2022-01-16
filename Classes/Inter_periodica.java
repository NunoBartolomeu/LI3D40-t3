package Classes;

public class Inter_periodica {
    
    //Atributos
    private int integerevencao;
    private int periodicidade;

    //Constutores
    public Inter_periodica (int integerevencao, int periodicidade) {
    this.integerevencao = integerevencao;
    this.periodicidade = periodicidade;
    }

    //Geters
    public int get_integerevencao () {return integerevencao;}
    public int get_periodicidade () {return periodicidade;}

    //Seters
    public void set_integerevencao (int integerevencao) {this.integerevencao = integerevencao;}
    public void set_periodicidade (int periodicidade) {this.periodicidade = periodicidade;}

}
