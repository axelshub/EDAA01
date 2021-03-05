package textproc;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> m;
	
	public MultiWordCounter(String [] multiWord) {
		m = new TreeMap<>();
		for (String s: multiWord) {
			m.put(s, 0);			
		}
	}
	public void process(String w) {
		if(m.containsKey(w)) {
			int count = m.get(w);
			m.put(w, count+1);
		}
	}
	
	public void report() {
		for(String key: m.keySet()) {
			System.out.println(key + ": " + m.get(key));
		}
	}
	

}
