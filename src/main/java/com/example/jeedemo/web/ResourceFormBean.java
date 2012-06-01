package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Resource;
import com.example.jeedemo.domain.Archive;
import com.example.jeedemo.service.ResourceManager;
import com.example.jeedemo.service.AssigningManager;

@SessionScoped
@Named("resourceBean")
public class ResourceFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Resource resource = new Resource();
	private ListDataModel<Resource> resources = new ListDataModel<Resource>();
	
	@Inject
	private ResourceManager rm;
	
	private Long resourceId;
	
	
	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public ListDataModel<Resource> getResources() {
		return resources;
	}

	public void setResources(ListDataModel<Resource> resources) {
		this.resources = resources;
	}

	public ListDataModel<Resource> getAllResources() {
		resources.setWrappedData(rm.getAllResources());
		return resources;
	}
	
	// Actions
	public String addResource() {
		rm.addResource(resource);
		return "showResources?faces-redirect=true";
		//return null;
	}

	public String deleteResource() {
		Resource resourceDelete = resources.getRowData();
		rm.deleteResource(resourceDelete);
		return null;
	}
	
	public String deleteResource1() {
		rm.deleteResource1(resourceId);
		return "showResources?faces-redirect=true";
	}
	
	public String updateResource(){
		Resource resourceToUpdate = resources.getRowData();
		rm.updateResource(resourceToUpdate);
		return "showResources?faces-redirect=true";
	}
}
