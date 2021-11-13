package cipherApp;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MainWindow {

	protected Shell shlLogin;

	private String userName = null;
	private String password = null;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlLogin.open();
		shlLogin.layout();
		while (!shlLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLogin = new Shell(SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shlLogin.setBackground(SWTResourceManager.getColor(0, 0, 0));
		shlLogin.setSize(575, 416);
		shlLogin.setText("Home");
		
		

		Button caeserBtn = new Button(shlLogin, SWT.BORDER | SWT.FLAT);
		
		caeserBtn.setGrayed(true);
		caeserBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				caeser window = new caeser();
				
				window.open();
				
			}
		});
		caeserBtn.setTouchEnabled(true);
		caeserBtn.setSelection(true);
		caeserBtn.setForeground(SWTResourceManager.getColor(102, 204, 255));
		caeserBtn.setBounds(234, 30, 123, 25);
		caeserBtn.setText("Caeser Cipher");
		
		
		Button vigenereBtn = new Button(shlLogin, SWT.BORDER | SWT.FLAT);
		vigenereBtn.setGrayed(true);
		vigenereBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vigenere window = new vigenere();
				
				window.open();
			}
			
		});
		
		
		vigenereBtn.setTouchEnabled(true);
		vigenereBtn.setSelection(true);
		vigenereBtn.setText("Vigenere Cipher");
		vigenereBtn.setForeground(SWTResourceManager.getColor(102, 204, 255));
		vigenereBtn.setBounds(234, 81, 123, 25);
		
		
		Button playfairBtn = new Button(shlLogin, SWT.BORDER | SWT.FLAT);
		playfairBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				playfair window = new playfair();
				
				window.open();
			}
		});
		playfairBtn.setTouchEnabled(true);
		playfairBtn.setText("Playfair Cipher");
		playfairBtn.setSelection(true);
		playfairBtn.setGrayed(true);
		playfairBtn.setForeground(SWTResourceManager.getColor(102, 204, 255));
		playfairBtn.setBounds(234, 131, 123, 25);
		

		
	}

}
