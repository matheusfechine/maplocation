package br.com.maplocation.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.maplocation.model.Location;

@Service
public class LocationService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void cadastra(Location location) {
		entityManager.persist(location);
	}

	@SuppressWarnings("unchecked")
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

	public Location obtem(Location location) {
		String hql = "from Location where name = :name";
		Query query = entityManager.createQuery(hql);
		query.setParameter("name", location.getName());
		return (Location)query.getSingleResult();
	}

}
