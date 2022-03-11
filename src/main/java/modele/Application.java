/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modele;

import vue.IHM;
import vue.IHM.InfosOuvrage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author krebst
 */
public class Application {
    
    //Attributs
    //-----------------------------------------------
    private int numDerLecteur;
    HashMap<String, Ouvrage> ouvrages = new HashMap<String, Ouvrage>();
    HashMap<Integer, Lecteur> lecteurs = new HashMap<Integer, Lecteur>();
    
    //Méthodes
    //-----------------------------------------------
    
    public ArrayList<String> getNumsISBN()
    {
        ArrayList<String> numsISBN = new ArrayList<String>();
        Set keys = this.ouvrages.keySet();
        Iterator<Ouvrage> iterator = keys.iterator();
        
        while(iterator.hasNext()){
            numsISBN.add(iterator.next().getISBN());
        }
        
        return numsISBN;
    }

    public void nouvelOuvrage(IHM ihm)
    {
        
        String nISBN = ihm.saisirISBNnonExiste(this.getNumsISBN());
        IHM.InfosOuvrage infos;
        infos = ihm.saisirOuvrage();
        
        Ouvrage ouvrage;
        ouvrage = new Ouvrage(nISBN,infos.titre,infos.dateParution, infos.nomEditeur,infos.auteurs,infos.publicVise);
        
        
        
        ihm.afficher("Création de l'ouvrage");
        this.lierOuvrage(ouvrage, nISBN);
        
    }
    
    public void nouveauLecteur(IHM ihm)
    {
        int nLecteur = this.incrementerNumLecteur();
        
        IHM.InfosLecteur infosLecteur;
        infosLecteur = ihm.saisirLecteur(nLecteur);
        
        Lecteur lecteur;
        lecteur = new Lecteur(nLecteur,infosLecteur.nom,infosLecteur.prenom,infosLecteur.dateDeNaissance,infosLecteur.adresse,infosLecteur.email);
        
        ihm.afficher("Création de l'ouvrage");
        this.lierLecteur(lecteur,nLecteur);
        
    }
    
    public void lierOuvrage(Ouvrage ouvrage, String nISBN)
    {
        this.ouvrages.put(nISBN, ouvrage);
    }
    
    public void lierLecteur(Lecteur lecteur, int nLecteur)
    {
        this.lecteurs.put(nLecteur, lecteur);
    }
    
    
    public void nouvelExemplaire(IHM ihm)
    {

        String nISBN = ihm.saisirISBNExiste(this.getNumsISBN());  

        Ouvrage O;
        O = unOuvrage(nISBN);

        IHM.InfosExemplaire infosExemplaire ;
        infosExemplaire = ihm.saisirExemplaire(O.getDateParution());

        O.ajouterExemplaire(infosExemplaire);
        O.incrementNumDerEx();

        ihm.afficher("Création de l'exemplaire");
    }  
    
    public Ouvrage unOuvrage(String nISBN) {

        Ouvrage O = ouvrages.get(nISBN);
        return O;
    } 
    
    public int incrementerNumLecteur()
    {
        this.numDerLecteur++;
        
        return this.numDerLecteur;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
}