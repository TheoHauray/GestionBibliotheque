/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author hauraytheo
 */
public class Emprunt implements Comparable<Emprunt>{
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Lecteur lecteur;
    private Exemplaire exemplaire;
    
    public Emprunt(LocalDate dateDebut, Exemplaire exemplaire, Lecteur lecteur)
    {
        this.dateDebut = dateDebut;
        this.dateFin = this.dateDebut.plusDays(15);
        this.lecteur = lecteur;
        this.exemplaire = exemplaire;
        
        exemplaire.ajouterEmpruntExemplaire(this);
        lecteur.ajouterEmpruntLecteur(this);
    }
    
    public Exemplaire getExemplaire()
    {
        return this.exemplaire;
    }
    
    public Lecteur getLecteur()
    {
        return this.lecteur;
    }
    
    public LocalDate getDateDebut()
    {
        return this.dateDebut;
    }
    
    public LocalDate getDateFin()
    {
        return this.dateFin;
    }
    
    public void supprimerEmprunt()
    {
        this.exemplaire.retirerEmpruntExemplaire(this);
        this.lecteur.retirerEmpruntLecteur(this);
    }
    
    public boolean enRetard() {
        return this.dateFin.isBefore(LocalDate.now());
    }

    @Override
    public int compareTo(Emprunt o) {
        if(this.exemplaire.compareTo(o.exemplaire) == 0 && this.lecteur.getNumero() == o.lecteur.getNumero())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
