package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Resource;
import com.example.jeedemo.domain.Archive;

@Stateless
public class ResourceManager {

	@PersistenceContext
	EntityManager em;

	public void addResource(Resource resource) {
		resource.setId(null);
		em.persist(resource);
	}

	public void deleteResource(Resource resource) {
		resource = em.find(Resource.class, resource.getId());
		em.remove(resource);
	}
	
	public void deleteResource1(Long resourceId) {
		Resource resource1 = em.find(Resource.class, resourceId);
		em.remove(resource1);
	}

	@SuppressWarnings("unchecked")
	public List<Archive> getAllResources() {
		return em.createNamedQuery("resource.assigned").getResultList();
	}
	
	public void updateResource(Resource resource) {	
		resource = em.find(Resource.class, resource.getId());
		em.merge(resource);
	}

}
