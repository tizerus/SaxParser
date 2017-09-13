package by.htp.saxparcer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import domain.Author;
import domain.Book;
import domain.BookTagName;

public class SaxParser extends DefaultHandler{
	private List<Book> books = new ArrayList<Book>();
	private List<Author> authorList = new ArrayList<Author>();
	private Book book;
	private Author author;
	private StringBuilder text = new StringBuilder();
	
	public List<Book> getBooksList() {
		return books;
	}
	
	public void startDocument() throws SAXException {
		System.out.println("Parsing started...");
	}
	
	public void endDocument() throws SAXException {
		System.out.println("Parsing ended...");
	}
	
	public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
		text = new StringBuilder();
		if (qName.equals("book")) {
			book = new Book();
			book.setId(Integer.parseInt(attributes.getValue("id")));
		}
		if (qName.equals("author")) {
			author = new Author();
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		BookTagName bookTagName = BookTagName.valueOf(qName.toUpperCase().replace("-", "_"));
		switch(bookTagName) {
			case TITLE:
				book.setTitle(text.toString());
				break;
			case PAGES:
				book.setPages(Integer.parseInt(text.toString()));
				break;
			case NAME:
				System.out.println("name : " + text.toString());
				author.setName(text.toString());
				break;
			case SURNAME:
				System.out.println("surname : " + text.toString());
				author.setSurname((text.toString()));
				break;
			case DATE:
				System.out.println("date : " + text.toString());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date newDate;
				try {
					newDate = formatter.parse(text.toString());
					author.setDate(newDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case AUTHOR:
				authorList.add(author);
				System.out.println("author " +author);
				author = null;
				break;
			case BOOK:
				books.add(book);
				book = null;
				break;
			case AUTHORS:
				//List<Author> newAuthors = new ArrayList<Author>(authorList);
				book.setAuthors(authorList);
				System.out.println(authorList.size());
				authorList.clear();
				break;
		}
		
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		text.append(ch, start, length);
	}
}
