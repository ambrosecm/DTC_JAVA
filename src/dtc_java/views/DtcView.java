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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.StackLayout;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.ResourceManager;


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
		parent.setLayout(null);
		
		Button btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.setImage(ResourceManager.getPluginImage("DTC_JAVA", "icons/releng_gears.gif"));
		btnNewButton.setBounds(41, 96, 84, 27);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(Display.getCurrent().getActiveShell(),SWT.OPEN);
				fileDialog.setFilterNames(new String[] { "java文件 (*.java)" });//设置扩展名
				fileDialog.setFilterExtensions(new String[] { "*.java" });//设置文件扩展名
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
		btnNewButton_1.setBounds(157, 153, 123, 27);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//开始分析
			}
		});
		btnNewButton_1.setText("Start Analyse");
		
		Label lblNewLabel = new Label(parent, SWT.BORDER | SWT.CENTER);
		lblNewLabel.setBounds(41, 197, 224, 346);
		
		Label lblNewLabel_1 = new Label(parent, SWT.BORDER | SWT.CENTER);
		lblNewLabel_1.setBounds(271, 197, 224, 346);
		
		Label lblNewLabel_2 = new Label(parent, SWT.BORDER | SWT.WRAP);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 7, SWT.NORMAL));
		lblNewLabel_2.setBounds(83, 22, 390, 34);
		lblNewLabel_2.setText("针对Java程序在代码级别进行的增加语句、修改语句、删除语句等修复操作，采用静态分析技术分析出影响的变量、语句、路径、方法、类等程序成分，生成控制流图、数据流图、抽象语法树");
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
