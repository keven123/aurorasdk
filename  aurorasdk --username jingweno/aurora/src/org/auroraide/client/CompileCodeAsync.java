package org.auroraide.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CompileCodeAsync {

	void getCompilingResult(String type, 
								ClassUnit classUnit,
								AsyncCallback callback);
}
