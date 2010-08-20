package org.auroraide.client.ui.treeItem;


import com.google.gwt.user.client.ui.TreeItem;

public class ClassTreeItem extends TreeItem {
	
	
	private String className;
	
	public ClassTreeItem(String className){
		super("<img src=\"icon/jcu_obj.gif\"/> "+className+".java");
	}
	


	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
