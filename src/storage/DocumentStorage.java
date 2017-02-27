package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import documentTools.Document;

/**
 * Document Storage - Provides methods to store and load list of documents.
 * 
 * @author Team J - Joey Arbogast
 * @version 2.0
 */
public abstract class DocumentStorage
{
	private static List<Document> documentsLoad = new ArrayList<>();
	private static List<Document> storeList;
	
	private final static String LIBRARY = "Library/Documents.tmp";
	
	/**
	 * loadDocuments - Loads list of Document objects.
	 * 
	 * @return List<Document> Documents
	 */
	@SuppressWarnings("unchecked")
	public static List<Document> loadDocuments()
	{
		try
		{
			// Create a FileInputStream, ObjectInputStream
			FileInputStream fileIn = new FileInputStream(LIBRARY);
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			
			// Read the object in from the file, cast to a document list and set
			// documentsLoad list equal to the returned list
			documentsLoad = (List<Document>) ois.readObject();
			
			ois.close();
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return new ArrayList<Document>(documentsLoad);
	}
	
	/**
	 * storeDocuments - Accepts a document as a parameter, adds it to a list, and
	 * writes ArrayList to a .tmp file.
	 * 
	 * @param doc Document to store
	 */
	public static void storeDocuments(Document doc)
	{
		List<Document> docs = new ArrayList<>();
		storeList = new ArrayList<>();
		
		// Get list of Documents
		try
		{
			docs = loadDocuments();
			storeList = docs;
		}
		catch (NullPointerException npe)
		{
			storeList.add(doc);
		}
		
		// Check for empty list
		if (!(docs.isEmpty()) || docs.equals(null))
		{
			for (int i = 0; i < storeList.size(); i++)
			{
				// Remove if empty
				if (doc.getTitle().equals(storeList.get(i).getTitle()))
				{
					storeList.remove(i);
				}
			}
			
			Path path = Paths.get(LIBRARY);
			
			try
			{
				Files.delete(path);
			}
			catch (IOException err)
			{
				err.printStackTrace();
			}
		}
		
		try
		{
			storeList.add(doc);
			
			File file = new File(LIBRARY);
			
			// Create a fileoutputstream with source file
			FileOutputStream docFile = new FileOutputStream(file, false);
			
			// Create an ObjectOutputStream from the FileOutputStream
			ObjectOutputStream objectOut = new ObjectOutputStream(docFile);
			
			// Write the list of documents to file
			objectOut.writeObject(storeList);
			
			storeList.clear();
			objectOut.close();
		}
		catch (IOException error)
		{
			error.printStackTrace();
		}
	}
}
