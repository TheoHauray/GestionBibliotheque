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
        infos ihm.saisirOuvrage();
        
        Ouvrage ouvrage;
        ouvrage = new Ouvrage(nISBN,infos.titre,infos.dateParution, infos.nomEditeur,infos.auteurs,infos.publicVise);
        
        this.ouvrages.put(ouvrage, nISBN);
        
        ihm.informerUtilisateur("Création de l'ouvrage");
        
        return ouvrage;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
}