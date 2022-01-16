package Classes;

import java.sql.Date;

public class Vcomercial {
    
    //Atributos
    private Date dtvcomercial;
    private String activo;
    private float valor;

    //Constutores
    public Vcomercial (Date dtvcomercial, String activo, float valor) {
    this.dtvcomercial = dtvcomercial;
    this.activo = activo;
    this.valor = valor;
    }

    //Geters
    public Date get_dtvcomercial () {return dtvcomercial;}
    public String get_activo () {return activo;}
    public float get_valor () {return valor;}

    //Seters
    public void set_dtvcomercial (Date dtvcomercial) {this.dtvcomercial = dtvcomercial;}
    public void set_activo (String activo) {this.activo = activo;}
    public void set_valor (float valor) {this.valor = valor;}

}
