/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author krebst
 */
public class Lecteur implements Serializable{
    private static final long serialVersionUID = 1L;
    private int nLecteur;
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private String adresse;
    private String email;
    private ArrayList<Emprunt> emprunts;
    
    public Lecteur(int nLecteur,String nom,String prenom,LocalDate dateDeNaissance,String adresse,String email)
    {
        this.nLecteur = nLecteur;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.email = email;
        this.emprunts = new ArrayList<Emprunt>();
    }
    
    public int getNumero(){
        return nLecteur;
    }
    
    public String getNom(){
        return nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public LocalDate getDateDeNaissance(){
        return dateDeNaissance;
    }
    
    public int getAge()
    {
        return Period.between(this.dateDeNaissance, LocalDate.now()).getYears();
    }
    
    public String getAdresse(){
        return adresse;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void lierEmpruntLecteur(Emprunt emprunt)
    {
        this.emprunts.add(emprunt);
    }
    
    public boolean sature()
    {
        if(this.emprunts != null)
        {
            if(this.emprunts.size() == 5)
            {
                return true;
            }    
        }
        return false;
    }
    
    public Emprunt getEmprunt(Ouvrage ouvrage, int nExemplaire)
    {
        Emprunt em = null;
        
        if(this.emprunts != null)
        {
            for(Emprunt emprunt : this.emprunts)
            {
                if(emprunt.getExemplaire().getOuvrage().getISBN().equals(ouvrage.getISBN()))
                {
                    em = emprunt;
                }
            }
        }
        return em;
    }
    
    public ArrayList<Emprunt> getEmprunts()
    {
        return this.emprunts;
    }
    
    public boolean lecteurConforme(PublicVise publicVise)
    {
        switch(publicVise)
        {
            case ADO :
                if(this.getAge() < 10)
                {
                    return false;
                }
                break;
            case ADULTE : 
                if(this.getAge() < 16)
                {
                    return false;
                }
                break;
        }
        return true;
    }
    
    public void ajouterEmpruntLecteur(Emprunt emprunt)
    {
        this.lierEmpruntLecteur(emprunt);
    }
    
    public void retirerEmpruntLecteur(Emprunt emprunt)
    {
        for(int i = 0; i < this.emprunts.size(); i++)
        {
            if(this.emprunts.get(i).compareTo(emprunt) == 0)
            {
                this.emprunts.remove(this.emprunts.get(i));
            }
        }
    }
}
