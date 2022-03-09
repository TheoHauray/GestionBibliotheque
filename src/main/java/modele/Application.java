/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modele;

import GestionBib.vue.IHM;
import GestionBib.vue.IHM.InfosOuvrage;
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
    HashMap<Ouvrage, String> ouvrages = new HashMap<Ouvrage, String>();
    HashMap<Lecteur, int> lecteurs = new HashMap<Lecteur, int>();
    
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

    public Ouvrage nouvelOuvrage(IHM ihm)
    {
        
        String nISBN = ihm.saisirISBNnonExiste(this.getNumsISBN());
        IHM.InfosOuvrage infos = new IHM.InfosOuvrage();
        infos = ihm.saisirOuvrage();
        
        Ouvrage ouvrage;
        ouvrage = new Ouvrage(nISBN,infos.titre,infos.dateParution, infos.nomEditeur,infos.auteurs,infos.publicVise);
        
        
        
        ihm.informerUtilisateur("Création de l'ouvrage");
        this.lierOuvrage(ouvrage, nISBN);
        
        return ouvrage;
    }
    
    public Ouvrage nouveauLecteur(IHM ihm)
    {
        nLecteur = this.incrementerNumLecteur();
        
        IHM.infosLecteur lecteur = new ihm.infosLecteur();
        lecteur = ihm.saisirLecteur(nLecteur);
        
        Lecteur lecteur;
        lecteur = new Lecteur(nLecteur,infosLecteur.nom,infosLecteur.prenom,infosLecteur.age,infosLecteur.adresse,infosLecteur.email);
        
        ihm.informerUtilisateur("Création de l'ouvrage");
        this.lierLecteur(lecteur,nLecteur);
        
        return lecteur;
    }
    
    public void lierOuvrage(Ouvrage ouvrage, String nISBN)
    {
        this.ouvrages.put(ouvrage, nISBN);
    }
    
    public void lierLecteur(Lecteur lecteur, int nLecteur)
    {
        this.lecteurs.put(lecteur, nLecteur);
    }
    
    public Ouvrage nouvelExemplaire(IHM ihm)
    {

        String nISBN = ihm.saisirISBNexiste(this.getNumsISBN());  

        Ouvrage O;
        O=unOuvrage(nISBN);

        IHM.InfosExemplaire infos= new ihm.InfosExemplaire();
        infos=ihm.saisirExemplaire(LocalDate dateParution);

        O.ajouterExemplaire(infos);
        O.incrementNumDerEx();

        Exemplaire e;
        e= new Exemplaire(nISBN, infos.dateParution, infos.empruntable);

        O.lierExemplaire;

        ihm.informerUtilisateur("Création de l'exemplaire");

        return Exemplaire;

         }  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
}