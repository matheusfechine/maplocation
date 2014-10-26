package br.com.maplocation.service;

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

	public List<Location> lista() {
		return null;
	}

	public void atualiza(Integer id) {
	}

	public void exclui(Integer id) {
	}

	public Location obtem(Location location) {
		String hql = "from Location where name = :name";
		Query query = entityManager.createQuery(hql);
		query.setParameter("name", location.getName());
		return (Location)query.getSingleResult();
	}

}
