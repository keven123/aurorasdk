/**
 * 
 */
package org.auroraide.server.compiler;

import java.util.List;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

import org.auroraide.server.compiler.util.ClassEntity;
import org.auroraide.server.compiler.util.ProblemEntity;

/**
 * Interface for compiler should be in sequence
 * loadClass->(setOptions)->compile->(getProblems,getDiagostics)->runClass
 * 
 * @author Owen
 * 
 */
public interface ICompiler {

	void loadClass(ClassEntity classEntity);

	boolean compile();

	String runClass();

	void setOptions(String... options);

	Iterable<String> getOptions();

	List<ProblemEntity> getProblems();

	DiagnosticCollector<JavaFileObject> gerDiagnostics();

}
