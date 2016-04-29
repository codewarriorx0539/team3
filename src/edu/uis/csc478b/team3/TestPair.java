
package edu.uis.csc478b.team3;

/**
 * TestPair: holds two files to test for plagiarism.
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class TestPair 
{

    /**
     * Constructor to set files.
     * Req 14.1.0
     * 
     * @param file1
     * @param file2
     */
    public TestPair(String file1, String file2)
    {
        // Test files must be valid
        if(file1 == null || file2 == null)
        {
            throw new NullPointerException("TestPair::TestPair null file");
        }
        
        this.file1 = file1;
        this.file2 = file2;
    }

    public String file1;
    public String file2;
}
