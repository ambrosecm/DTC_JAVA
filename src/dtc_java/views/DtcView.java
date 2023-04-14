package dtc_java.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import java.io.File;
import java.io.IOException;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import javax.inject.Inject;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.wb.swt.SWTResourceManager;

import soot.*;


import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.StackLayout;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.MessageBox;

public class DtcView extends ViewPart {
	public DtcView() {
	}

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "dtc_java.views.DtcView";

	@Inject
	IWorkbench workbench;
	private Text text;

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		@Override
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		@Override
		public Image getImage(Object obj) {
			return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(240, 248, 255));
		parent.setLayout(null);

		Button btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.setImage(ResourceManager.getPluginImage("DTC_JAVA", "icons/releng_gears.gif"));
		btnNewButton.setBounds(41, 96, 96, 27);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
				fileDialog.setFilterNames(new String[] { "java文件 (*.java)" });// 设置扩展名
				fileDialog.setFilterExtensions(new String[] { "*.java" });// 设置文件扩展名
				String selectedFile = fileDialog.open();
				text.setText(selectedFile);
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.NORMAL));
		btnNewButton.setText("选择文件");

		text = new Text(parent, SWT.BORDER);
		text.setBounds(143, 98, 295, 23);

		Button btnNewButton_1 = new Button(parent, SWT.CENTER);
		btnNewButton_1.setImage(ResourceManager.getPluginImage("DTC_JAVA", "icons/releng_gears.gif"));
		btnNewButton_1.setBounds(98, 151, 131, 27);
		btnNewButton_1.setText("Start Analyse");

		Label lblNewLabel = new Label(parent, SWT.BORDER | SWT.CENTER);
		lblNewLabel.setBounds(41, 246, 224, 346);

		Label lblNewLabel_1 = new Label(parent, SWT.BORDER | SWT.CENTER);
		lblNewLabel_1.setBounds(272, 246, 224, 346);

		Label lblNewLabel_2 = new Label(parent, SWT.BORDER | SWT.WRAP);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(255, 255, 240));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft JhengHei", 7, SWT.NORMAL));
		lblNewLabel_2.setBounds(41, 22, 529, 34);
		lblNewLabel_2.setText("针对Java程序在代码级别进行的增加语句、修改语句、删除语句等修复操作，采用静态分析技术分析出影响的变量、语句、路径、方法、类等程序成分，生成控制流图、数据流图、抽象语法树");

		ProgressBar progressBar = new ProgressBar(parent, SWT.HORIZONTAL);
		progressBar.setBounds(252, 153, 193, 23);
		GridData data = new GridData();
		data.horizontalSpan = 2;
		data.grabExcessHorizontalSpace = true;
		progressBar.setLayoutData(data);
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		final int maximun = progressBar.getMaximum();
		final int minimus = progressBar.getMinimum();

		Combo combo = new Combo(parent, SWT.READ_ONLY);
		combo.setEnabled(false);
		String[] items = new String[] { "NONE", "控制流图CFG", "数据流图DFG", "抽象语法树AST" };
		combo.setItems(items);
		combo.setToolTipText("NONE");
		combo.setBounds(198, 202, 131, 28);
		combo.select(0);
		combo.setText("NONE");
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String text = combo.getText();
				if (text.equals(items[1])) {
					MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_SEARCH);
					mb.setText("Message");
					mb.setMessage("");
					mb.open();

				} else if (text.equals(items[2])) {

				} else if (text.equals(items[2])) {

				}
			}
		});

		// 开始分析
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text.getText() == null || text.getText().equals("")) {
					MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_SEARCH);
					mb.setText("Message");
					mb.setMessage("请先选择要分析的文件");
					mb.open();
					return;
				}
				btnNewButton_1.setEnabled(false);
				progressBar.setSelection(0);
				String filepath=text.getText().replaceAll("\\\\","/");
				
//				AstGeneratror ast=new AstGeneratror();
//				try {
//					ast.generate(filepath);
//				} catch (Exception ee) {
//					// TODO Auto-generated catch block
//					ee.printStackTrace();
//				}
				CFGGenerate cfg=new CFGGenerate();
				cfg.generate(filepath);
				
//				DataFlowGraph dfg=new DataFlowGraph();
//				dfg.generate(filepath);
				
				
				
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = minimus; i < maximun; i++) {
							try {
								Thread.sleep(50);
							} catch (Exception e) {

							}
							parent.getDisplay().asyncExec(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									if (progressBar.isDisposed()) {
										return;
									}
									progressBar.setSelection(progressBar.getSelection() + 1);
								}

							});

						}
						parent.getDisplay().asyncExec(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								while (progressBar.getSelection() != 100) {
								}
								MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_SEARCH);
								mb.setText("Message");
								mb.setMessage("Analysis Completed Successfully!");
								mb.open();
								btnNewButton_1.setEnabled(true);
								combo.setEnabled(true);
							}

						});

					}

				};
				new Thread(runnable).start();

			}
		});

		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				DtcView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager) {
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
	}

	private void makeActions() {
	}

	private void hookDoubleClickAction() {
	}

	@Override
	public void setFocus() {

	}
}
