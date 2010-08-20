package org.auroraide.server.jci;

import java.util.List;

import org.auroraide.server.compiler.util.ClassEntity;
import org.auroraide.server.compiler.util.ProblemEntity;

public interface IJCICompiler {
	
	void loadClass(ClassEntity classEntity);

	boolean compile();

	String runClass() throws Exception;

	void setOptions(String[] options);

	//Iterable<String> getOptions();
	
	List<ProblemEntity> getProblems();
	
	//List<ProblemEntity> getErrors();
	
	//List<WarningEntity> getWarnings();

	//DiagnosticCollector<JavaFileObject> gerDiagnostics();
}
