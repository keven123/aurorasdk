package org.auroraide.server.database;

import java.sql.Blob;

public class File extends Table{
	
	public String name;
	public String content;
	public Blob compiled;
	public String project;
	public String pkg;
	
	
	
	public File(String project, String pkg, String name, String content, Blob compiled){
		this.name=name;
		this.project=project;
		this.pkg=pkg;
		this.content=content;
		this.compiled=compiled;
	}
	
	public File(){
	}
	
}