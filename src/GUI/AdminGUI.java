package GUI;

import documentTools.InvalidFileTypeException;
import documentTools.TitleAuthorExtractionException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * AdminGUI - Handles all user interface actions for the GutenSearch Admin
 * Panel.
 *
 * @author Team J - Joey Arbogast and Melanie Piotrowski
 * @version 2.0
 */
public class AdminGUI
{
	private Helper helper;
	
	private JButton browseButton, uploadButton;
	private JFrame frame;
	private JPanel gridBagPanel;
	private JTextField authorTextField, filepathTextField, titleTextField;
	
	private final int COLUMNS = 2;
	private final int ROWS = 5;
	private final int WINDOW_HEIGHT = 210;
	private final int WINDOW_WIDTH = 600;
	
	/**
	 * Constructor - Creates layout of Admin Panel.
	 */
	public AdminGUI()
	{
		// Set panel attributes
		gridBagPanel = new JPanel();
		gridBagPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		gridBagPanel.setLayout(new GridBagLayout());
		
		// Set frame attributes
		frame = new JFrame("Admin Panel");
		frame.setContentPane(gridBagPanel);
		frame.setDefaultCloseOperation(1);
		
		// Build the panel and add to frame
		buildGridBagPanel();
	}
	
	/**
	 * buildGridBagPanel - builds a GridBag layout panel with text fields, labels,
	 * and buttons.
	 */
	public void buildGridBagPanel()
	{
		// Set default font
		Font font = new Font(null, Font.BOLD, 14);
		
		GridBagConstraints button = new GridBagConstraints();
		GridBagConstraints label = new GridBagConstraints();
		GridBagConstraints textField = new GridBagConstraints();
		
		// Create labels
		JLabel authorLabel = new JLabel("Author");
		authorLabel.setFont(font);
		
		JLabel filepathLabel = new JLabel("File Path");
		filepathLabel.setFont(font);
		
		JLabel titleLabel = new JLabel("Document Title");
		titleLabel.setFont(font);
		
		// Set button action listeners
		browseButton = new JButton("Browse");
		browseButton.addActionListener(new BrowseButtonListener());
		browseButton.setFont(font);
		
		uploadButton = new JButton("Upload");
		uploadButton.addActionListener(new UploadButtonListener());
		uploadButton.setFont(font);
		
		// Set text field attributes and lock file path field
		authorTextField = new JTextField();
		authorTextField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		filepathTextField = new JTextField();
		filepathTextField.setBackground(Color.WHITE);
		filepathTextField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		filepathTextField.setEditable(false);
		
		titleTextField = new JTextField();
		titleTextField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		button.fill = GridBagConstraints.NONE;
		button.insets = new Insets(0, 5, 5, 5);
		button.weightx = 1;
		button.weighty = 1;
		
		label.fill = GridBagConstraints.HORIZONTAL;
		label.insets = new Insets(5, 5, 0, 5);
		label.weightx = 1;
		
		textField.anchor = GridBagConstraints.LINE_START;
		textField.fill = GridBagConstraints.HORIZONTAL;
		textField.insets = new Insets(0, 5, 5, 5);
		textField.ipady = 11;
		textField.weightx = 1;
		textField.weighty = 1;
		
		// populate GridBag layout
		for (int i = 0; i < ROWS; i++)
		{
			button.gridy = i;
			label.gridy = i;
			textField.gridy = i;
			
			for (int j = 0; j < COLUMNS; j++)
			{
				button.gridx = j;
				label.gridx = j;
				textField.gridx = j;
				
				switch (i)
				{
					case 0:
						// If first row, first column
						if (j == 0)
						{
							gridBagPanel.add(filepathLabel, label);
						}
						break;
					case 1:
						// If second row, first column
						if (j == 0)
						{
							// File path spans 2 columns with manual offset
							textField.gridwidth = COLUMNS;
							textField.insets = new Insets(0, 5, 5, 102);
							
							gridBagPanel.add(filepathTextField, textField);
							
							textField.gridwidth = 1;
							textField.insets = new Insets(0, 5, 5, 5);
						}
						// If second row, second column
						if (j == 1)
						{
							button.anchor = GridBagConstraints.LINE_END;
							gridBagPanel.add(browseButton, button);
						}
						break;
					case 2:
						// If third row, first column
						if (j == 0)
						{
							gridBagPanel.add(titleLabel, label);
						}
						// If third row, second column
						if (j == 1)
						{
							gridBagPanel.add(authorLabel, label);
						}
						break;
					case 3:
						// If fourth row, first column
						if (j == 0)
						{
							gridBagPanel.add(titleTextField, textField);
						}
						// If Fourth row, second column
						if (j == 1)
						{
							gridBagPanel.add(authorTextField, textField);
						}
						break;
					case 4:
						// If fifth row, first column
						if (j == 0)
						{
							button.anchor = GridBagConstraints.LINE_START;
							gridBagPanel.add(uploadButton, button);
						}
						break;
					default:
						break;
				}
			}
		}
	}
	
	/**
	 * display - Displays frame and sets size.
	 */
	public void display()
	{
		// Build window and set to visible
		frame.pack();
		frame.setVisible(true);
		
		// Set window size and lock
		frame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	/**
	 * main - instantiates Admin GUI.
	 */
	public static void main(String[] args)
	{
		// Instantiate an instance of the AdminGUI
		new AdminGUI().display();
	}
	
	/**
	 * BrowseButtonListener - implements ActionListener interface listener for
	 * browse button.
	 *
	 * @author Team J - Joey Arbogast
	 * @version 1.0
	 */
	private class BrowseButtonListener implements ActionListener
	{
		/**
		 * actionPerformed - executes when the user clicks on the browse button.
		 * 
		 * @param e event object
		 */
		public void actionPerformed(ActionEvent e)
		{
			// Store return file of file chooser showOpenDialog method
			int returnVal = 0;
			
			File docFile;
			JFileChooser fc;
			
			// If the browse button was clicked...
			if (e.getSource() == browseButton)
			{
				// Set the selection mode to Files only
				fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				returnVal = fc.showOpenDialog(frame);
				
				// If the return value was equal to the Approve option...
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					// Get the selected file and store in File object
					docFile = fc.getSelectedFile();
					
					// Set the filepath TextField to the path selected in the browse
					// dialog window
					filepathTextField.setText(docFile.getAbsolutePath());
				}
			}
		}
	}
	
	/**
	 * UploadButtonListener - implements ActionListener interface listener for
	 * upload button.
	 *
	 * @author Team J - Joey Arbogast
	 * @version 1.0
	 */
	private class UploadButtonListener implements ActionListener
	{
		/**
		 * actionPerformed - executes when the user clicks on the upload button.
		 * 
		 * @param e event object
		 */
		public void actionPerformed(ActionEvent e)
		{
			// If the upload button was clicked...
			if (e.getSource() == uploadButton)
			{
				try
				{
					// Check document type
					if (!(filepathTextField.getText().contains(".txt")))
					{
						throw new InvalidFileTypeException();
					}
					
					helper = new Helper(titleTextField.getText(), authorTextField.getText(),
							filepathTextField.getText());
					JOptionPane.showMessageDialog(null, helper.getMessage());
				}
				catch (FileNotFoundException error)
				{
					JOptionPane.showMessageDialog(null,
							error.getMessage() + "Invalid file path! \nCannot locate file.");
				}
				catch (InvalidFileTypeException error)
				{
					JOptionPane.showMessageDialog(null, error.getMessage());
				}
				catch (TitleAuthorExtractionException error)
				{
					JOptionPane.showMessageDialog(null, error.getMessage());
				}
			}
		}
	}
}
