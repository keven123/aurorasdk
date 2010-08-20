package org.auroraide.server.fileBuilder;

import java.util.ArrayList;
import java.util.List;

import org.auroraide.client.ClassUnit;
import org.auroraide.client.FileBuilder;
import org.auroraide.server.database.File;
import org.auroraide.server.database.Pkg;
import org.auroraide.server.database.Project;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class FileBuilderImpl extends RemoteServiceServlet implements FileBuilder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;


	@SuppressWarnings("unchecked")
	public ClassUnit[] getFiles() {
		List<?> files=new ArrayList();
		List<?> pro=new ArrayList();
		List<?> pkg=new ArrayList();
		
		File f=new File();
		Project p=new Project();
		Pkg pk=new Pkg();
		
		ClassUnit[] classUnits=null;
		
		int count=0;
		
		try {
			files=f.loadAll();
			pro=p.loadAll();
			pkg=pk.loadAll();
			classUnits=new ClassUnit[files.size()+pro.size()+pkg.size()];
			for(int i=0;i<files.size();i++){
				File file=(File)files.get(i);
				ClassUnit classUnit=new ClassUnit();
				classUnit.className=file.name;
				classUnit.packageName=file.pkg;
				classUnit.projectName=file.project;
				classUnit.classContent=file.content;
				classUnits[count]=classUnit;
				count++;
			}
			
			
			
			for(int i=0;i<pkg.size();i++){
				Pkg package1=(Pkg)pkg.get(i);
				ClassUnit classUnit=new ClassUnit();
				classUnit.projectName=package1.project;
				classUnit.packageName=package1.name;
				classUnits[count]=classUnit;
				count++;
			}
			
			for(int i=0;i<pro.size();i++){
				Project project=(Project)pro.get(i);
				ClassUnit classUnit=new ClassUnit();
				classUnit.projectName=project.name;
				classUnits[count]=classUnit;
				count++;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		/*ClassUnit classUnit=new ClassUnit();
		classUnit.className="HelloWorld";
		classUnit.packageName="com.test";
		classUnit.projectName="AuroraIDE";
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		out.println("package com.test;");
		out.println("public class HelloWorld {");
		out.println("  public static void main(String args[]) {");
		out.println("    System.out.println(\"Hello World!\");");
		out.println("  }");
		out.println("}");
		out.close();
		classUnit.classContent=writer.toString();
		
		package com.test;
		\npublic class HelloWorld {
		\npublic static void main(String args[]) {
		\nSystem.out.println(\"Hello World!\");
		\n}
		\n}
		
		ClassUnit classUnit1=new ClassUnit();
		classUnit1.className="HelloWorld1";
		classUnit1.packageName="com.test1";
		classUnit1.projectName="AuroraIDE1";
		StringWriter writer1 = new StringWriter();
		PrintWriter out1 = new PrintWriter(writer1);
		out1.println("package com.test;");
		out1.println("public class HelloWorld {");
		out1.println("  public static void main(String args[]) {");
		out1.println("    System.out.println(\"Hello World!\");");
		out1.println("  }");
		out1.println("}");
		out1.close();
		classUnit1.classContent=writer1.toString();
		
		classUnits[0]=classUnit;
		classUnits[1]=classUnit1;*/
		
		return classUnits;
	}

	
	public static void main(String[] args){
		FileBuilderImpl fi=new FileBuilderImpl();
		ClassUnit classUnit=new ClassUnit();
		classUnit.projectName="a";
		classUnit.packageName="com.s";
		classUnit.className="Owen";
		fi.deleteFile(classUnit, "project");
	}

	@Override
	public boolean createFile(ClassUnit classUnit, String type) {
		boolean success=false;
		try {
		if(type.equalsIgnoreCase("project")){
			String project=classUnit.projectName;
			Project prj=new Project();
			prj.name=project;
			success=prj.save();
			return success;	
		}
		else if(type.equalsIgnoreCase("package")){
			String package1=classUnit.packageName;
			String project1=classUnit.projectName;
			Pkg pkg=new Pkg();
			pkg.name=package1;
			pkg.project=project1;
			success=pkg.save();
			return success;
		}
		else if (type.equalsIgnoreCase("class")){
			File file=new File(classUnit.projectName,classUnit.packageName,classUnit.className,classUnit.classContent,null);
			success=file.save();
			return success;
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean deleteFile(ClassUnit classUnit, String type) {
		//boolean success=false;
		try {
		if(type.equalsIgnoreCase("project")){
			String project=classUnit.projectName;
			Project prj=new Project();
			prj.name=project;
			//List temp=prj.loadAll(new String[] {"name"}, new Object[]{project});	
			int i=prj.deleteAll(new String[] {"name"},new Object[] {prj.name});
			if(i>0)
				return true;
			return false;
		}
		else if(type.equalsIgnoreCase("package")){
			String package1=classUnit.packageName;
			String project1=classUnit.projectName;
			Pkg pkg=new Pkg();
			pkg.name=package1;
			pkg.project=project1;
			//List temp=pkg.loadAll(new String[] {"name","project"}, new Object[]{package1,project1});
			int i=pkg.deleteAll(new String[] {"name","project"},new Object[] {pkg.name,pkg.project});
			if(i>0)
				return true;
			return false;
		}
		else if (type.equalsIgnoreCase("class")){
			File file=new File(classUnit.projectName,classUnit.packageName,classUnit.className,classUnit.classContent,null);
			int i=file.deleteAll(new String[] {"name","pkg","project"},new Object[]{file.name,file.pkg,file.project});
			if(i>0)
				return true;
			return false;
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean modifyFile(ClassUnit classUnit, String type) {
		if(type.equalsIgnoreCase("class")){
			//ClassUnit classU=classUnit;
			
			try {
				File file=new File();
				List<?> result=file.loadAll(new String[] {"name","project","pkg"}, new Object[]{classUnit.className,classUnit.projectName,classUnit.packageName});
				if(result!=null&&result.size()>0){
					file=(File)result.get(0);
					file.content=classUnit.classContent;
					boolean success=file.save();
					return success;
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return false;
		
	}
}
