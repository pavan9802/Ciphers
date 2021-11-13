package cipherApp;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.LinkedHashSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.ibm.icu.util.CharsTrie.Iterator;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import java.io.*;
import java.util.*;

public class playfair {

	protected Shell shlPlayfairCipher;
	private Text inputTxt;
	private Text outputTxt;
	private Text keyTxt;
	//Create global variable so helper methods will not need vars
	
	String key;
    String text;
    char[][] matrix = new char[5][5];

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			playfair window = new playfair();
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
		shlPlayfairCipher.open();
		shlPlayfairCipher.layout();
		while (!shlPlayfairCipher.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPlayfairCipher = new Shell();
		shlPlayfairCipher.setSize(450, 300);
		shlPlayfairCipher.setText("Playfair Cipher");
		
		Label lblKey = new Label(shlPlayfairCipher, SWT.NONE);
		lblKey.setText("Key");
		lblKey.setBounds(100, 63, 59, 14);
		
		Label lblInput = new Label(shlPlayfairCipher, SWT.NONE);
		lblInput.setText("Input");
		lblInput.setBounds(100, 32, 59, 14);
		
		inputTxt = new Text(shlPlayfairCipher, SWT.BORDER);
		inputTxt.setBounds(165, 29, 192, 25);
		
		Button encodeBtn = new Button(shlPlayfairCipher, SWT.NONE);
		encodeBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				key = keyTxt.getText().toLowerCase();
				
				text = inputTxt.getText().toLowerCase();
				
				duplicateRemover();
				generateKey();
				outputTxt.setText(encryptMessage());
				
			}
		});
		encodeBtn.setText("Encode");
		encodeBtn.setBounds(100, 104, 96, 27);
		
		Label lblOutput = new Label(shlPlayfairCipher, SWT.NONE);
		lblOutput.setText("Output");
		lblOutput.setBounds(100, 194, 59, 14);
		
		outputTxt = new Text(shlPlayfairCipher, SWT.BORDER);
		outputTxt.setBounds(165, 194, 192, 27);
		
		Button decodeBtn = new Button(shlPlayfairCipher, SWT.NONE);
		decodeBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				key = keyTxt.getText().toLowerCase();
				
				text = inputTxt.getText().toLowerCase();
				
				duplicateRemover();
				generateKey();
				outputTxt.setText(decryptMessage());
				
			}
		});
		decodeBtn.setText("Decode");
		decodeBtn.setBounds(261, 104, 96, 27);
		
		keyTxt = new Text(shlPlayfairCipher, SWT.BORDER);
		keyTxt.setBounds(165, 60, 192, 27);

	}
	
	// takes out duplicates
    public void duplicateRemover()
    {
        LinkedHashSet<Character> set = new LinkedHashSet<Character>();
        
        String newKey = "";
        
        for (int i = 0; i < key.length(); i++)
            set.add(key.charAt(i));
  
        java.util.Iterator<Character> it = set.iterator();
        
        while (it.hasNext()) {
            newKey += (Character)it.next();
        }
        
        key = newKey;
    }
    public void generateKey()
    
    {
        Set<Character> set = new HashSet<Character>();
        
        for (int i = 0; i < key.length(); i++)
        {
            if (key.charAt(i) == 'j') {
            	continue;
            }
            
            set.add(key.charAt(i));
        }
  
        // remove repeated characters from the cipher key
        String tempKey = new String(key);
        
        for (int i = 0; i < 26; i++) 
        {
            char ch = (char)(i + 97);
            if (ch == 'j') {
            	continue;
            }
              
            
            if (!set.contains(ch)) {
            	 tempKey += ch;
            }
               
        }
  
        // creates the cipher matrix
        for (int i = 0, idx = 0; i < 5; i++) {
        	
        	for (int j = 0; j < 5; j++) {
        		matrix[i][j] = tempKey.charAt(idx++);
        	}
            
        	
        }
            
  
        
       
    }
    
    public String formateEncryptedTxt()
    {
        String formatedPT = "";
        int len = text.length();
        
        for (int i = 0; i < len; i++)
        {
            // replaces j with i int he plaintext
           
            if (text.charAt(i) == 'j')
                formatedPT += 'i';
            else
                formatedPT += text.charAt(i);
        }
  
        // inserts x between same characters
        // ex: assembly -> asxsembly
        
        for (int i = 0; i < formatedPT.length()-1; i += 2) 
        {
            if (formatedPT.charAt(i) == formatedPT.charAt(i + 1))
                formatedPT = formatedPT.substring(0, i + 1) + 'x' + formatedPT.substring(i + 1);
        }
        
        // makes the plaintext into an even text
        if (len % 2 == 1)
            formatedPT += 'x'; 
        
        return formatedPT;
    }
  
    // function to group every two characters
    public String[] formPairs(String formatedPT)
    {
        int len = formatedPT.length();
        String[] pairs = new String[len / 2];
        
        for (int i = 0, cnt = 0; i < len / 2; i++)
            pairs[i] = formatedPT.substring(cnt, cnt += 2);
        
        return pairs;
    }
  
    // function to get position of character in key table
    public int[] getCharPos(char ch)
    {
        int[] keyPos = new int[2];
        
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5; j++)
            {
                
                if (matrix[i][j] == ch)
                {
                    keyPos[0] = i;
                    keyPos[1] = j;
                    break;
                }
            }
        }
        return keyPos;
    }
  
    public String encryptMessage()
    {
        String message = formateEncryptedTxt();
        String[] msgPairs = formPairs(message);
        String encryptedText = "";
        
        for (int i = 0; i < msgPairs.length; i++) 
        {
            char ch1 = msgPairs[i].charAt(0);
            char ch2 = msgPairs[i].charAt(1);
            
            //ch1Pos[0] & ch2Pos [0] = row
            //ch1Pos[1] & ch2Pos [1] = col
            int[] ch1Pos = getCharPos(ch1);
            int[] ch2Pos = getCharPos(ch2);
  
            // if both the characters are in the same row move their column position 1 to the right
            if (ch1Pos[0] == ch2Pos[0]) {
            	
                ch1Pos[1] = (ch1Pos[1] + 1) % 5;
                ch2Pos[1] = (ch2Pos[1] + 1) % 5;
            }
            
            // if both the characters are in the same column their column position 1 to the down
            else if (ch1Pos[1] == ch2Pos[1])
            {
                ch1Pos[0] = (ch1Pos[0] + 1) % 5;
                ch2Pos[0] = (ch2Pos[0] + 1) % 5;
            }
            
            // if both the characters are in different rows and columns switch the column position of both characters
            else {
                int temp = ch1Pos[1];
                ch1Pos[1] = ch2Pos[1];
                ch2Pos[1] = temp;
            }
            
            // with the new matrix indexes concatenate the chars at their position to the encrypted text
            encryptedText = encryptedText + matrix[ch1Pos[0]][ch1Pos[1]] + matrix[ch2Pos[0]][ch2Pos[1]];
        }
        
        return encryptedText;
    }
    
    public String decryptMessage()
    {
        String message = text;
        String[] msgPairs = formPairs(message);
        String decryptedTxt = "";
        
        for (int i = 0; i < msgPairs.length; i++) 
        {
            char ch1 = msgPairs[i].charAt(0);
            char ch2 = msgPairs[i].charAt(1);
            
            //ch1Pos[0] & ch2Pos [0] = row
            //ch1Pos[1] & ch2Pos [1] = col
            int[] ch1Pos = getCharPos(ch1);
            int[] ch2Pos = getCharPos(ch2);
  
            // if both the characters are in the same row move their column position 1 to the left
            if (ch1Pos[0] == ch2Pos[0]) {
            	
            
                
            	ch1Pos[1] = (ch1Pos[1] + 4) % 5;
                ch2Pos[1] = (ch2Pos[1] + 4) % 5;
            }
            
            // if both the characters are in the same column their column position 1 to the up
            else if (ch1Pos[1] == ch2Pos[1])
            {
                ch1Pos[0] = (ch1Pos[0] + 4) % 5;
                ch2Pos[0] = (ch2Pos[0] + 4) % 5;
            }
            
            // if both the characters are in different rows and columns switch the column position of both characters
            else {
                int temp = ch1Pos[1];
                ch1Pos[1] = ch2Pos[1];
                ch2Pos[1] = temp;
            }
            
            // with the new matrix indexes concatenate the chars at their position to the encrypted text
            if( matrix[ch2Pos[0]][ch2Pos[1]] == 'x') {
    
            	decryptedTxt = decryptedTxt + matrix[ch1Pos[0]][ch1Pos[1]];
            }else {
            	decryptedTxt = decryptedTxt + matrix[ch1Pos[0]][ch1Pos[1]] + matrix[ch2Pos[0]][ch2Pos[1]];
            }
            
        }
        
        return decryptedTxt;
    }
    
}


