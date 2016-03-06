
package edu.uis.csc478b.team3;

/**
 * 
 * 
 * <h3>Class:</h3> TestPair <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * Holds two files to test for plagiarism. <br>
 * 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class TestPair 
{

    /**
     *
     * @param file1
     * @param file2
     */
    public TestPair(String file1, String file2)
    {
        this.file1 = file1;
        this.file2 = file2;
    }

    /**
     *
     */
    public String file1;

    /**
     *
     */
    public String file2;
}
