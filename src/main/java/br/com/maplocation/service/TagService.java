package br.com.maplocation.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
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

	public Tag obtemPor(Integer id) {
		return entityManager.find(Tag.class, id);
	}

}
