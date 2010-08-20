package org.auroraide.server.compiler.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.auroraide.server.compiler.ICompiler;
import org.auroraide.server.compiler.util.ClassEntity;
import org.auroraide.server.compiler.util.Executor;
import org.auroraide.server.compiler.util.JavaSourceFromString;
import org.auroraide.server.compiler.util.ProblemEntity;



public class CompilerImpl implements ICompiler {

	private Iterable<String> options;
	private DiagnosticCollector<JavaFileObject> diagnostics;
	private ClassEntity classEntity;
		
	@Override
	public Iterable<String> getOptions() {
		
		return options;
	}

	@Override
	public void setOptions(String... options) {
		this.options = Arrays.asList(options);
	}

	@Override
	public List<ProblemEntity> getProblems() {		
		List<ProblemEntity> problemEntities=new ArrayList<ProblemEntity>();
		for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()){
			String kind=diagnostic.getKind().toString();
			String description=diagnostic.getMessage(null).substring(
					diagnostic.getMessage(null).lastIndexOf(
							diagnostic.getLineNumber()+":")+3);
			String source=classEntity.getClassName()+".java";
			String path=classEntity.getProjectName()+"/"+
				classEntity.getPackageName().replace('.', '/')+"/"+
				classEntity.getClassName();
			String line=String.valueOf(diagnostic.getLineNumber());
			
			ProblemEntity problemEntity=new ProblemEntity(kind,
					description,source,path,line);
			
			problemEntities.add(problemEntity);
		}
		
		return problemEntities;
	}	
	
	@Override
	public DiagnosticCollector<JavaFileObject> gerDiagnostics() {
		return diagnostics;
		
	}

	@Override
	public String runClass() {
		String result="";
		try {
			result=Executor.doCommand("java "+classEntity.getPackageName()+"."+classEntity.getClassName(),
					"\\Documents and Settings\\owen\\workspace\\"+classEntity.getProjectName()+"/bin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean compile() {
		if(classEntity==null||classEntity.getClassName().isEmpty()
				||classEntity.getPackageName().isEmpty()||classEntity.getContent().isEmpty())
			throw new RuntimeException();
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		diagnostics = new DiagnosticCollector<JavaFileObject>();
		JavaFileObject file = new JavaSourceFromString(classEntity.getClassName(),
				classEntity.getContent());
		Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
		CompilationTask task = compiler.getTask(null, null, diagnostics,
				options, null, compilationUnits);		
		return task.call();
	}

	@Override
	public void loadClass(ClassEntity classEntity) {
		this.classEntity=classEntity;		
	}
	
	public static void main(String[] arg){
		CompilerImpl compiler=new CompilerImpl();
		
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		out.println("package com.compiler;");
		out.println("public class HelloWorld {");
		out.println("  public static void main(String args[]) {");
		out.println("    System.out.println(\"Hello World!\");");
		out.println("  }");
		out.println("}");
		out.close();
		
		ClassEntity classEntity=new ClassEntity("AuroraIDE","com.compiler","HelloWorld",writer.toString());
		compiler.setOptions("-d", "bin", "-sourcepath",
				"src");
		compiler.loadClass(classEntity);
		boolean success=compiler.compile();
		if(!success){
			Iterator<?> i=compiler.getProblems().iterator();
			while(i.hasNext()){
				ProblemEntity pe=(ProblemEntity)i.next();
				System.out.println(pe.getKind());
				System.out.println(pe.getDescription());
				System.out.println(pe.getResource());
				System.out.println(pe.getPath());
				System.out.println(pe.getLocation());
			}
			
		}
		else{
			System.out.println(compiler.runClass());
		}
	}

	
}
