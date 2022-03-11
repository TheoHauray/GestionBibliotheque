/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;
import modele.PublicVise;
import java.util.Date;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.util.Scanner;
import java.util.ArrayList;
import util.ES;
/**
 *
 * @author jaghdamm
 */
public class IHM {
    
public void afficher(String message){
    System.out.println(message);
}

public void afficherLecteur(String nom, String prenom, int numero, LocalDate dateDeNaissance, String adresse, String email){
    System.out.println("numero:"+numero+"\n Nom:"+nom+"\n  Prenom:"+prenom+" \n Date de Naissance"+dateDeNaissance+"\n  Adresse:"+adresse+"\n Email:"+email);
}

public void afficherExemplaire(int numero, Date dateDeReception, boolean empruntable){
    System.out.println("numero:"+numero+" \n Date de reception:"+ dateDeReception+ " \n empruntable ?"+empruntable);
}

public void afficheOuvrage(String nISBN, String titre, String nomAuteurs, String nomEditeur, Date dateParution){
    System.out.println("Numero ISBN:"+nISBN+"\n  Titre:"+titre+" \n Auteurs:"+nomAuteurs+" \n Editeur:"+nomEditeur+" \n Date de parution:"+dateParution);
} 

public boolean existe(ArrayList<String> numsISBN, String numISBN){
  
    for (String s: numsISBN){
       
        if (s.equals(numISBN)){
               return true;    
        }
    }
    return false;
}

//Utiliser sc.nextInt au lieu de sc.nextLine
public String saisirISBNnonExiste(ArrayList<String> numsISBN){
   String n = "";
   while(n !="0" && existe(numsISBN,n)){
        Scanner sc= new Scanner(System.in);
        System.out.println("Entrez le Numéro ISBN:");
        n = sc.nextLine();
    } 
   return n;
}

public String saisirISBNExiste(ArrayList<String> numsISBN){
   String n = "";
   while(n !="0" && !existe(numsISBN,n)){
        Scanner sc= new Scanner(System.in);
        System.out.println("Entrez le Numéro ISBN:");
        n = sc.nextLine();
    } 
   return n;
}

public InfosOuvrage saisirOuvrage()
{
    String titre, nomEditeur;
    LocalDate dateParution;
    ArrayList<String> auteurs = new ArrayList<String>();
    PublicVise publicVise = null;
    Scanner sc= new Scanner(System.in);
    int test = 1;
    int nPublic = 0;
    
    ES.afficherTitre("Saisir les informations de l'ouvrage");
    titre = ES.lireChaine("- Titre de l'ouvrage");
    dateParution = ES.lireDate("- Date de parution");

    while(dateParution.isAfter(now()))
    {
        dateParution = ES.lireDate("-- La date de parution doit être antérieur à la date du jour.");
    }
    nomEditeur = ES.lireChaine("- Nom de l'éditeur");
    
    while(test != 0)
    {
        auteurs.add(ES.lireChaine("- Entrer le nom d'un auteur"));
        afficher("Il y a-t-il un autre auteur ? - Si non, entrez 0");
        test = sc.nextInt();
    }
    
    test = 1;
    afficher("- Entrer 1 pour adulte, 2 pour ado, 3 pour enfant");
    
    while(test != 0)
    {
        nPublic = sc.nextInt(); 
        if(nPublic != 1 && nPublic != 2 && nPublic != 3)
        {
            afficher("-- Le numéro doit être 1, 2 ou 3");
        }
        else 
        {
            test = 0;
        }
    }
    
    switch (nPublic) 
    {
        case 1:
            publicVise = PublicVise.ADULTE;
            break;
        case 2:
            publicVise = PublicVise.ADO;
            break;
        case 3:
            publicVise = PublicVise.ENFANT;
            break;
    }
    
    InfosOuvrage infosOuvrage = new InfosOuvrage(titre, dateParution, nomEditeur, auteurs, publicVise);
    
    return infosOuvrage;
}

public InfosExemplaire saisirExemplaire(LocalDate dateParution)
{
    LocalDate dateReception;
    boolean empruntable;
    
    ES.afficherTitre("Saisir les informations de l'exemplaire");
    dateReception = ES.lireDate("- Date de réception");
    
    while(dateReception.isBefore(dateParution))
    {
        dateReception = ES.lireDate("-- La date de réception doit être postérieure à la date de parution.");
    }
    
    empruntable = ES.lireBoolean("- L'exemplaire est-il empruntable ?");
    InfosExemplaire infosExemplaire = new InfosExemplaire(dateReception, empruntable);
    
    return infosExemplaire;
}

public InfosLecteur saisirLecteur(int nLecteur)
{
    String nom, prenom, adresse, email;
    LocalDate dateDeNaissance;

    ES.afficherTitre("Saisir les informations du lecteur");
    nom = ES.lireChaine("- Nom : ");
    prenom = ES.lireChaine("- Prenom : ");
    adresse = ES.lireChaine("- Adresse : ");
    email = ES.lireEmail("- Email : ");
    
    dateDeNaissance = ES.lireDate("- Date de naissance : ");
    
    while(dateDeNaissance.isAfter(now()))
    {
        dateDeNaissance = ES.lireDate("-- La date de réception doit être antérieure à la date de parution.");
    }
    
    InfosLecteur infosLecteur = new InfosLecteur(nom, prenom, dateDeNaissance, adresse, email);
    
    return infosLecteur;
}

public static class InfosOuvrage {
    public final String titre;
    public final LocalDate dateParution;
    public final String nomEditeur;
    public final ArrayList<String> auteurs;
    public final PublicVise publicVise;
     
    public InfosOuvrage(final String titre, final LocalDate dateParution, final String nomEditeur, final ArrayList<String> auteurs, final PublicVise publicVise){
        this.titre = titre;
        this.dateParution = dateParution;
        this.nomEditeur = nomEditeur;
        this.auteurs = auteurs;
        this.publicVise = publicVise;
    }
}

public static class InfosExemplaire {
    public final LocalDate dateReception;
    public final Boolean empruntable;

     
    public InfosExemplaire(final LocalDate dateReception, final Boolean empruntable){
        this.dateReception = dateReception;
        this.empruntable = empruntable;
    }
}
 

public static class InfosLecteur {
    public final String nom;
    public final String prenom;
    public final LocalDate dateDeNaissance;
    public final String adresse;
    public final String email;
     
    public InfosLecteur(final String nom, final String prenom, final LocalDate dateDeNaissance, final String adresse, final String email){
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.email = email;
    }
}
}


