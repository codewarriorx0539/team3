
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.FileProcessor;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import java.util.ArrayList;

public abstract class Filter 
{
    protected String masterFile;
    protected String suspectFile;
                        
    protected ArrayList<ArrayList<String> > mWords;
    protected ArrayList<ArrayList<String> > sWords;
    protected ArrayList<String> mSentences;
    protected ArrayList<String> sSentences;
    
    protected FileProcessor fileProcessor;
    
    /*
        String mText = fileProcessor.fileAsAString(masterFile);
                fileProcessor.getSentences(mText, mSentences);
                fileProcessor.getWordsOfSentences(mText, mWords);

                String sText = fileProcessor.fileAsAString(suspectFile);
                fileProcessor.getSentences(sText, sSentences);
                fileProcessor.getWordsOfSentences(sText, sWords);
    */
    
    
    
    public Filter()
    {
        fileProcessor = new FileProcessor();
        
        mWords = new ArrayList<ArrayList<String> >();
        sWords = new ArrayList<ArrayList<String> >();
        mSentences = new ArrayList<String>();
        sSentences = new ArrayList<String>();
        
    }
    
    public Filter( PlagiarismTest config )
    {
        fileProcessor = new FileProcessor();
        
        this.masterFile = config.getMasterFile();
        this.suspectFile = config.getSuspectFile();
        
        mWords = new ArrayList<ArrayList<String> >();
        sWords = new ArrayList<ArrayList<String> >();
        mSentences = new ArrayList<String>();
        sSentences = new ArrayList<String>();
    }
    
    abstract public String runPlagiarismTest();

    public ArrayList<ArrayList<String>> getmWords() {
        return mWords;
    }

    public void setmWords(ArrayList<ArrayList<String>> mWords) {
        this.mWords = mWords;
    }

    public ArrayList<ArrayList<String>> getsWords() {
        return sWords;
    }

    public void setsWords(ArrayList<ArrayList<String>> sWords) {
        this.sWords = sWords;
    }

    public ArrayList<String> getmSentences() {
        return mSentences;
    }

    public void setmSentences(ArrayList<String> mSentences) {
        this.mSentences = mSentences;
    }

    public ArrayList<String> getsSentences() {
        return sSentences;
    }

    public void setsSentences(ArrayList<String> sSentences) {
        this.sSentences = sSentences;
    }
    
    
 
}
