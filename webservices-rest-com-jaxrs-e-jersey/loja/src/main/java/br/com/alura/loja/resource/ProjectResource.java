package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.ProjectDAO;
import br.com.alura.loja.modelo.Project;

@Path("project")
public class ProjectResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String getFirstProject() {
		Project project = new ProjectDAO().busca(1l);
		
		return project.toXML();
	}
}
