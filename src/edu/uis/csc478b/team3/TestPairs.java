
package edu.uis.csc478b.team3;

import java.util.ArrayList;

public class TestPairs 
{
    public ArrayList< TestPair > createPairs( ArrayList< String > files )
    {
        ArrayList< TestPair > pairs = new ArrayList< >();
        
        for(int i = 0; i < files.size(); i++)
        {
            for(int j = i + 1; j < files.size(); j++ )
            {
                pairs.add(new TestPair( files.get(i), files.get(j) ));
            }
        }
        
        return pairs;
    }
    
    public static void main(String[] args) 
    {
        ArrayList< String > files = new ArrayList< >();
        TestPairs testPairs = new TestPairs();
        
        files.add("A");
        files.add("B");
        files.add("C");
        files.add("D");
        
        ArrayList< TestPair > pairs = testPairs.createPairs(files);
        
        for(TestPair tp : pairs)
        {
            System.out.println( tp.file1 + " " + tp.file2);
        }
    }
}
