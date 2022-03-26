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
public class Exemplaire implements Comparable<Exemplaire>, Serializable {
    private static final long serialVersionUID = 1L;
    private int nExemplaire;
    private LocalDate dateDeReception;
    private boolean empruntable;
    private Emprunt emprunt;
    private Ouvrage ouvrage;
    
    public Exemplaire (Ouvrage ouvrage, int nExemplaire, LocalDate dateDeReception,boolean empruntable) {
        this.ouvrage = ouvrage;
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
    
    public Ouvrage getOuvrage()
    {
        return this.ouvrage;
    }
    
    public void lierEmpruntExemplaire(Emprunt emprunt)
    {
        this.emprunt = emprunt;
    }
    
    public void ajouterEmpruntExemplaire(Emprunt emprunt)
    {
        this.lierEmpruntExemplaire(emprunt);
    }
    
    public void retirerEmpruntExemplaire(Emprunt emprunt)
    {
        this.emprunt = null;
    }

    @Override
    public int compareTo(Exemplaire o) {
        if(this.ouvrage.getISBN() == o.getOuvrage().getISBN() && this.nExemplaire == o.nExemplaire)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}