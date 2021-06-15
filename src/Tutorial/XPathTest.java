package Tutorial;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class XPathTest {

    public static void main(String[] args) throws Exception {

        // Build DOM

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("src/Tutorial/inventory.xml");

        //Create XPath

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        System.out.println("Get book titles written after 2001");

        // 1) Get book titles written after 2001
        XPathExpression expr = xpath.compile("//book[@year>2001]/title/text()");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        System.out.println("Get book titles added in last node");
        // 2) Get book titles added in last node

        expr = xpath.compile("//book[last()]/title/text()");
        result = expr.evaluate(doc, XPathConstants.NODESET);
        nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        System.out.println("Get book titles added in first node");
        // 3) Get book titles added in first node

        expr = xpath.compile("//book[1]/title/text()");
        result = expr.evaluate(doc, XPathConstants.NODESET);
        nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        System.out.println("Get all writers");
        // 4) Get all the writers
        expr = xpath.compile("//book/author/text()");
        result = expr.evaluate(doc, XPathConstants.NODESET);
        nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        System.out.println("Count all book titles");

        // 5) Count all books titles
        expr = xpath.compile("count(//book/title)");
        result = expr.evaluate(doc, XPathConstants.NUMBER);
        Double count = (Double) result;
        System.out.println(count.intValue());

        System.out.println("Get book titles with writer name start with Neal");

        // 6) Get book titles with writer name starting with Neal
        expr = xpath.compile("//book[starts-with(author, 'Neal')]");
        result = expr.evaluate(doc, XPathConstants.NODESET);
        nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getChildNodes()
                    .item(1).getTextContent());
        }

        System.out.println("Reading comment node");

        // 7) Reading comment node
        expr = xpath.compile("//inventory/comment()");
        result = expr.evaluate(doc, XPathConstants.STRING);
        String comment = (String) result;
        System.out.println(comment);


    }
}
