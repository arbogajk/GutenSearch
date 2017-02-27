package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * UserGUI - Handles all user interface actions for GutenSearch and gives users
 * a way to search for indexed paragraphs and launch the Admin Panel.
 *
 * @author Team J- Kevin Perkinson, Joey Arbogast, and Melanie Piotrowski
 * @version 2.0
 */
public class UserGUI
{
	private final int COLUMNS = 2;
	private final int ROWS = 3;
	private final int WINDOW_WIDTH = 800;
	private final int WINDOW_HEIGHT = 500;
	
	private JButton adminGuiButton;
	private JButton clearButton;
	private JButton searchButton;
	
	private JFrame frame;
	private JPanel gridbagPanel;
	private JPanel displayArea;
	private JTextField searchField;
	private JScrollPane scrollPane;
	
	/**
	 * Constructor - Creates layout of user GUI.
	 */
	public UserGUI()
	{
		gridbagPanel = new JPanel(new GridBagLayout());
		gridbagPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		gridbagPanel.setLayout(new GridBagLayout());
		
		frame = new JFrame("User GUI");
		frame.setContentPane(gridbagPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildGridBagPanel();
	}
	
	/**
	 * buildGridBagPanel - Builds a GridBag layout panel with a text area, text
	 * field, and buttons.
	 */
	public void buildGridBagPanel()
	{
		// Set default font
		Font font = new Font(null, Font.BOLD, 14);
		
		GridBagConstraints button = new GridBagConstraints();
		GridBagConstraints textArea = new GridBagConstraints();
		GridBagConstraints textField = new GridBagConstraints();
		
		// Set button action listeners
		adminGuiButton = new JButton("Admin GUI");
		adminGuiButton.addActionListener(new AdminButtonListener());
		adminGuiButton.setFont(font);
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ClearButtonListener());
		clearButton.setFont(font);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchButtonListener());
		searchButton.setFont(font);
		
		// Instantiate text area, scroll pane, and text field
		displayArea = new JPanel();
		displayArea.setLayout(new BoxLayout(displayArea,1));
		//displayArea.setEditable(false);
		
		scrollPane = new JScrollPane(displayArea);
		scrollPane.setBackground(Color.WHITE);
		searchField = new JTextField();
		
		// Set the font of the results area
		displayArea.setFont(new Font(null, Font.BOLD, 18));
		
		// GridBag Constraints
		button.anchor = GridBagConstraints.LINE_END;
		button.fill = GridBagConstraints.NONE;
		button.insets = new Insets(5, 5, 5, 86);
		button.weightx = 0;
		
		textArea.anchor = GridBagConstraints.LINE_START;
		textArea.fill = GridBagConstraints.BOTH;
		textArea.gridwidth = 2;
		textArea.insets = new Insets(5, 5, 5, 5);
		textArea.weightx = 1;
		textArea.weighty = 1;
		
		textField.anchor = GridBagConstraints.LINE_START;
		textField.fill = GridBagConstraints.HORIZONTAL;
		textField.gridwidth = 2;
		textField.insets = new Insets(5, 5, 5, 178);
		textField.ipady = 9;
		textField.weightx = 1;
		
