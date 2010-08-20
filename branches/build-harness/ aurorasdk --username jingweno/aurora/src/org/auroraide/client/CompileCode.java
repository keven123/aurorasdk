package org.auroraide.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface CompileCode extends RemoteService {

	public static class Util {
		private static CompileCodeAsync instance;

		public static CompileCodeAsync getInstance() {
			if (instance == null) {
				instance = (CompileCodeAsync) GWT.create(CompileCode.class);
				ServiceDefTarget target = (ServiceDefTarget) instance;
				target.setServiceEntryPoint(GWT.getModuleBaseURL()
						+ "CompileCode");
			}
			return instance;
		}
	}

	ProblemUnit[] getCompilingResult(String type, 
								ClassUnit classUnit);
}
