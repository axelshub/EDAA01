package textproc;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class BookReaderController {
	
	public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
}
    private void createWindow(GeneralWordCounter counter, String title,
                                                int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        
     // pane är en behållarkomponent till vilken de övriga komponenterna (listvy, knappar etc.) ska läggas till.
     
     SortedListModel<Map.Entry<String, Integer>> slm = new SortedListModel<>(counter.getWordList());
     JList list = new JList(slm);
     JScrollPane scrollPane = new JScrollPane(list);
     
     frame.add(scrollPane);
     
     JPanel southPanel = new JPanel();
     JRadioButton a = new JRadioButton("Alphabetic", true);
	 a.addActionListener(event -> slm.sort((map1, map2) -> map1.getKey().compareTo(map2.getKey())));

     JRadioButton f = new JRadioButton("Frequency", false);
     f.addActionListener(event -> slm.sort((map1, map2) -> map2.getValue() - map1.getValue()));
     
     //Groups the buttons so only one can be selected at once
     ButtonGroup group = new ButtonGroup();
     group.add(a);
     group.add(f);
     
     JTextField tf = new JTextField("", 10);
     JButton search = new JButton("Find");
     frame.getRootPane().setDefaultButton(search);
     search.addActionListener(event -> {
    	 boolean contains = false;
    	 for(int i = 0; i<slm.getSize(); i++) {
    		 if(slm.getElementAt(i).getKey().equals(tf.getText().toLowerCase().trim())) {
    			 list.ensureIndexIsVisible(i);
    			 list.setSelectedIndex(i);
    			 contains = true;
    		 }
    	 }
    	 if(contains == false) {
    		 JOptionPane optionPane = new JOptionPane("The word was not found", JOptionPane.ERROR_MESSAGE);    
    		 JDialog dialog = optionPane.createDialog("Error");
    		 dialog.setAlwaysOnTop(true);
    		 dialog.setVisible(true);
    		 
    	 }
    	 
     });
     
     southPanel.add(a);
     southPanel.add(f);
     southPanel.add(tf);
     southPanel.add(search);
     pane.add(southPanel, BorderLayout.SOUTH);
     
     
     
	
     frame.pack();
     frame.setVisible(true);
        
    }
}
