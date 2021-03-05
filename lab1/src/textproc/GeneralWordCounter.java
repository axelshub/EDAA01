package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> m;
	private Set<String> exceptionWords;
	
	
	public GeneralWordCounter(Set<String> exceptionWords) {
		this.exceptionWords = exceptionWords;
		m = new TreeMap<>();
	}
	public void process(String w) {
		if(!exceptionWords.contains(w)) {
			if(m.containsKey(w)) {
				m.put(w, m.get(w) + 1);
			} else {
				m.put(w, 1);
			}
		}
	}
	
	public void report() {
		//for(String key: m.keySet()) {
		//	if(m.get(key)> 200) {
		//		System.out.println(key + ": " + m.get(key));
			//}
		//}
		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		
		wordList.sort(new WordCountComparator());
		
		int n = 10;
		if(wordList.size() < n) {
			n = wordList.size();
		}
		for(int i = 0; i < n; i++) {
			System.out.println(wordList.get(i));
		}
	}
	
	public List<Map.Entry<String, Integer>> getWordList() {
        return new ArrayList<>(m.entrySet());
}

}
