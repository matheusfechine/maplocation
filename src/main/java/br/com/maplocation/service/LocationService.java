package br.com.maplocation.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.maplocation.model.Location;
@Transactional
@Repository("locationService")
public class LocationService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private TagService tagService;
	
	public void cadastra(Location location) {
		entityManager.persist(location);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Location> lista() {
		String hql = "from Location";
		Query query = entityManager.createQuery(hql);
		return (ArrayList<Location>)query.getResultList();
	}

	public void atualiza(Location location) {
		entityManager.merge(location);
	}

	public void exclui(Integer id) {
		Location location = entityManager.find(Location.class, id);
		entityManager.remove(location);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Location obtemPor(Integer id){
		String hql = "from Location as location where location.id = :id";
		Query query = entityManager.createQuery(hql);
		query.setParameter("id", id);
		return (Location)query.getSingleResult();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Location obtem(Location location) {
		String hql = "select location from Location as location join location.tags where location.name = :name";
		Query query = entityManager.createQuery(hql);
		query.setParameter("name", location.getName());
		return (Location)query.getSingleResult();
	}

}
