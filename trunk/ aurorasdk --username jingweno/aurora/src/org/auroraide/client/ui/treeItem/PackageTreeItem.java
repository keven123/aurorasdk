package org.auroraide.client.ui.treeItem;

import com.google.gwt.user.client.ui.TreeItem;

public class PackageTreeItem extends TreeItem {

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}


	private ClassTreeItem classTreeItem;
	private String packageName;
	
	public PackageTreeItem(String packageName){
		super("<img src=\"icon/package_obj.gif\"/> "+packageName);
		this.packageName=packageName;
	}
	
	public PackageTreeItem(String packageName, String className){
		super("<img src=\"icon/package_obj.gif\"/> "+packageName);
		classTreeItem=new ClassTreeItem(className);
		this.packageName=packageName;
		this.addItem(classTreeItem);
	}

}
