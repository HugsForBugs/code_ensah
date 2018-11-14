package com.ensah;

/**
 * Permet d'isoler PFE des impl�mentations des livrables
 * 
 * Pour assurer une maintenabilit� facile du code
 * 
 * (Ajout de d'autres types de livrables n'impactera pas le code de la classe
 * PFE)
 * 
 * En plus, elle permet d'imposer � tous les livrables d'etre imprimables c�d
 * donnent une impl�mentation de la m�thode print
 * 
 * @author BOUDAA
 *
 */
public interface Livrable {

	public void print();

}
