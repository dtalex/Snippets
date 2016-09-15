package pkg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;
import net.sf.saxon.s9api.Serializer.Property;

public class Classozza {
	public static void main(String[] args) throws SaxonApiException, SAXException, IOException, ParserConfigurationException, TransformerException {
		Processor proc = new Processor(false);
		XsltCompiler compiler = proc.newXsltCompiler();
		XsltExecutable out = compiler.compile(new StreamSource(new File("xsl.xsl")) );
		XsltTransformer transf = out.load();
		transf.setSource(new StreamSource(new File("xml.xml")) );
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Serializer serial = new Serializer();
//		serial.setOutputProperty(Property.INDENT, "yes");
		serial.setOutputStream(stream);
		transf.setDestination(serial);
		transf.transform();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document output = builder.parse(new ByteArrayInputStream(stream.toByteArray()));
	    System.out.println(getStringFromDoc(output));
//		System.out.println(new String(stream.toByteArray() ));
		
	}
	
	
	public static String getStringFromDoc(Document doc) throws TransformerException {

        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, result);
        writer.flush();
        return writer.toString();
    }
}
