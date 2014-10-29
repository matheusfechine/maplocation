package br.com.maplocation.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.maplocation.model.Tag;

@Service("tagService")
@Transactional
public class TagService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void cadastra(Tag tag) {
		entityManager.persist(tag);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Tag obtemPor(Integer id) {
		return entityManager.find(Tag.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Tag> lista() {
		String hql = "from Tag";
		Query query = entityManager.createQuery(hql);
		return (ArrayList<Tag>)query.getResultList();
	}
	
	public void atualiza(Tag tag) {
		entityManager.merge(tag);
	}

}
