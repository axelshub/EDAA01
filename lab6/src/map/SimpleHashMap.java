package map;


public class SimpleHashMap<K, V> implements Map<K, V> {
	
	public static void main(String args[]) {
		SimpleHashMap map = new SimpleHashMap(2);
		map.put(2,4);
		map.put(54,53);
		map.put(42,-4);
		map.put(7,5);
		map.put(7,4);
		map.put(5543,43);
		map.put(22,-4);
		map.put(71,4);
		map.put(74,1);
		map.remove(71);
		map.remove(2);
		
		System.out.println("Amount of entries: " + map.size);
		System.out.println(map.show());
		
	}
	
	private Entry<K,V>[] table;
	private static final double loadFactor = 0.75;
	private int size;
	private int capacity;
	
	/** Constructs an empty hashmap with the default initial capacity (16)
    and the default load factor (0.75). */
	public SimpleHashMap() {
		capacity = 16;
		table = (Entry<K,V>[]) new Entry[capacity];
		
	}
	
	/** Constructs an empty hashmap with the specified initial capacity
    and the default load factor (0.75). */
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		table = (Entry<K,V>[]) new Entry[capacity];

	}
	
	
	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		Entry<K,V> entry = find(index((K) arg0), (K) arg0);
		if (entry == null) {
			return null;
		} 
		return entry.getValue();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public V put(K arg0, V arg1) {
		// TODO Auto-generated method stub
		int index = index(arg0);
		
		Entry<K,V> existingEntry = find(index, arg0);
		if (existingEntry != null) { //Key exists on index
			V oldValue = existingEntry.getValue();
			existingEntry.setValue(arg1);
			if (size/capacity > 0.75) {
				rehash();
			} 
			return oldValue;
		} else { 
			if (table[index] == null) {
				table[index] = new Entry<K,V>(arg0, arg1);
				size++;
			} else {
				existingEntry = table[index];
				while (existingEntry.next != null) {
					existingEntry = existingEntry.next;
				}
				existingEntry.next = new Entry<K,V>(arg0, arg1);
				size++;
			}
		}
		if (size/capacity > 0.75) {
			rehash();
		} 
		return null;
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		//Unders??k om nyckeln arg0 finns i listan
		if(isEmpty()) {
			return null;
		}
		
		int index = index((K) arg0);
		Entry<K,V> head = table[index];
		if(head == null) { //Index is empty
			return null;
		} else if(head.getKey().equals((K) arg0)) { //Found key in first
			table[index] = head.next;
			size--;
			return head.getValue();
		} else {
			while(head.next != null) {
				if(head.next.getKey().equals((K) arg0)) {
					V value = head.next.getValue();
					head.next = head.next.next;
					size--;
					return value;
				}
				head = head.next;
			}
	
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	public String show() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < table.length; i++) {
			Entry<K,V> entry = table[i];
			if(entry != null) {
				str.append(i +". ");
				while(entry != null) {
					str.append(entry.toString());
					entry = entry.next;
				}	
			} else {
				str.append(i +". Empty ");
			}
			str.append("\n");
		}
		return str.toString();
	}
	
	private int index(K key) {
		return Math.abs(key.hashCode() % capacity);
		
	}
	
	private Entry<K,V> find(int index, K key) {
		if (table[index] != null) {
			Entry<K,V> temp = table[index];
			while (temp != null) {
				if (temp.getKey().equals(key)) {
					return temp;
				}
				temp = temp.next;
			}
		}
		return null;	
	}
	
	private void rehash() {
		Entry<K,V>[] temp = table;
		this.capacity = 2 * capacity;
		table = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
		
		for (int i = 0; i < temp.length; i++) {
			Entry<K,V> head = temp[i];
			if (head != null) {
				put(head.getKey(), head.getValue());
				while (head.next != null) {
					put(head.next.getKey(), head.next.getValue());
					head = head.next;
				}
			}		
		}		
	}
		
	
	//Nestled class
	public static class Entry<K, V> implements Map.Entry<K,V> {
		   private K key;
	       private V value;
	       private Entry<K,V> next;
	       
	       public Entry(K key, V value) {
	    	   this.key = key;
	    	   this.value = value;
	       }
	 
			@Override
			public K getKey() {
				// TODO Auto-generated method stub
				return key;
			}

			@Override
			public V getValue() {
				// TODO Auto-generated method stub
				return value;
			}

			@Override
			public V setValue(V value) {
				// TODO Auto-generated method stub
				return this.value = value;
			}
			
			@Override
			public String toString() {
				return "Key=" + getKey() + " Value=" + getValue() + ", ";
			}

		}

	
	
}
