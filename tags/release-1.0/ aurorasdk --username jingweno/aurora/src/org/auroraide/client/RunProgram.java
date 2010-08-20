package org.auroraide.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface RunProgram extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static RunProgramAsync instance;
		public static RunProgramAsync getInstance(){
			if (instance == null) {
				instance = (RunProgramAsync) GWT.create(RunProgram.class);
				ServiceDefTarget target = (ServiceDefTarget) instance;
				target.setServiceEntryPoint(GWT.getModuleBaseURL() + "RunProgram");
			}
			return instance;
		}
	}
	
	String runClass(String type,ClassUnit classUnit);
}
