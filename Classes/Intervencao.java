package Classes;

import java.sql.Date;

public class Intervencao {
    //Atributos
    private int noint;
    private String descricao;
    private String estado;
    private Date dtinicio;
    private Date dtfim;
    private float valcusto;
    private String activo;
    private String artdisc;

    //Constutores
    public Intervencao (int noint, String descricao, String estado, Date dtinicio, Date dtfim, float valcusto, String activo, String artdisc) {
    this.noint = noint;
    this.descricao = descricao;
    this.estado = estado;
    if (dtinicio < activo.get_dtaquisicao()) kill;
    else this.dtinicio = dtinicio;
    this.dtfim = dtfim;
    this.valcusto = valcusto;
    this.activo = activo;
    this.artdisc = artdisc;
    }

    //Geters
    public int get_noint () {return noint;}
    public String get_descricao () {return descricao;}
    public String get_estado () {return estado;}
    public Date get_dtinicio () {return dtinicio;}
    public Date get_dtfim () {return dtfim;}
    public float get_valcusto () {return valcusto;}
    public String get_activo () {return activo;}
    public String get_artdisc () {return artdisc;}

    //Seters
    public void set_noint (int noint) {this.noint = noint;}
    public void set_descricao (String descricao) {this.descricao = descricao;}
    public void set_estado (String estado) {this.estado = estado;}
    public void set_dtinicio (Date dtinicio) {this.dtinicio = dtinicio;}
    public void set_dtfim (Date dtfim) {this.dtfim = dtfim;}
    public void set_valcusto (float valcusto) {this.valcusto = valcusto;}
    public void set_activo (String activo) {this.activo = activo;}
    public void set_artdisc (String artdisc) {this.artdisc = artdisc;}
}