		// Populate GridBag layout
		for (int i = 0; i < ROWS; i++)
		{
			button.gridy = i;
			textArea.gridy = i;
			textField.gridy = i;
			
			for (int j = 0; j < COLUMNS; j++)
			{
				button.gridx = j;
				textArea.gridx = j;
				textField.gridx = j;
				
				switch (i)
				{
					case 0:
						// If first row, first column
						if (j == 0)
						{
							gridbagPanel.add(searchField, textField);
						}
						// If first row, second column
						if (j == 1)
						{
							gridbagPanel.add(searchButton, button);
							
							button.insets = new Insets(5, 5, 5, 5);
							gridbagPanel.add(clearButton, button);
						}
						break;
					case 1:
						// If second row, first column
						if (j == 0)
						{
							gridbagPanel.add(scrollPane, textArea);
						}
						break;
					case 2:
						// If third row, first column
						if (j == 0)
						{
							button.anchor = GridBagConstraints.LINE_START;
							gridbagPanel.add(adminGuiButton, button);
						}
						break;
					default:
						break;
				}
			}
		}
	}
	
	/**
	 * clear - Clears search field and search results display area.
	 */
	private void clear()
	{
		searchField.setText("");
		displayArea.removeAll();
		scrollPane.setViewportView(displayArea);
	}
	
	/**
	 * display - Displays frame and sets size.
	 */
	public void display()
	{
		// Set search result display area attributes
		//displayArea.set
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		// Build window and set to visible
		frame.pack();
		frame.setVisible(true);
		
		// Set window size and lock
		frame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
	}
	/**
	 * createResults - populates the search results panel
	 * @param text String the full paragraph text
	 */
	private void createResults(String text){
		JButton bResults = new JButton();
		String fullText = text;
		bResults.setBounds(100, 100, 150, 200);
		bResults.addActionListener(new ExpandButtonListener(fullText));
		text = "<html>" + text.replaceAll("\\n", "<br>") + "</html>";
		
		if(text.length() >= 140)
			bResults.setText(text.substring(0, 140));
		else
			bResults.setText(text.substring(0, 80));
		
		//bResults.setMargin(new Insets(1,1,1,1));
		bResults.setBackground(Color.WHITE);
		
		bResults.setHorizontalAlignment(SwingConstants.LEFT);
		
		bResults.setFont(new Font(null, Font.BOLD, 12));
		displayArea.add(bResults);
		scrollPane.setViewportView(displayArea);
	}
	private class ExpandButtonListener implements ActionListener{
		private String fullText;
		public ExpandButtonListener(String text){
			fullText = text;
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String text = fullText;
			JFrame expandedFrame = new JFrame("Results");
			JTextArea paraText = new JTextArea();
			JPanel textPanel = new JPanel();
			paraText.setText(text);
			paraText.setFont(new Font(null,Font.BOLD,14));
			paraText.setEditable(false);
			textPanel.add(paraText);
			
			expandedFrame.add(textPanel);
			expandedFrame.setVisible(true);
			expandedFrame.setMinimumSize(new Dimension(250, 250));
			expandedFrame.setLocationRelativeTo(null);
			expandedFrame.setResizable(true);
			
			expandedFrame.setDefaultCloseOperation(1);
			expandedFrame.pack();
			
			
			
			
			expandedFrame.setVisible(true);
			
		}
		
		
		
	}
	/**
	 * AdminButtonListener - Implements ActionListener interface listener for
	 * admin panel button.
	 *
	 * @author Team J - Kevin Perkinson
	 * @version 1.0
	 */
	private class AdminButtonListener implements ActionListener
	{
		/**
		 * actionPerformed - Executes when the user clicks on the admin panel
		 * button.
		 * 
		 * @param e event object
		 */
		public void actionPerformed(ActionEvent e)
		{
			// If the admin panel button was clicked...
			if (e.getSource() == adminGuiButton)
			{
				// Launch an instance of the AdminGui class
				new AdminGUI().display();
			}
		}
	}
	
	/**
	 * ClearButtonListener - Implements ActionListener interface listener for
	 * clear button.
	 *
	 * @author Team J - Kevin Perkinson
	 * @version 1.0
	 */
	private class ClearButtonListener implements ActionListener
	{
		/**
		 * actionPerformed - Executes when the user clicks on the clear button.
		 * 
		 * @param e event object
		 */
		public void actionPerformed(ActionEvent e)
		{
			// If the clear button was clicked...
			if (e.getSource() == clearButton)
			{
				// Clear the search field and text area
				clear();
			}
		}
	}
	
	/**
	 * SearchButtonListener - Implements ActionListener interface listener for
	 * search button.
	 *
	 * @author Team J - Joey Arbogast
	 * @version 2.0
	 */
	private class SearchButtonListener implements ActionListener
	{
		/**
		 * actionPerformed - Executes when the user clicks on the search button.
		 * 
		 * @param e event object
		 */
		public void actionPerformed(ActionEvent e)
		{
			// If the search button was clicked...
			if (e.getSource() == searchButton)
			{
				displayArea.removeAll();
				scrollPane.setViewportView(displayArea);
				// Check input and display error if necessary
				if (searchField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "No results for " + searchField.getText() + ".");
				}
				else
				{
					// Create a helper object and pass the text from the searchField
					Helper helper = new Helper(searchField.getText());
					
					// Return the list of results
					List<String> results = helper.getSearchResults();
					
					// Check if the list return is null or empty
					if (results == null || results.isEmpty())
					{
						JTextArea noResults = new JTextArea();						
						noResults.setText("No results found for " + searchField.getText());
						noResults.setEditable(false);
						displayArea.add(noResults);
						scrollPane.setViewportView(noResults);
					}
					else
					{
						StringBuilder paragraphResults = new StringBuilder();
						
						// For each paragraph's toString: append it to the string builder
						for (String para : results)
						{
							createResults(para);
							//paragraphResults.append(para + "\n\n");
						}
						
						// Display the paragraph results
						//displayArea.setText(paragraphResults.toString());
					}
				}
			}
		}
	}
	
	/**
	 * main - Instantiates user GUI and loads documents from library.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Instantiate an instance of the UserGUI
		new UserGUI().display();
	}
}
