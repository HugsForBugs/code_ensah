package com.ihm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bll.CompteManagerImpl;
import com.bo.Compte;
import com.dao.api.JDBCConnection;
import com.bo.Client;
import com.exception.CompteOperationException;
import com.exception.DataBaseException;
import com.exception.ObjectNotFoundException;
import com.utils.DateUtils;

/**
 * La partie HIM de l'application
 * 
 * @author boudaa
 *
 */
public class ConsoleApp {

	public static int mainMenu() {

		System.out.println("--------APPLICATION GESTION COMPTE----------");

		System.out.println("1- Cr�er un compte  ");
		System.out.println("2- D�biter un compte ");
		System.out.println("3- Cr�diter un compte ");
		System.out.println("4- Virement ");
		System.out.println("5- Afficher un compte");
		System.out.println("6- Afficher tous les comptes");
		System.out.println("7- Quitter");
		System.out.println("Choisir une option ");

		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();

		return s;

	}

	public static void main(String[] args) {

		try {
			JDBCConnection.createUniqueConnexion();
		} catch (DataBaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		CompteManagerImpl compteManager = new CompteManagerImpl();

		do {
			Scanner sc = new Scanner(System.in);

			int choice = mainMenu();

			switch (choice) {
			case 1:
				Client p = new Client();
				System.out.println("Entrer le nom de la personne");
				p.setNom(sc.nextLine());
				System.out.println("Entrer le pr�nom de la personne");
				p.setPrenom(sc.nextLine());

				System.out.println("Entrer CIN ");
				p.setCni(sc.nextLine());
				
				System.out.println("Entrer Adresse ");
				p.setAdresse(sc.nextLine());

				System.out.println("Date naissance au format jj/mm/aaaa");

				boolean ok = false;

				do {
					try {
						p.setDateNaissance(DateUtils.convertStringToDate("dd/MM/yyyy", sc.nextLine()));
						ok = true;
					} catch (ParseException e1) {

						System.out.println("La date naissance au format jj/mm/aaaa");
					}
				} while (!ok);

				Compte newCompte;
				try {
					newCompte = compteManager.createCompte(p);
					System.out.println("Compte cr�e avec succ�s");
					System.out.println(newCompte);
				} catch (DataBaseException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}

				break;

			case 2:
				Compte compte = null;
				try {
					System.out.println("Entrer le num�ro de compte");
					compte = compteManager.getCompteByNum(sc.nextLong());
					System.out.println("Entrer le montant de d�bit");

					compteManager.debiter(compte, sc.nextDouble());

				} catch (ObjectNotFoundException ex) {

					System.err.println("le compte indiqu� n'existe pas");
				}

				catch (CompteOperationException e) {

					System.err.println(e.getMessage());

				} catch (DataBaseException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}

				break;

			case 3:
				System.out.println("Entrer le num�ro de compte");
				Compte compte2 = null;
				try {
					compte2 = compteManager.getCompteByNum(sc.nextLong());
					System.out.println(compte2);
					System.out.println("Entrer le montant de cr�dit");
					compteManager.crediter(compte2, sc.nextDouble());

				} catch (ObjectNotFoundException ex) {

					System.err.println("le compte indiqu� n'existe pas");

				} catch (DataBaseException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}
				break;

			case 4:
				try {
					System.out.println("Entrer le num�ro de compte � d�biter ");
					Compte c1 = compteManager.getCompteByNum(sc.nextLong());

					System.out.println("Entrer le num�ro de compte � cr�diter ");
					Compte c2 = compteManager.getCompteByNum(sc.nextLong());

					System.out.println("Entrer le montant de virement");
					compteManager.virement(c1, c2, sc.nextDouble());
				} catch (ObjectNotFoundException ex) {

					System.err.println("le compte indiqu� n'existe pas");
				}

				catch (CompteOperationException e) {

					System.err.println(e.getMessage());

				} catch (DataBaseException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}
				break;

			case 5:

				System.out.println("Entrer le num�ro de compte � afficher ");
				try {
					System.out.println(compteManager.getCompteByNum(sc.nextLong()).toString());
				} catch (ObjectNotFoundException ex) {

					System.err.println("le compte indiqu� n'existe pas");
				}

				catch (DataBaseException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}

				break;

			case 6:

				try {
					List<Compte> listComptes = compteManager.showBanque();

					for (Compte it : listComptes) {

						System.out.println(it);
					}

				} catch (DataBaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case 7:

				System.exit(0);

			default:
				System.out.println("veuillez choisir une option de 1 � 7");
				break;
			}

		} while (true);
	}

}
