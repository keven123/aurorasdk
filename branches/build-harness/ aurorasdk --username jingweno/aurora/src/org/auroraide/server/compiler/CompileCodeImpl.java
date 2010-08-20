package org.auroraide.server.compiler;

import java.sql.Blob;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.auroraide.client.ClassUnit;
import org.auroraide.client.CompileCode;
import org.auroraide.client.ProblemUnit;
import org.auroraide.server.compiler.util.ClassEntity;
import org.auroraide.server.database.File;
import org.auroraide.server.jci.IJCICompiler;
import org.auroraide.server.jci.compilers.impl.JCICompilerImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CompileCodeImpl extends RemoteServiceServlet 
							 implements CompileCode {

	private static final long serialVersionUID = 1L;
	
	public ProblemUnit[] getCompilingResult(String type,
										ClassUnit classUnit) {
		
		String[] args=new String[]{classUnit.packageName.replace(".", "/")+"/"+classUnit.className+".java"};
		IJCICompiler c;
		try {
			c = new JCICompilerImpl(type,args);
		
		//ICompiler c = new CompilerImpl();
		ClassEntity classEntity=new ClassEntity(classUnit.projectName,classUnit.packageName,classUnit.className,classUnit.classContent);
		//c.setOptions("-d", "bin", "-sourcepath", "src");
		c.loadClass(classEntity);
		if(!c.compile()){
			ProblemUnit[] problems=new ProblemUnit[c.getProblems().size()];
			for(int i=0;i<c.getProblems().size();i++){
				ProblemUnit problem=new ProblemUnit();
				problem.kind=c.getProblems().get(i).getKind();
				problem.description=c.getProblems().get(i).getDescription();
				problem.location=c.getProblems().get(i).getLocation();
				problem.path=c.getProblems().get(i).getPath();
				problem.resource=c.getProblems().get(i).getResource();
				problems[i]=problem;
			}
			
			return problems;
		}
		else{
			File file=new File();
			List<?> files=file.loadAll(new String[]{"name","project","pkg"}, new Object[]{classEntity.getClassName(),classEntity.getProjectName(),classEntity.getPackageName()});
			file=(File)files.get(0);
			Blob blob=new SerialBlob(classEntity.getBytecode());
			file.compiled=blob;
			file.save();
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
