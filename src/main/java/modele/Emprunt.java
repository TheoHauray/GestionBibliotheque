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
public class Emprunt implements Comparable<Emprunt>{
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
    
    public Exemplaire getExemplaire()
    {
        return this.exemplaire;
    }
    
    public Lecteur getLecteur()
    {
        return this.lecteur;
    }
    
    public void supprimerEmprunt()
    {
        this.exemplaire.retirerEmpruntExemplaire(this);
        this.lecteur.retirerEmpruntLecteur(this);
    }

    @Override
    public int compareTo(Emprunt o) {
        if(this.exemplaire == o.exemplaire && this.lecteur.getNumero() == o.lecteur.getNumero())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
