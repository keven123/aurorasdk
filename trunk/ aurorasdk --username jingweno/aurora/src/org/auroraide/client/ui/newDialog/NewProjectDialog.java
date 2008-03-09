package org.auroraide.client.ui.newDialog;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public class NewProjectDialog extends DialogBox {

	private Label classNameLabel;
	private Label packageNameLabel;
	private Label sourceNameLabel;
	private Label projectNameLabel;

	public void hide() {
		super.hide();
		className.setText("");
		packageName.setText("");
		projectName.setText("");
		projectName.setReadOnly(false);
		packageName.setReadOnly(false);
		className.setReadOnly(false);
		packageName.setVisible(true);
		className.setVisible(true);
		classNameLabel.setVisible(true);
		packageNameLabel.setVisible(true);
		projectNameLabel.setVisible(true);
	}

	private Button cancelButton;
	private Button finishButton;
	private TextBox className;
	private TextBox packageName;
	private TextBox sourceName;
	private TextBox projectName;
	private String type;

	public NewProjectDialog() {
		setHTML("New dialog");

		final SimplePanel simplePanel = new SimplePanel();
		setWidget(simplePanel);
		simplePanel.setSize("100%", "100%");

		final FlexTable flexTable = new FlexTable();
		simplePanel.setWidget(flexTable);
		flexTable.setSize("100%", "100%");

		projectName = new TextBox();
		flexTable.setWidget(0, 1, projectName);
		projectName.setVisibleLength(50);

		sourceName = new TextBox();
		flexTable.setWidget(1, 1, sourceName);
		sourceName.setReadOnly(true);
		sourceName.setText("src");
		sourceName.setVisibleLength(50);

		packageName = new TextBox();
		flexTable.setWidget(2, 1, packageName);
		packageName.setVisibleLength(50);

		className = new TextBox();
		flexTable.setWidget(3, 1, className);
		className.setVisibleLength(50);

		projectNameLabel = new Label("Project name:");
		flexTable.setWidget(0, 0, projectNameLabel);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);

		sourceNameLabel = new Label("Source name:");
		flexTable.setWidget(1, 0, sourceNameLabel);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);

		packageNameLabel = new Label("Package name:");
		flexTable.setWidget(2, 0, packageNameLabel);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);

		classNameLabel = new Label("Class name:");
		flexTable.setWidget(3, 0, classNameLabel);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);

		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		flexTable.setWidget(4, 1, horizontalPanel);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 1,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 1,
				HasHorizontalAlignment.ALIGN_RIGHT);

		finishButton = new Button();
		horizontalPanel.add(finishButton);
		finishButton.setWidth("50");
		finishButton.setText("Finish");

		cancelButton = new Button();
		horizontalPanel.add(cancelButton);
		cancelButton.setTabIndex(1);
		cancelButton.setWidth("50");
		cancelButton.setText("Cancel");

	}

	public void setType(String type) {
		this.type = type;
		if (type.equalsIgnoreCase("project")) {
			setHTML("New Project - Create a new project");
			packageName.setVisible(false);
			packageNameLabel.setVisible(false);
			classNameLabel.setVisible(false);
			className.setVisible(false);

		} else if (type.equalsIgnoreCase("package")) {
			setHTML("New Java Package - Create a new Java package");
			projectName.setReadOnly(true);
			classNameLabel.setVisible(false);
			className.setVisible(false);
		} else if (type.equalsIgnoreCase("class")) {
			setHTML("New Java Class - Create a new Java class");
			projectName.setReadOnly(true);
			packageName.setReadOnly(true);
		}
	}

	public String getType() {
		return type;
	}

	public TextBox getClassName() {
		return className;
	}

	public TextBox getPackageName() {
		return packageName;
	}

	public TextBox getSourceName() {
		return sourceName;
	}

	public TextBox getProjectName() {
		return projectName;
	}

	public Button getFinishButton() {
		return finishButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

}
