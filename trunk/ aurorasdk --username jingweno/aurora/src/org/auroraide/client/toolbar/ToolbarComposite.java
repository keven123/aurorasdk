package org.auroraide.client.toolbar;

import org.auroraide.client.ClassUnit;
import org.auroraide.client.FileBuilder;
import org.auroraide.client.ui.codePressEditor.CodePressEditorWidget;
import org.auroraide.client.ui.editorTabPanel.EditorTabPanel;
import org.auroraide.client.ui.newDialog.NewProjectDialog;
import org.auroraide.client.ui.treeItem.ClassTreeItem;
import org.auroraide.client.ui.treeItem.PackageTreeItem;
import org.auroraide.client.ui.treeItem.ProjectTreeItem;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarComposite extends Composite {

	private PushButton save_pushButton;
	private PushButton down_pushButton;
	private PushButton compile_pushButton;
	private PushButton run_pushButton;

	public ToolbarComposite(final Tree packageTree,
			final NewProjectDialog newProject,
			final EditorTabPanel editorPanel,
			final CodePressEditorWidget codePressEditor) {
		final HorizontalPanel toolBarlPanel = new HorizontalPanel();

		toolBarlPanel.setStyleName("gwt-RichTextToolbar");
		toolBarlPanel.setHeight("3%");
		toolBarlPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		toolBarlPanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		final PushButton newPrj_pushButton = new PushButton(new Image(
				"icon/new_wiz1.gif"), new Image("icon/new_wiz2.gif"));
		toolBarlPanel.add(newPrj_pushButton);
		newPrj_pushButton.setTitle("New Project");
		newPrj_pushButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				newProject.setType("project");
				newProject.show();

			}

		});

		final PushButton newJP_pushButton = new PushButton(new Image(
				"icon/newjprj_wiz1.gif"), new Image("icon/newjprj_wiz.gif"));
		toolBarlPanel.add(newJP_pushButton);
		newJP_pushButton.setTitle("New Java Project");
		newJP_pushButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				newProject.setType("project");
				newProject.center();
				newProject.show();
			}

		});

		final PushButton newP_pushButton = new PushButton(new Image(
				"icon/newpack_wiz1.gif"), new Image("icon/newpack_wiz.gif"));
		toolBarlPanel.add(newP_pushButton);
		newP_pushButton.setTitle("New Java Package");
		newP_pushButton.addClickListener(new ClickListener() {
			public void onClick(final Widget sender) {
				if (packageTree.getSelectedItem() instanceof ProjectTreeItem) {
					newProject.setType("package");
					newProject.center();
					newProject.show();
					String projectName = packageTree.getSelectedItem()
							.getText().trim();
					newProject.getProjectName().setText(projectName);
				}
			}
		});

		final PushButton newClass_pushButton = new PushButton(new Image(
				"icon/newclass_wiz1.gif"), new Image("icon/newclass_wiz.gif"));
		toolBarlPanel.add(newClass_pushButton);
		newClass_pushButton.setTitle("New Java Class");
		newClass_pushButton.addClickListener(new ClickListener() {
			public void onClick(final Widget sender) {
				if (packageTree.getSelectedItem() instanceof PackageTreeItem) {
					newProject.setType("class");
					newProject.center();
					newProject.show();
					String packageName = packageTree.getSelectedItem()
							.getText().trim();
					String projectName = packageTree.getSelectedItem()
							.getParentItem().getText().trim();
					newProject.getProjectName().setText(projectName);
					newProject.getPackageName().setText(packageName);
				}
			}
		});

		save_pushButton = new PushButton(new Image("icon/save_edit1.gif"),
				new Image("icon/save_edit.gif"));
		toolBarlPanel.add(save_pushButton);
		save_pushButton.setTitle("Save Selected File");
		save_pushButton.addClickListener(new ClickListener() {
			public void onClick(final Widget sender) {
				if (packageTree.getSelectedItem() instanceof ClassTreeItem) {
					((ClassUnit) packageTree.getSelectedItem().getUserObject()).classContent = codePressEditor
							.getCode();

					ClassUnit classUnit = (ClassUnit) packageTree
							.getSelectedItem().getUserObject();
					FileBuilder.Util.getInstance().modifyFile(classUnit,
							"class", new AsyncCallback() {

								public void onFailure(Throwable caught) {
									Window.alert("Saving Fails!");

								}

								public void onSuccess(Object result) {
									if (result.toString().equalsIgnoreCase(
											"false"))
										Window.alert("Saving Fails!");

								}
							});

				}
			}
		});

		final PushButton delete_pushButton = new PushButton(new Image(
				"icon/delete_config1.gif"), new Image("icon/delete_config.gif"));
		toolBarlPanel.add(delete_pushButton);
		delete_pushButton.setTitle("Delete Selected File");
		delete_pushButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {

				boolean delete = Window.confirm("Are you sure to delete "
						+ packageTree.getSelectedItem().getText().trim() + "?");
				if (delete) {
					if (packageTree.getSelectedItem() instanceof ProjectTreeItem) {
						ClassUnit classUnit = new ClassUnit();
						classUnit.projectName = packageTree.getSelectedItem()
								.getText().trim();
						FileBuilder.Util.getInstance().deleteFile(classUnit,
								"project", new AsyncCallback() {

									public void onFailure(Throwable caught) {
										Window.alert("Deleting Fails!");

									}

									public void onSuccess(Object result) {
										if (result.toString().equalsIgnoreCase(
												"false"))
											return;
										if (editorPanel.getWidgetCount() > 0)
											editorPanel.remove(0);
										packageTree.getSelectedItem().remove();

									}
								});
					} else if (packageTree.getSelectedItem() instanceof PackageTreeItem) {
						ClassUnit classUnit = new ClassUnit();
						classUnit.packageName = packageTree.getSelectedItem()
								.getText().trim();
						classUnit.projectName = packageTree.getSelectedItem()
								.getParentItem().getText().trim();
						FileBuilder.Util.getInstance().deleteFile(classUnit,
								"package", new AsyncCallback() {

									public void onFailure(Throwable caught) {
										Window.alert("Deleting Fails!");

									}

									public void onSuccess(Object result) {
										if (result.toString().equalsIgnoreCase(
												"false"))
											return;

										if (editorPanel.getWidgetCount() > 0)
											editorPanel.remove(0);
										packageTree.getSelectedItem().remove();

									}
								});
					} else if (packageTree.getSelectedItem() instanceof ClassTreeItem) {
						ClassUnit classUnit = (ClassUnit) packageTree
								.getSelectedItem().getUserObject();
						FileBuilder.Util.getInstance().deleteFile(classUnit,
								"class", new AsyncCallback() {

									public void onFailure(Throwable caught) {
										Window.alert("Deleting Fails!");

									}

									public void onSuccess(Object result) {
										if (result.toString().equalsIgnoreCase(
												"false"))
											return;

										if (editorPanel.getWidgetCount() > 0)
											editorPanel.remove(0);
										packageTree.getSelectedItem().remove();

									}
								});
					}

				}
			}

		});

		final PushButton debug_pushButton = new PushButton(new Image(
				"icon/debug_exc1.gif"), new Image("icon/debug_exc.gif"));
		toolBarlPanel.add(debug_pushButton);
		debug_pushButton.setTitle("Debug Selected File");
		debug_pushButton.addClickListener(new ClickListener() {
			public void onClick(final Widget sender) {
				Window.alert("I can't debug now. I am still learning it!");
			}
		});

		compile_pushButton = new PushButton(new Image("icon/redo_edit1.gif"),
				new Image("icon/redo_edit.gif"));
		toolBarlPanel.add(compile_pushButton);
		compile_pushButton.setTitle("Compile Selected File");

		run_pushButton = new PushButton(new Image("icon/run_exc1.gif"),
				new Image("icon/run_exc.gif"));
		toolBarlPanel.add(run_pushButton);
		run_pushButton.setTitle("Run Selected File");
		initWidget(toolBarlPanel);

		down_pushButton = new PushButton(new Image("icon/import_wiz1.gif"),
				new Image("icon/import_wiz.gif"));
		toolBarlPanel.add(down_pushButton);
		down_pushButton.setTitle("Download Compiled File");
	}

	public PushButton getCompile_pushButton() {
		return compile_pushButton;
	}

	public void setCompile_pushButton(PushButton compile_pushButton) {
		this.compile_pushButton = compile_pushButton;
	}

	public PushButton getRun_pushButton() {
		return run_pushButton;
	}

	public void setRun_pushButton(PushButton run_pushButton) {
		this.run_pushButton = run_pushButton;
	}

	public PushButton getDown_pushButton() {
		return down_pushButton;
	}

	public PushButton getSave_pushButton() {
		return save_pushButton;
	}
}
