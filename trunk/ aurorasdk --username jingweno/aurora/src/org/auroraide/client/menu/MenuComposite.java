package org.auroraide.client.menu;

import org.auroraide.client.ui.codePressEditor.CodePressEditorWidget;
import org.auroraide.client.ui.editorTabPanel.EditorTabPanel;
import org.auroraide.client.ui.newDialog.NewProjectDialog;
import org.auroraide.client.ui.treeItem.PackageTreeItem;
import org.auroraide.client.ui.treeItem.ProjectTreeItem;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Tree;

public class MenuComposite extends Composite {

	private ToggleMenuItem jdk_compilerMenu;
	private ToggleMenuItem ecj_compilerMenu;
	private ToggleMenuItem line_editMenu;
	private ToggleMenuItem autoComplete_editMenu;
	private ToggleMenuItem syn_editMenu;
	private ToggleMenuItem readOnly_editMenu;
	private TwoComponentMenuItem compile_runMenu;
	private TwoComponentMenuItem run_runMenu;

	public MenuComposite(final Tree packageTree,
			final NewProjectDialog newProject, final EditorTabPanel editorPanel) {
		final MenuBar menuBar = new MenuBar();

		final MenuBar fileMenu = new MenuBar(true);

		final MenuBar newFileMenu = new MenuBar(true);

		final TwoComponentMenuItem javaProject_newFileMenu = new TwoComponentMenuItem(
				"Java Project", new Image("icon/newjprj_wiz1.gif"),
				new Command() {

					public void execute() {
						newProject.setType("project");
						newProject.center();
						newProject.show();

					}
				});
		
		newFileMenu.addItem(javaProject_newFileMenu);

		final TwoComponentMenuItem project_newFileMenu = new TwoComponentMenuItem(
				"Project...", new Image("icon/new_wiz.gif"), new Command() {

					public void execute() {
						newProject.setType("project");
						newProject.center();
						newProject.show();

					}
				});
		newFileMenu.addItem(project_newFileMenu);

		newFileMenu.addItem("<hr>", true, (Command) null);

		final TwoComponentMenuItem package_newFileMenu = new TwoComponentMenuItem(
				"Package", new Image("icon/newpack_wiz1.gif"), new Command() {

					public void execute() {
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
		newFileMenu.addItem(package_newFileMenu);

		final TwoComponentMenuItem class_newFileMenu = new TwoComponentMenuItem(
				"Class", new Image("icon/newclass_wiz1.gif"), new Command() {

					public void execute() {
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
		newFileMenu.addItem(class_newFileMenu);

		newFileMenu.addItem("<hr>", true, (Command) null);

		final TwoComponentMenuItem ther_newFileMenu = new TwoComponentMenuItem(
				"Other...", new Image("icon/new_wiz1.gif"), new Command() {

					public void execute() {
						newProject.setType("project");
						newProject.center();
						newProject.show();
					}
				});
		newFileMenu.addItem(ther_newFileMenu);

		final MenuItem new_fileMenu = fileMenu
				.addItem(
						"<img src=\"icon/add_obj.gif\"/>  New		<img src=\"icon/run_co.gif\"/>",
						true, newFileMenu);

		final MenuItem open_fileMenu = fileMenu.addItem(
				"<img src=\"icon/opentype.gif\"/>  Open File...", true,
				new Command() {

					public void execute() {
						// TODO Auto-generated method stub

					}
				});

		fileMenu.addItem("<hr>", true, (Command) null);

		final MenuItem close_fileMenu = fileMenu.addItem(
				"<img src=\"icon/close_view.gif\"/>  Close", true,
				new Command() {

					public void execute() {
						// TODO Auto-generated method stub

					}
				});

		final MenuItem closeAll_fileMenu = fileMenu.addItem(
				"<img src=\"icon/error_obj.gif\"/>  Close All", true,
				new Command() {

					public void execute() {
						// TODO Auto-generated method stub

					}
				});

		final MenuItem delete_fileMenu = fileMenu.addItem(
				"<img src=\"icon/delete_config1.gif\"/>  Delete", true,
				new Command() {

					public void execute() {
						boolean delete = Window
								.confirm("Are you sure to delete "
										+ packageTree.getSelectedItem()
												.getText().trim() + "?");
						if (delete) {

							editorPanel.remove(0);

							packageTree.getSelectedItem().remove();
						}

					}
				});

		final MenuItem file_menuBar = menuBar.addItem("File    ", fileMenu);
		file_menuBar.setStylePrimaryName("gwt-MenuItem1");
		file_menuBar.setStyleName("gwt-MenuItem1");

		fileMenu.addItem("<hr>", true, (Command) null);

		final MenuItem save_fileMenu = fileMenu.addItem(
				"<img src=\"icon/save_edit1.gif\"/>  Save", true,
				new Command() {

					public void execute() {
						// TODO Auto-generated method stub

					}
				});

		final MenuItem saveAs_fileMenu = fileMenu.addItem(
				"<img src=\"icon/saveas_edit1.gif\"/>  Save As...", true,
				new Command() {

					public void execute() {
						// TODO Auto-generated method stub

					}
				});

		fileMenu.addItem("<hr>", true, (Command) null);

		final MenuItem exit_fileMenu = fileMenu.addItem("     Exit",
				new Command() {
					public void execute() {
						boolean close = Window
								.confirm("Are you sure to close Aurora SDK?");
						if (close)
							closeWindow();
					}
				});

		final MenuBar runMenu = new MenuBar(true);

		final MenuBar editMenu = new MenuBar(true);

		line_editMenu = new ToggleMenuItem("Line Numbers",
				new Image("Yes.gif"), new Image("No.gif"), new Command() {
					public void execute() {
						CodePressEditorWidget selectedEditor = (CodePressEditorWidget) editorPanel
								.getWidget(editorPanel.getTabBar()
										.getSelectedTab());
						selectedEditor.toggleLineNumbers();
						line_editMenu.toggle();

					}
				});

		editMenu.addItem(line_editMenu);

		autoComplete_editMenu = new ToggleMenuItem("Auto Complete", new Image(
				"Yes1.gif"), new Image("No1.gif"), new Command() {

			public void execute() {
				CodePressEditorWidget selectedEditor = (CodePressEditorWidget) editorPanel
						.getWidget(editorPanel.getTabBar().getSelectedTab());
				selectedEditor.toggleAutoComplete();
				autoComplete_editMenu.toggle();

			}
		});
		editMenu.addItem(autoComplete_editMenu);

		syn_editMenu = new ToggleMenuItem("Syntax Highlight", new Image(
				"Yes2.gif"), new Image("No2.gif"), new Command() {

			public void execute() {
				CodePressEditorWidget selectedEditor = (CodePressEditorWidget) editorPanel
						.getWidget(editorPanel.getTabBar().getSelectedTab());
				selectedEditor.toggleEditor();
				syn_editMenu.toggle();

			}
		});
		editMenu.addItem(syn_editMenu);

		readOnly_editMenu = new ToggleMenuItem("Read Only",
				new Image("No3.gif"), new Image("Yes3.gif"), new Command() {

					public void execute() {
						CodePressEditorWidget selectedEditor = (CodePressEditorWidget) editorPanel
								.getWidget(editorPanel.getTabBar()
										.getSelectedTab());
						selectedEditor.toogleReadOnly();
						readOnly_editMenu.toggle();

					}
				});
		editMenu.addItem(readOnly_editMenu);

		final MenuItem edit_menuBar = menuBar.addItem("Show    ", editMenu);
		edit_menuBar.setStylePrimaryName("gwt-MenuItem1");
		edit_menuBar.setStyleName("gwt-MenuItem1");

		final MenuItem run_menuBar = menuBar.addItem("Run    ", runMenu);
		run_menuBar.setStylePrimaryName("gwt-MenuItem1");
		run_menuBar.setStyleName("gwt-MenuItem1");

		compile_runMenu = new TwoComponentMenuItem("Compile", new Image(
				"icon/redo_edit1.gif"), (Command) null);
		runMenu.addItem(compile_runMenu);

		run_runMenu = new TwoComponentMenuItem("Run", new Image(
				"icon/run_exc1.gif"), (Command) null);
		runMenu.addItem(run_runMenu);

		final TwoComponentMenuItem debug_runMenu = new TwoComponentMenuItem(
				"Debug", new Image("icon/debug_exc1.gif"), new Command() {
					public void execute() {
						Window
								.alert("I can't debug now. I am still learning it!");
					}
				});
		runMenu.addItem(debug_runMenu);

		final MenuBar compilerMenu = new MenuBar(true);

		runMenu.addItem("<hr>", true, (Command) null);

		final MenuItem compiler_runMenu = runMenu
				.addItem(
						"<img src=\"icon/annotation_obj.gif\"/>  Java Compiler		<img src=\"icon/run_co.gif\"/>",
						true, compilerMenu);

		ecj_compilerMenu = new ToggleMenuItem("Eclipse", new Image(
				"icon/Yes.gif"), new Image("icon/blank.gif"), (Command) null);

		jdk_compilerMenu = new ToggleMenuItem("Sun", new Image(
				"icon/blank1.gif"), new Image("icon/Yes1.gif"), (Command) null);

		compilerMenu.addItem(ecj_compilerMenu);
		compilerMenu.addItem(jdk_compilerMenu);

		final MenuBar helpMenu = new MenuBar(true);

		final TwoComponentMenuItem welcome_helpMenu = new TwoComponentMenuItem(
				"Welcome", new Image("icon/information.gif"), new Command() {

					public void execute() {
						Window.alert("Welcome to Aurora SDK!");

					}
				});
		helpMenu.addItem(welcome_helpMenu);

		helpMenu.addItem("<hr>", true, (Command) null);

		final TwoComponentMenuItem about_helpMenu = new TwoComponentMenuItem(
				"About Aurora SDK", new Image("icon/linkto_help.gif"),
				new Command() {

					public void execute() {
						Window
								.alert("Aurora SDK\n\nVersion:0.1\n\nCopyright Owen 2007\nEmail:jingweno@cs.ubc.ca");

					}
				});
		helpMenu.addItem(about_helpMenu);

		final MenuItem help_menuBar = menuBar.addItem("Help    ", helpMenu);
		help_menuBar.setStylePrimaryName("gwt-MenuItem1");
		help_menuBar.setStyleName("gwt-MenuItem1");
		initWidget(menuBar);
	}

	public TwoComponentMenuItem getCompile_runMenu() {
		return compile_runMenu;
	}

	public void setCompile_runMenu(TwoComponentMenuItem compile_runMenu) {
		this.compile_runMenu = compile_runMenu;
	}

	public TwoComponentMenuItem getRun_runMenu() {
		return run_runMenu;
	}

	public void setRun_runMenu(TwoComponentMenuItem run_runMenu) {
		this.run_runMenu = run_runMenu;
	}

	private native void closeWindow()/*-{
			$wnd.close();
		}-*/;

	public ToggleMenuItem getEcj_compilerMenu() {
		return ecj_compilerMenu;
	}

	public ToggleMenuItem getJdk_compilerMenu() {
		return jdk_compilerMenu;
	}

}
