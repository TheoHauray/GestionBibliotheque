/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;
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
    
    Lecteur(int numero,String nom,String prenom,LocalDate dateDeNaissance,String adresse,String email)
    {
        this.nLecteur = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.email = email;
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
      public String getAdresse(){
        return adresse;
    }
      public String getEmail(){
        return email;
    }
    
    
}
