package org.auroraide.server.compiler.util;

public class ProblemEntity {

	private String description;
	private String resource;
	private String path;
	private String location;
	private String kind;
	
	public ProblemEntity(String kind,String description,String resource,
			String path,String location){
		setKind(kind);
		setDescription(description);
		setResource(resource);
		setPath(path);
		setLocation(location);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
}
