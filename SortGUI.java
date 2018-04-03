import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
/*
 * John Payton
 * November 28, 2016
 * Dr.Comer
 * Data Structures
 * This code allows a user to import an Ascending, Descending, or Random data file and it will be organized.
 * The information is sorted in by the four types of sorting, BubbleSort, CocktailShaker, Shell Sort, and Quick Sort.
 * You can pick different sorting methods and click it and it should display the data in the text window on the code.
 * There is a impportButton that uses getFile() to access the data and then there is a 
 */
public class SortGUI implements ActionListener{
   
    private JFrame frame = new JFrame();
    

	JButton importButton = new JButton("Import Your file");

    JButton BubButton = new JButton("Bubble Sort");
    
    JButton CocktailButton = new JButton("Cocktail Shaker Sort");
    
    JButton ShellButton = new JButton("Shell Sort");
    
    JButton QuickButton = new JButton("Quick Sort");
    
    
    JTextArea display = new JTextArea(10, 30);
    
    
   
    
    public SortGUI() {

    	importButton.addActionListener(this);
    	BubButton.addActionListener(this);
    	CocktailButton.addActionListener(this);
    	ShellButton.addActionListener(this);
    	QuickButton.addActionListener(this);
        
        // the panel with the button and text
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        
        
        panel.add(BubButton);
        panel.add(CocktailButton);
        panel.add(ShellButton);
        panel.add(QuickButton);
      
       //second panel that actually goes before the first one so that you can import your files and see it in the textArea
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel2.setLayout(new GridLayout(0, 1));
        
        panel2.add(importButton);
        panel2.add(display);
        
        
        // set up the frame and display it
        frame.add(panel2, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sorting Data");
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void BubbleSort( int [ ] num )
    {
         int i;
         boolean flag = true;   
         int temp;  

         while ( flag )
         {
        	 //loop through the whole file and compare each entry with the next value
                flag= false;   
                for( i=0;  i < num.length -1;  i++ )
                {
                       if ( num[ i ] < num[i+1] )   
                       {
                               temp = num[i];               
                               num[i] = num[i+1];
                               num[i+1] = temp;
                              flag = true;          
                       } 
                       //switch the way the inequality sign goes to do ascending
                       if ( num[i] > num[i+1] )   
                       {
                               temp = num[i];               
                               num[i] = num[i+1];
                               num[i+1] = temp;
                              flag = true;              
                      } 
                } 
          } 
     } 
    
    public static void cocktailShakerSort(int [ ] num)
    {
    	boolean swapped = false;
    	do
    	{
    	int n = num.length;
    	//loop through all the int array
    	for(int i=0; i<= (n-2); i++)
    	{//make a swap if a value is larger than the proceeding one
    		if(num[i]>num[i+1])
    		{
    			int tmp = num[i];
    			num[i] = num[i+1];
    			num[i+1]= tmp;
    			swapped = true;
    		}
    	}
    	if(!swapped)//this stops the method once it is done searching through the code
    	{
    		break;
    	}
    	
    	}
    	while (swapped);
    }
    
    public static void quickSort(int[] num, int left, int right)
    {
    	int i = 0;
    	int j = 0;
    	int pivot = 0;//place holder variables that will come into play in the code
    	//check if the left is smaller than the right because if so then youll swap
    	if(left<right)
    	{
    		i = left;
    		j = (right+1);
    		pivot = num[left];
    		while(i<j)//you need to look for the value that needs to be moved
    		{
    			i = i+1;
    			while((i <= right)&& (num[i]< pivot))
    			{
    				i = i+1;
    			}
    			while((j >= left)&& (num[j]< pivot))
    			{
    				j = j-1;
    			}
    			if(i <= right)
    			{
    				int tmp = num[i];
    				num[i] = num[j];
    				num[j] = tmp;
    			}
    		}
    		int temp = num[j];
    		num[j] = num[left];
    		num[left] = num[j];
    		//once you've made a swap run it again for the next value over until it is entirely sorted
    		quickSort(num, left, j-1);
    		quickSort(num, j+1, right);
    	}
    }
    
    public static void shellsort( int [ ] num )
    {
        for( int gap = num.length / 2; gap > 0;
                     gap = gap == 2 ? 1 : (int) ( gap / 2.2 ) )
            for( int i = gap; i < num.length; i++ )
            {
                int tmp = num[ i ];
                int j = i;
  
                for(; j >= gap && tmp ==  num[ j - gap ]; j -= gap )
                    num[ j ] = num[j - gap];
                num[ j ] = tmp;
            }
    }
    
    public File getFile() throws NullPointerException//this allows the user to access whatever file they choose
    {
    	JFileChooser fileChose = new JFileChooser();
    	return fileChose.getSelectedFile();
    }
    public int[] textToNum(String str)//this takes whatever is in the display and change it to an int[]
    {
    	int [] integ;
    	BufferedReader trans = new BufferedReader(new FileReader(this.getFile()));
	    String line = str;
	    while((line = trans.readLine())!=null)
	    {
	    	display.append(Integer.parseInt(line)).append("/n");
	    	integ = display.append(Integer.parseInt(line)).append("/n");
	    }
	     trans.close();
	     return integ;
    }
    // process the button clicks
    public void actionPerformed(ActionEvent e) {
    	String tmpFile = ""+ this.getFile();
    	this.textToNum(tmpFile);
    	int[] nums = this.textToNum(tmpFile);
        if(e.getSource()== importButton)
        {
        	 
        	 display.setText(""+tmpFile);
        }
        if(e.getSource()== BubButton)
        {
        	this.BubbleSort(nums);
        }
        if(e.getSource()== CocktailButton)
        {
        	this.cocktailShakerSort(nums);
        }
        if(e.getSource()== ShellButton)
        {
        	this.shellsort(nums);
        }
        if(e.getSource()== QuickButton)
        {
        	this.quickSort(nums, nums[1], nums[nums.length-1]);
        }
    }

    // create one Frame
    public static void main(String[] args) {
        new SortGUI();
    }
}

