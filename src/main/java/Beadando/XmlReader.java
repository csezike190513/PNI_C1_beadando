package Beadando;
// https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

// https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/

public class XmlReader{

    public static ArrayList<Pill> readPillsFromXml(String filepath){
        ArrayList<Pill> pills = new ArrayList<>();

        try{

            DocumentBuilderFactory dBf = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = dBf.newDocumentBuilder();
            Document doc = dB.parse(filepath);

            Element root =doc.getDocumentElement();
/*
            System.out.println(root.getNodeName());
            System.out.println(root.getNodeType());

            System.out.println();

            System.out.println(Node.ELEMENT_NODE);
            System.out.println(Node.TEXT_NODE);

            System.out.println();

            System.out.println(root.getTextContent());//kiirja az sml-t de csak a szöveget

            System.out.println();
*/
            NodeList child = root.getChildNodes();


            int numelement = 0;
            Node node;
            for (int i = 0; i < child.getLength(); i++) {
                node = child.item(i);
                if(node.getNodeType()== Node.ELEMENT_NODE){
                    //System.out.println(node.getTextContent());
                    numelement++;
                   NodeList childnode= node.getChildNodes();
                   String name ="",id ="",veny="";
                    for (int j = 0; j < childnode.getLength(); j++) {
                        if(childnode.item(j).getNodeType()==Node.ELEMENT_NODE){
                            switch (childnode.item(j).getNodeName()) {
                                case "name" -> name = childnode.item(j).getTextContent();
                                case "id" -> id = childnode.item(j).getTextContent();
                                case "veny" -> veny = childnode.item(j).getTextContent();

                            }
                        }

                    }
                    pills.add(new Pill(name,Integer.parseInt(id),Integer.parseInt(veny)));
                }
            }
            //System.out.println("pillek száma: "+numelement+"db");

        }catch(Exception e){
            e.printStackTrace();
        }

        return pills;
    }

/*
    public static void main(String[] args) {
        ArrayList<Beadando.Pill> users =  readPillsFromXml("src/main/resources/pills.xml");
        System.out.println("------------------------------------");
        System.out.println("------------------------------------");
        System.out.println("userek száma: "+users.size()+"db");
        System.out.println();
        System.out.println(users);
    }*/

}
