package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();

		ArrayList<TextProcessor> list = new ArrayList<>();
		
		list.add(new SingleWordCounter("nils"));
		list.add(new SingleWordCounter("norge"));
		
		MultiWordCounter mw = new MultiWordCounter(REGIONS);
		Set<String> exceptionWords = new HashSet<>();
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		
		while(scan.hasNext()) {
			exceptionWords.add(scan.next().toLowerCase());
		}
		scan.close();
		
		GeneralWordCounter gwc = new GeneralWordCounter(exceptionWords);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			
			mw.process(word);
			gwc.process(word);

			for(TextProcessor n : list) {
				n.process(word);
			}
		}

		s.close();

		for(TextProcessor n : list) {
			n.report();
	}
		mw.report();
		gwc.report();
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
	}
}