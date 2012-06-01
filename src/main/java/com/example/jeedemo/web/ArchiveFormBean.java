package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Resource;
import com.example.jeedemo.domain.Archive;
import com.example.jeedemo.service.ArchiveManager;
import com.example.jeedemo.service.AssigningManager;

@SessionScoped
@Named("archiveBean")
public class ArchiveFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Archive archive = new Archive();
	private ListDataModel<Archive> archives = new ListDataModel<Archive>();
	
	private Archive archiveShow = new Archive();
	private ListDataModel<Resource> resourcesOfArchive = new ListDataModel<Resource>();

	private Long archiveId;

	@Inject
	private ArchiveManager arm;
	
	@Inject
	private AssigningManager asm;

	
	
	public Long getArchiveId() {
		return archiveId;
	}
	public void setArchiveId(Long archiveId) {
		this.archiveId = archiveId;
	}
	public Archive getArchive() {
		return archive;
	}
	public void setArchive(Archive archive) {
		this.archive = archive;
	}
	
	public ListDataModel<Archive> getAllArchives() {
		archives.setWrappedData(arm.getAllArchives());
		return archives;
	}

	public ListDataModel<Resource> getObtainableResources() {
		resourcesOfArchive.setWrappedData(arm.getResourcesOfArchive(archiveShow));
		return resourcesOfArchive;
	}
	
	// Actions
	public String addArchive() {
		arm.addArchive(archive);
		return "showArchives?faces-redirect=true";
		//return null;
	}

	public String deleteArchive() {
		Archive archiveDelete = archives.getRowData();
		arm.deleteArchive(archiveDelete);
		return null;
	}
	
	public String deleteArchive1() {
		arm.deleteArchive1(archiveId);
		return "showArchives?faces-redirect=true";
	}
	
	public String showDetailsArchive() {
		archiveShow = archives.getRowData();
		return "details?faces-redirect=true";
	}
	
	public String disposeResource(){
		Resource resourceDispose = resourcesOfArchive.getRowData();
		asm.disposeResource(archiveShow, resourceDispose);
		return null;
	}
	
	public String updateArchive(){
		Archive archiveToUpdate = archives.getRowData();
		arm.updateArchive(archiveId, archiveToUpdate);
		return "showArchives?faces-redirect=true";
	}
	
	public void validateTel(ComponentSystemEvent event){
		 
		FacesContext fc = FacesContext.getCurrentInstance();
		UIComponent components = event.getComponent();
 
		UIInput uiTel = (UIInput)components.findComponent("tel");
		String tel1 = uiTel.getLocalValue().toString();
		String areaCode = tel1.substring(0,2);
		int id = tel1.indexOf("-");
		
		if(!areaCode.equals("(0")){
			FacesMessage msg = 
					  new FacesMessage("Podałeś błędny numer kierunkowy!");
		    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(components.getClientId(), msg);
			fc.renderResponse();
		}
		if(id!=9){
			FacesMessage msg = 
					  new FacesMessage("Podałeś separator w błędnym miejscu!");
		    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(components.getClientId(), msg);
			fc.renderResponse();
		}
	}
}
