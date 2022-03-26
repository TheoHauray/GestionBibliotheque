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
    private int nExemplaire;
    private LocalDate dateDeReception;
    private boolean empruntable;
    private Emprunt emprunt;
    
    public Exemplaire (int nExemplaire, LocalDate dateDeReception,boolean empruntable ) {
        this.nExemplaire = nExemplaire;
        this.dateDeReception = dateDeReception;
        this.empruntable = empruntable;
        this.emprunt = null;
    }

    public int getNumero(){
    return nExemplaire;
    }
    
    public LocalDate getDateDeReception(){
        return dateDeReception;
    }
    
    public boolean getEmpruntable(){
        return empruntable;
    }
    
    public boolean getEmpruntEnCours()
    {
        if(this.emprunt == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void lierEmpruntExemplaire(Emprunt emprunt)
    {
        this.emprunt = emprunt;
    }
    
    public void ajouterEmpruntExemplaire(Emprunt emprunt)
    {
        this.lierEmpruntExemplaire(emprunt);
    }
}