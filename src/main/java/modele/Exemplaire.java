/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
import java.io.Serializable;
import java.time.LocalDate;


/**
 *
 * @author krebst
 */
public class Exemplaire implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numero;
    private LocalDate dateDeReception;
    private boolean empruntable;
    
    Exemplaire (int numero, LocalDate dateDeReception,boolean empruntable ) {
        this.numero = numero;
        this.dateDeReception = dateDeReception;
        this.empruntable = empruntable;
    }

    public int getnumero(){
    return numero;
    }
    
    public LocalDate getdateDeReception(){
        return dateDeReception;
    }
    
    public boolean getempruntable(){
        return empruntable;
    }
    
}