package org.auroraide.server.compiler;

import java.sql.Blob;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.auroraide.client.ClassUnit;
import org.auroraide.client.RunProgram;
import org.auroraide.server.compiler.util.ClassEntity;
import org.auroraide.server.database.File;
import org.auroraide.server.jci.IJCICompiler;
import org.auroraide.server.jci.compilers.impl.JCICompilerImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RunProgramImpl extends RemoteServiceServlet implements RunProgram {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String runClass(String type,ClassUnit classUnit) {
		String[] args=new String[]{classUnit.packageName.replace(".", "/")+"/"+classUnit.className+".java"};
		IJCICompiler c;
		try {
			c = new JCICompilerImpl(type,args);
			ClassEntity classEntity=new ClassEntity(classUnit.projectName,classUnit.packageName,classUnit.className,classUnit.classContent);
			c.loadClass(classEntity);
			if(c.compile()){
				
				File file=new File();
				List<?> files=file.loadAll(new String[]{"name","project","pkg"}, new Object[]{classEntity.getClassName(),classEntity.getProjectName(),classEntity.getPackageName()});
				file=(File)files.get(0);
				Blob blob=new SerialBlob(classEntity.getBytecode());
				file.compiled=blob;
				file.save();
				
				return c.runClass();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Compile Error!";
	}

}
