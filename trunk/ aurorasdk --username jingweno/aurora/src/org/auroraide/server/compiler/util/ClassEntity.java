package org.auroraide.server.compiler.util;

public class ClassEntity {
	
	private String projectName;
	private String className;
	private String packageName;
	private String content;
	private byte[] bytecode;
	
	public ClassEntity(){
		
	}
	
	public ClassEntity(String projectName, String packageName, String className, String content){
		setProjectName(projectName);
		setPackageName(packageName);
		setClassName(className);
		setContent(content);
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public byte[] getBytecode() {
		return bytecode;
	}

	public void setBytecode(byte[] bytecode) {
		this.bytecode = bytecode;
	}
	

}
