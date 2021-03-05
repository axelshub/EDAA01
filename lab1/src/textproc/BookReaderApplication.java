package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BookReaderApplication {
	
	public static void main(String[] args) throws FileNotFoundException {
		//Scans Nils Holgersson
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		
		
		//Creates Set of exceptionwords from text file
		Scanner t = new Scanner(new File("undantagsord.txt"));	
		Set<String> stopwords = new HashSet<String>();		//Creating an empty set
		while(t.hasNext()) {
			stopwords.add(t.next());
		}
		
	
		//Adds GeneralWordCounter object to list
		GeneralWordCounter gwc = new GeneralWordCounter(stopwords);

		while(s.hasNext()) {
			gwc.process(s.next().toLowerCase());
			
		}
		
		new BookReaderController(gwc);
		
	}

}
