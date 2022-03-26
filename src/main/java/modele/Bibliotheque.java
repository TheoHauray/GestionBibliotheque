/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modele;

import java.io.Serializable;
import vue.IHM;
import vue.IHM.InfosOuvrage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import util.ES;

/**
 *
 * @author krebst
 */
public class Bibliotheque implements Serializable {
    
    private static final long serialVersionUID = 1L;
    //Attributs
    //-----------------------------------------------
    private int numDerLecteur;
    HashMap<String, Ouvrage> ouvrages;
    HashMap<Integer, Lecteur> lecteurs;
    ArrayList<Emprunt> emprunts;
    
    //Constructeur
    //-----------------------------------------------
    public Bibliotheque()
    {
        this.numDerLecteur = 0;
        this.ouvrages = new HashMap<String, Ouvrage>();
        this.lecteurs = new HashMap<Integer, Lecteur>();
        this.emprunts = new ArrayList<Emprunt>();
    }
    //Méthodes
    //-----------------------------------------------
    
    public ArrayList<String> getNumsISBN()
    {
        ArrayList<String> numsISBN = new ArrayList<String>();
        
        this.ouvrages.forEach(
            (key, value)
                -> numsISBN.add(key));

        return numsISBN;
    }
    
    public ArrayList<Integer> getNumsLecteurs()
    {
        ArrayList<Integer> numsLecteurs = new ArrayList<Integer>();
        
        this.lecteurs.forEach(
            (key, value)
                -> numsLecteurs.add(key));

        return numsLecteurs;
    }

    public void nouvelOuvrage(IHM ihm)
    {
        String nISBN = ihm.saisirISBNnonExiste(this.getNumsISBN());
        IHM.InfosOuvrage infos = ihm.saisirOuvrage();
        
        Ouvrage ouvrage = new Ouvrage(nISBN,infos.titre,infos.dateParution, infos.nomEditeur,infos.auteurs,infos.publicVise);
        this.lierOuvrage(ouvrage, nISBN);
        
        ihm.informerUtilisateur("Création de l'ouvrage", true);
    }
    
    public void nouvelExemplaire(IHM ihm)
    {
        String nISBN = ihm.saisirISBNExiste(this.getNumsISBN());  

        Ouvrage O = unOuvrage(nISBN);

        IHM.InfosExemplaire infosExemplaire = ihm.saisirExemplaire(O.getDateParution());
        O.ajouterExemplaire(infosExemplaire);

        ihm.informerUtilisateur("Création de l'exemplaire", true);
    }  
    
    public void nouveauLecteur(IHM ihm)
    {
        int nLecteur = this.incrementerNumLecteur();
        
        IHM.InfosLecteur infosLecteur = ihm.saisirLecteur(nLecteur);
        
        Lecteur l = new Lecteur(nLecteur,infosLecteur.nom,infosLecteur.prenom,infosLecteur.dateDeNaissance,infosLecteur.adresse,infosLecteur.email);
        
        ihm.informerUtilisateur("Création de lecteur", true);
        this.lierLecteur(l,nLecteur);
    }
    
    public void consulterLecteur(IHM ihm)
    {
        int nLecteur = ihm.saisirLecteurExiste(this.getNumsLecteurs());
        
        if(nLecteur != 0)
        {
            Lecteur l = lecteurs.get(nLecteur);
            ihm.afficherLecteur(l.getNom(), l.getPrenom(), nLecteur, l.getDateDeNaissance(), l.getAdresse(), l.getEmail()); 
        }
    }
    
    public void consulterOuvrage(IHM ihm)
    {
        String nISBN = ihm.saisirISBNExiste(this.getNumsISBN());
        
        if(nISBN != "0")
        {
            Ouvrage o = ouvrages.get(nISBN);
            ihm.afficheOuvrage(o.getISBN(), o.getTitre(), o.getAuteurs(), o.getNomEditeur(), o.getDateParution());     
        }
    }
    
    public void consulterExemplaireOuvrage(IHM ihm)
    {
        String nISBN = ihm.saisirISBNExiste(this.getNumsISBN());
        
        if(nISBN != "0")
        {
            Ouvrage o = ouvrages.get(nISBN);
            ihm.afficheOuvrage(o.getISBN(), o.getTitre(), o.getAuteurs(), o.getNomEditeur(), o.getDateParution());

            if(o.getExemplaires().size() == 0)
            {
                ihm.informerUtilisateur("Aucun exemplaire pour cet ouvrage", false);
            }
            else
            {
                for(Exemplaire e : o.getExemplaires())
                {
                    ihm.afficherExemplaire(e.getNumero(), e.getDateDeReception(), e.getEmpruntable());
                }
            }
        }
    }
    
    public void emprunterExemplaire(IHM ihm)
    {
        int nLecteur = ihm.saisirLecteurExiste(this.getNumsLecteurs());
        
        if(nLecteur != 0)
        {
            Lecteur l = lecteurs.get(nLecteur);
            
            if(l.sature() == false)
            {
                String nISBN = ihm.saisirISBNExiste(this.getNumsISBN());
        
                if(nISBN != "0")
                {
                    Ouvrage o = ouvrages.get(nISBN); 
                    
                    if(l.lecteurConforme(o.getPublicVise()) == true)
                    {
                        int nExemplaire = ihm.saisirExemplaireExiste(o.getNumsExemplaires());

                        if(nExemplaire != 0)
                        {
                            Exemplaire e = o.unExemplaire(nExemplaire);

                            if(e.getEmpruntable() == false)
                            {
                                ihm.informerUtilisateur("L'exemplaire n'est pas empruntable", false);
                            }
                            
                            else if(e.getEmpruntEnCours() == true)
                            {
                                ihm.informerUtilisateur("L'exemplaire est déjà emprunté", false);
                            }
                            
                            else
                            {
                                Emprunt em = new Emprunt(e,l);
                                this.lierEmprunt(em);
                                
                                ihm.informerUtilisateur("Emprunt créé", true);
                            }
                        }
                    }
                    else
                    {
                        ihm.informerUtilisateur("Le lecteur n'est pas conforme avec le public visé de l'ouvrage", false);
                    }
                }
            }
            else
            {
                ihm.informerUtilisateur("Le lecteur a déjà emprunté 5 exemplaires", false);
            }
        }
    }
    
    public void lierOuvrage(Ouvrage ouvrage, String nISBN)
    {
        this.ouvrages.put(nISBN, ouvrage);
    }
    
    public void lierLecteur(Lecteur lecteur, int nLecteur)
    {
        this.lecteurs.put(nLecteur, lecteur);
    }
    
    public void lierEmprunt(Emprunt emprunt)
    {
        this.emprunts.add(emprunt);
    }
    
    public Ouvrage unOuvrage(String nISBN) {

        Ouvrage o = ouvrages.get(nISBN);
        return o;
    } 
    
    public Lecteur unLecteur(int nLecteur)
    {
        Lecteur l = lecteurs.get(nLecteur);
        return l;
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