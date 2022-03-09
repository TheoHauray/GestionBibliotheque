/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionBib.modele;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author haurayt
 */
public class Ouvrage implements Serializable{
    private static final long serialVersionUID = 1L;
    //Attributs
    //-----------------------------------------------
    private String nISBN;
    private String titre;
    private LocalDate dateParution;
    private String nomEditeur;
    private ArrayList<String> auteurs;
    private PublicVise publicVise;
    private int numDerEx;
    private ArrayList<Exemplaire> exemplaires;
    
    //Constructeur
    //-----------------------------------------------
    Ouvrage(String nISBN,String titre,LocalDate dateParution,String nomEditeur,ArrayList<String> auteurs,PublicVise publicVise)
    {
        this.nISBN = nISBN;
        this.titre = titre;
        this.dateParution = dateParution;
        this.nomEditeur = nomEditeur;
        this.auteurs = auteurs;
        this.publicVise = publicVise;
        this.numDerEx = 0;
    }
    
    public String getISBN() {
        return nISBN;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public LocalDate getDateParution() {
        return dateParution;
    }
    
    public String getNomEditeur() {
        return nomEditeur;
    }
    
    public ArrayList<String> getAuteurs(){
        return this.auteurs;
    }
    
    public int getNumDerEx() {
        return numDerEx;
    }
    
    public ArrayList<Exemplaire> getExemplaires(){
        return this.exemplaires;
    }
    
    public int incrementNumDerEx() {
        numDerEx = numDerEx + 1;
        return getNumDerEx();
    }
    
    public void lierExemplaire(Exemplaire ex) {
        this.exemplaires.add(ex);
    }
}
