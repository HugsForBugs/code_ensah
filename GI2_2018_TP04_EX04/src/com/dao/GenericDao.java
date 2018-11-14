package com.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.exception.DataBaseException;
import com.exception.ObjectNotFoundException;

/**
 * Interface des DAO g�n�riques
 * 
 * @author Tarik BOUDAA
 *
 * @param <T,
 *            PK>
 */
public interface GenericDao<T, PK> extends Dao {

	/**
	 * M�thode permettant la sauvegarde d'un objet de type T pass� en pram�tre � la
	 * m�thode
	 * 
	 * @param obj
	 *            l'objet � p�rsister
	 * @return
	 */
	public abstract Long add(T obj) throws DataBaseException;

	/**
	 * M�thode pour supprimer un objet dont l'id est pass� en param�tre
	 * 
	 * @param id
	 *            l'identifiant de l'objet � supprimer
	 * @return
	 */
	public abstract void delete(PK id) throws DataBaseException;

	/**
	 * M�thode de mise � jour d'un objet pass� en param�tre
	 * 
	 * @param obj
	 *            contient la nouvelle version de l'objet avec laquelle la mise �
	 *            jour sera effectu�e
	 * @return
	 */
	public abstract void update(T obj) throws DataBaseException;

	/**
	 * M�thode pour rechercher une entit� par son identifiant
	 * 
	 * @param id
	 *            identifiant de l'entit� recherch�e
	 * @return
	 */
	public abstract T find(PK id) throws DataBaseException, ObjectNotFoundException;


	/**
	 * M�thode pour r�cup�rer toutes les entit�s de type T
	 * 
	 * @return
	 */
	public abstract List<T> getAll() throws DataBaseException;

}
