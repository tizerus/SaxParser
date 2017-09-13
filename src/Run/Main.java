package Run;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.saxparcer.SaxParser;
import domain.Book;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		XMLReader reader = XMLReaderFactory.createXMLReader();
		SaxParser handler = new SaxParser();
		reader.setContentHandler(handler);
		reader.parse(new InputSource("D:\\Work\\eclipse\\workspace\\SaxParser\\xmlpath\\books.xml"));
		List<Book> list = handler.getBooksList();
		for (Book book: list) {
			System.out.println(book.toString());
			
		}
	}
}