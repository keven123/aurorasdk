package org.auroraide.client.ui.treeItem;

import com.google.gwt.user.client.ui.TreeItem;

public class ProjectTreeItem extends TreeItem {
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
	private PackageTreeItem packageTreeItem;
	private String projectName;
	
	public ProjectTreeItem(String projectName){
		super("<img src=\"icon/prj_obj.gif\"/> "+projectName);
		this.projectName=projectName;
	}
	
	public ProjectTreeItem(String projectName, String packageName){
		super("<img src=\"icon/icon/prj_obj.gif\"/> "+projectName);
		packageTreeItem=new PackageTreeItem(packageName);
		this.projectName=projectName;
		this.getChild(0).addItem(packageTreeItem);
	}
}
