
package edu.uis.csc478b.team3;

import edu.uis.csc478b.team3.config.ClassFiles;
import edu.uis.csc478b.team3.config.Configuration;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import edu.uis.csc478b.team3.filters.PlagiarismFilter;
import edu.uis.csc478b.team3.filters.SentenceSimilarity;
import edu.uis.csc478b.team3.filters.Similarity;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class XmlConfig 
{
    public ClassFiles readXmlClassFiles(String fileName ) throws JAXBException
    {
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance( ClassFiles.class  );
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        
        return (ClassFiles) jaxbUnmarshaller.unmarshal(file);
    }
    
    public void writeXmlClassFiles(String fileName, ClassFiles classFiles) throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance( ClassFiles.class );
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
        File file = new File(fileName);
        jaxbMarshaller.marshal(classFiles, file);
        jaxbMarshaller.marshal(classFiles, System.out);
    }
    
    public Configuration readXmlConfiguration(String fileName , Class[] classes) throws JAXBException
    {
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance( classes, null  );
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        
        return (Configuration) jaxbUnmarshaller.unmarshal(file);
    }
       
    public void writeXmlConfiguration(String fileName, Configuration configuration, Class[] classes) throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance( classes, null );
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
        File file = new File(fileName);
        jaxbMarshaller.marshal(configuration, file);
        jaxbMarshaller.marshal(configuration, System.out);
    }
    
    public void createSampleProfile() throws JAXBException, Exception
    {
        // Programmically set configuration
        Configuration configuration = new Configuration();
        ArrayList< PlagiarismTest > list = new ArrayList<>();
        PlagiarismTest pt = new PlagiarismTest();
        ArrayList< String > files = new ArrayList<>();

        files.add("master.txt");
        files.add("suspect.txt");
        files.add("other.txt");

        
        SentenceSimilarity sent = new SentenceSimilarity();
        ArrayList< PlagiarismFilter > sentenceFilters = new ArrayList< >();
        sentenceFilters.add(sent);

        Similarity wfreq = new Similarity( );
        ArrayList< PlagiarismFilter > wordFilters = new ArrayList< >();
        wordFilters.add(wfreq);

        pt.setFiles(files);
        pt.setSentenceFilters(sentenceFilters);
        pt.setWordFilters(wordFilters);

        list.add(pt);
        configuration.setTests(list);
        
        Class[] classes = new Class[3];
        
        classes[0] = Configuration.class;
        classes[1] = SentenceSimilarity.class;
        classes[2] = Similarity.class;
        
        writeXmlClassFiles("classes.xml", new ClassFiles(classes) );
        writeXmlConfiguration("configuration.xml", configuration, classes);
    }
    
    
    
    

}
