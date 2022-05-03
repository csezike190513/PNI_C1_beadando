package Beadando;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import Beadando.Pill;
import Beadando.XmlReader;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;

public class Pills {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Pill> pills = XmlReader.readPillsFromXml("src/main/resources/pills.xml");
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> listPill(pills);
                case 2 -> addNewPill(pills);
                case 3 -> modifyPill(pills);
                case 4 -> deletePill(pills);
            }
            System.out.println("1 - List pills\r\n2 - Add new pill\r\n"
                    + "3 - Modify a pill\r\n4 - Delete a pill\r\n0 - Exit");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 4) {
                    System.out.println("Not valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid option.");
                scanner.nextLine();
            }
        }

        savePillsToXml(pills, "src/main/resources/pills.xml");
    }

    private static void modifyPill(ArrayList<Pill> pills) {
        System.out.print("Enter name of user what you want to modify: ");
        String name = scanner.nextLine();
        for (Pill pill : pills) {
            if (pill.getName().equals(name)) {

                System.out.print("Enter id of new pill: ");
                int id = scanner.nextInt();
                pills.set(pills.indexOf(pill), new Pill(name,id, pill.getVeny()));
                System.out.println("Pill is successfully modified.");
                return;
            }
        }
        System.out.println("There is no pill with this name.");
    }

    private static void deletePill(ArrayList<Pill> pills) {
        System.out.print("Enter name of user what you want to delete: ");
        String name = scanner.nextLine();
        for (Pill pill : pills) {
            if (pill.getName().equals(name)) {
                pills.remove(pill);
                System.out.println("Pill is successfully deleted.");
                return;
            }
        }
        System.out.println("There is no pill with this name.");
    }

    private static void addNewPill(ArrayList<Pill> pills) {
        System.out.print("Enter name of new pill: ");
        String name = scanner.nextLine();

        System.out.print("Enter id of new pilll: ");
        int id = scanner.nextInt();

        int veny =0;
        while(true){

            System.out.print("Enter veny of new pilll (1 if veny 0 if not veny): ");
            try{
                veny = scanner.nextInt();
                if(veny!=0 && veny !=1){
                    throw new HibasVeny();
                }
                break;
            }catch (HibasVeny hv){
                System.out.println("Hibás veny érték");
            }

        }


        pills.add(new Pill(name,id,veny));
    }



    private static void listPill(ArrayList<Pill> pills) {
        for(Pill pill:pills){
            if(pill.getVeny()==1){
                System.out.println(pill.getName()+", "+pill.getId()+", Venyre kapható");
            }
            else if(pill.getVeny()==0){
                System.out.println(pill.getName()+", "+pill.getId()+", Veny nelkül kapható");
            }

        }

    }

    public static void savePillsToXml(ArrayList<Pill> pills, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("pills");
            document.appendChild(rootElement);

            for (Pill pill : pills) {
                Element pillElement = document.createElement("pills");
                rootElement.appendChild(pillElement);
                createChildElement(document, pillElement, "name", pill.getName());
                createChildElement(document, pillElement, "id",
                        String.valueOf(pill.getId()));
                createChildElement(document, pillElement, "veny",  String.valueOf(pill.getVeny()));

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createChildElement(Document document, Element parent,
                                           String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

}

class HibasVeny extends Exception{

        }