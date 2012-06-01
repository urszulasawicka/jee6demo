package com.example.jeedemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Resource;
import com.example.jeedemo.domain.Archive;

@Stateless
public class ArchiveManager {

	@PersistenceContext
	EntityManager em;

	public void addArchive(Archive archive) {
		archive.setId(null);
		em.persist(archive);
	}

	public void deleteArchive(Archive archive) {
		archive = em.find(Archive.class, archive.getId());
		em.remove(archive);
	}
	
	public void deleteArchive1(Long archiveId) {
		Archive archive = em.find(Archive.class, archiveId);
		em.remove(archive);
	}

	@SuppressWarnings("unchecked")
	public List<Archive> getAllArchives() {
		return em.createNamedQuery("archive.all").getResultList();
	}

	public List<Resource> getResourcesOfArchive(Archive archive) {
		archive = em.find(Archive.class, archive.getId());
		// lazy loading here - try this code without this (shallow) copying
		List<Resource> resources = new ArrayList<Resource>(archive.getResources());
		return resources;
	}
	
	public void updateArchive(Long archiveId, Archive a) {
		
		Archive nA = new Archive(archiveId, a.getName(),
				a.getAddress(), a.getCity(), a.getTel());		
		//archive = em.find(Archive.class, archive.getId(archive.setId(archiveId)));
		em.merge(nA);
	}

}
