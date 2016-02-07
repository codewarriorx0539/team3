
package edu.uis.csc478b.team3;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Plagiarism 
{
    Configuration config;
    
    /**
     * 
     * @param fileName
     * @throws JAXBException 
     */
    public void readXmlConfiguration(String fileName) throws JAXBException
    {
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance( Configuration.class );

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        config = (Configuration) jaxbUnmarshaller.unmarshal(file);
    }
    
    /**
     * 
     * @param fileName
     * @throws JAXBException 
     */
    public void writeXmlConfiguration(String fileName) throws JAXBException
    {
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(config, file);
        jaxbMarshaller.marshal(config, System.out);
    }
    

    public static void main(String[] args) 
    {
    
    }
    
}
