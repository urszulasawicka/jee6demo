package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Resource;
import com.example.jeedemo.domain.Archive;
import com.example.jeedemo.service.ArchiveManager;
import com.example.jeedemo.service.AssigningManager;

@SessionScoped
@Named("assignBean")
public class AssigningFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AssigningManager asm;

	@Inject
	private ArchiveManager arm;

	private Long resourceId;
	private Long archiveId;
	
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public Long getArchiveId() {
		return archiveId;
	}
	public void setArchiveId(Long archiveId) {
		this.archiveId = archiveId;
	}

	public List<Resource> getObtainableResources() {
		return asm.getObtainableResources();
	}

	public List<Archive> getAllArchives() {
		return arm.getAllArchives();
	}

	public String assignResource() {
		asm.assignResource(archiveId, resourceId);
		return null;
	}
}
