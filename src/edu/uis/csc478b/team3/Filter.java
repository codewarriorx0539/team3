
package edu.uis.csc478b.team3;

import java.util.ArrayList;

public abstract class Filter 
{
    ArrayList<ArrayList<String> > mWords;
    ArrayList<ArrayList<String> > sWords;
    ArrayList<String> mSentences;
    ArrayList<String> sSentences;
    
    public Filter( ArrayList<ArrayList<String> > mWords, ArrayList<ArrayList<String> > sWords, ArrayList<String> mSentences , ArrayList<String> sSentences )
    {
        this.mWords = mWords;
        this.sWords = sWords;
        this.mSentences = mSentences;
        this.sSentences = sSentences;
    }
    
    abstract public boolean runPlagiarismTest();
 
}
