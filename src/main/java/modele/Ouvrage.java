/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;
import vue.IHM.InfosExemplaire;

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
    private ArrayList<Exemplaire> exemplaires;
    private int numDerEx;

    //Constructeur
    //-----------------------------------------------
    public Ouvrage(String nISBN,String titre,LocalDate dateParution,String nomEditeur,ArrayList<String> auteurs,PublicVise publicVise)
    {
        this.nISBN = nISBN;
        this.titre = titre;
        this.dateParution = dateParution;
        this.nomEditeur = nomEditeur;
        this.auteurs = auteurs;
        this.publicVise = publicVise;
        this.numDerEx = 0;
        this.exemplaires = new ArrayList<Exemplaire>();
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
    
    public PublicVise getPublicVise()
    {
        return this.publicVise;
    }
    
    public ArrayList<Integer> getNumsExemplaires()
    {
        ArrayList<Integer> numsExemplaires = new ArrayList<Integer>();
        
        for(Exemplaire exemplaire : this.exemplaires)
        {
            numsExemplaires.add(exemplaire.getNumero());
        }
        
        return numsExemplaires;
    }
    
    public void incrementNumDerEx() {
        numDerEx = numDerEx + 1;
    }
    
    public void lierExemplaire(Exemplaire ex) {
        this.exemplaires.add(ex);
    }
    
    public void ajouterExemplaire(InfosExemplaire infosExemplaire)
    {
        Exemplaire e;
        this.incrementNumDerEx();
        e= new Exemplaire(this.getNumDerEx(), infosExemplaire.dateReception, infosExemplaire.empruntable);
        
        this.lierExemplaire(e);
    }
    
    public Exemplaire unExemplaire(int nExemplaire)
    {
        Exemplaire e = null;
        
        for(Exemplaire exemplaire : this.exemplaires)
        {
            if(exemplaire.getNumero() == nExemplaire)
            {
                e = exemplaire;
            }
        }
        
        return e;
    }
}
