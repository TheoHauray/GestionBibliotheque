/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.time.LocalDate;

/**
 *
 * @author hauraytheo
 */
public class Emprunt {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Lecteur lecteur;
    private Exemplaire exemplaire;
    
    public Emprunt(Exemplaire exemplaire, Lecteur lecteur)
    {
        this.dateDebut = LocalDate.now();
        this.dateFin = LocalDate.now().plusDays(15);
        this.lecteur = lecteur;
        this.exemplaire = exemplaire;
        
        exemplaire.ajouterEmpruntExemplaire(this);
        lecteur.ajouterEmpruntLecteur(this);
    }
    
    public Emprunt creerEmprunt(Lecteur lecteur, Exemplaire exemplaire)
    {
        Emprunt em = new Emprunt(exemplaire, lecteur);
        
        exemplaire.lierEmpruntExemplaire(em);
        
        return em;
    }
}
