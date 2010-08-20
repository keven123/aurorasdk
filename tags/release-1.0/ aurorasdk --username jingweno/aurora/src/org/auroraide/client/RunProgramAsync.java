package org.auroraide.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RunProgramAsync {
	void runClass(String type,ClassUnit classUnit, AsyncCallback callback);
}
