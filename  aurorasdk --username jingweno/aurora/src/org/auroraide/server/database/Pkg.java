package org.auroraide.server.database;

public class Pkg extends Table{
	
	public String name;
	public String project;
	
	public Pkg(String name,String project){
		this.name=name;
		this.project=project;
	}
	
	public Pkg(){
	}
	
}