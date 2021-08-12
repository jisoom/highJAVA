package kr.or.ddit.basic;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML DOM을 이용한 문서 생성 예제
 * @author PC-02
 *
 */
public class DOMCreationTest {
/*
	W3C(World Wide Web Consortium) DOM(Document Object Model) 대하여..
	DOM은 문서에 접근하는 표준 방법으로서, 이를 이용하면 플랫폼 및 개발언어에 독립적으로 
	문서의 내용, 구조 및 스타일 정보를 동적으로 핸들링(접근, 수정, 삭제)할 수 있다.
	
	W3C DOM 표준은 다음과 같이 크게 3가지로 나누어 볼 수 있다.
		
		Core DOM - 모든 문서 타입을 위한 핵심 표준 모델(API)
		XML DOM - XML문서를 위한 표준 모델
		HTML DOM - HTML문서를 위한 표준 모델
 */
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		//XML 문서를 생성하기 위한 DocumentBuilder 객체 생성하기
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //도큐먼트 팩토리를 통해↓
		DocumentBuilder documentBuilder = factory.newDocumentBuilder(); //도큐먼트 빌더를 생성
		
		//Document 객체 생성(w3c로 되있는 거 선택)
		Document document = documentBuilder.newDocument(); //도큐먼트 빌더를 통해 도큐먼트 생성
		
		//root 엘리먼트 생성(w3c로 되있는 거 선택)
		Element root = document.createElement("data"); //도큐먼트를 통해 루트 생성
		
		//booklist 엘리먼트 생성
		Element booklist = document.createElement("booklist"); //도큐먼트를 통해 북리스트 생성
		
		//book 엘리먼트 생성 및 속성값 설정하기
		Element book = document.createElement("book"); //도큐먼트를 통해 북 생성
		book.setAttribute("isbn", "B001"); //북 속성
		book.setAttribute("kind", "JAVA"); //북 속성
		
		//자식 엘리먼트 생성 및 설정
		Element title = document.createElement("title");
		title.setTextContent("자바초급"); //title태그안의 글자
		Element author = document.createElement("author");
		author.setTextContent("이순신"); //author태그안의 글자
		Element price = document.createElement("price");
		price.setTextContent("25000"); //price태그안의 글자
		
		//book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		booklist.appendChild(book);
		//-------------------------------------------------------------------------------
		
		// book 엘리먼트 생성 및 속성 값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B002");
		book.setAttribute("kind", "JAVA");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("자바고급");
		author = document.createElement("author");
		author.setTextContent("홍길동");
		price = document.createElement("price");
		price.setTextContent("30000");
		
		//book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		booklist.appendChild(book);
		//-------------------------------------------------------------------------------
		
		// book 엘리먼트 생성 및 속성 값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B003");
		book.setAttribute("kind", "DB");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("디비설계");
		author = document.createElement("author");
		author.setTextContent("이몽룡");
		price = document.createElement("price");
		price.setTextContent("30000");
		
		//book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		booklist.appendChild(book);		
		//-------------------------------------------------------------------------------
		
		// book 엘리먼트 생성 및 속성 값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B004");
		book.setAttribute("kind", "DB");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("디비구현");
		author = document.createElement("author");
		author.setTextContent("강감찬");
		price = document.createElement("price");
		price.setTextContent("40000");
		
		//book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		booklist.appendChild(book);			
		//-------------------------------------------------------------------------------
		
		//루트 앨리먼트에 booklist 추가하기
		root.appendChild(booklist);
		
		//도큐먼트에 root 엘리먼트 추가하기
		document.appendChild(root);
		
		//XML 문자열로 변환하기
		ByteArrayOutputStream boas = new ByteArrayOutputStream();
		
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(boas);
		
		TransformerFactory transformFactory = TransformerFactory.newInstance();
		Transformer transformer = transformFactory.newTransformer();
		
		//출력 인코딩 설정하기
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		//들여쓰기 설정(공백 크기 : 4)
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); //들여쓰기 4칸
		
		transformer.transform(source, result);
		
		try(FileOutputStream fos = new FileOutputStream("./src/new_book.xml")) {
			
			fos.write(boas.toByteArray());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}