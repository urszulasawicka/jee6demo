package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Resource;
import com.example.jeedemo.domain.Archive;


/* 
 * This is a Stateless EJB Bean
 * All its methods are transactional
 */
@Stateless
public class AssigningManager {

	@PersistenceContext
	EntityManager em;

	public void assignResource(Long archiveId, Long resourceId) {

		Archive archive = em.find(Archive.class, archiveId);
		Resource resource = em.find(Resource.class, resourceId);
		resource.setAssigned(true);

		archive.getResources().add(resource);
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getObtainableResources() {
		return em.createNamedQuery("resource.assigned").getResultList();
	}

	public void disposeResource(Archive archive, Resource resource) {

		archive = em.find(Archive.class, archive.getId());
		resource = em.find(Resource.class, resource.getId());

		Resource toDelete = null;
		// lazy loading here (person.getCars)
		for (Resource r : archive.getResources())
			if (r.getId().compareTo(resource.getId()) == 0) {
				toDelete = r;
				break;
			}

		if (toDelete != null)
			archive.getResources().remove(toDelete);
		
		resource.setAssigned(false);
	}
}
