package org.auroraide.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileBuilderAsync {
	void getFiles(AsyncCallback callback);
	void createFile(ClassUnit classUnit, String type, AsyncCallback callback);
	void deleteFile(ClassUnit classUnit, String type, AsyncCallback callback);
	void modifyFile(ClassUnit classUnit, String type, AsyncCallback callback);
}
