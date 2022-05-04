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

import static java.lang.System.err;

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
            System.out.println(ANSI_BLACK_BACK+ANSI_BLUE+"1 - List pills\r\n2 - Add new pill\r\n"
                    + "3 - Modify a pill\r\n4 - Delete a pill\r\n0 - Exit\n"+ANSI_RESET);
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 4) {
                    err.println("Not valid option.\n");
                }
            } catch (InputMismatchException e) {
                err.println("Not valid option.\n");
                scanner.nextLine();
            }
        }

        savePillsToXml(pills, "src/main/resources/pills.xml");
    }

    private static void modifyPill(ArrayList<Pill> pills) {
        System.out.print("Enter name of pill what you want to modify: ");
        String name = scanner.nextLine();
        for (Pill pill : pills) {
            if (pill.getName().equals(name)) {

                int choice = -1;
                while (choice != 0) {
                    switch (choice) {
                        case 1 -> ModId(pills,name);
                        case 2 -> ModVeny(pills,name);
                    }
                    System.out.println(ANSI_BLACK_BACK+ANSI_BLUE+"1 - Modify Id\r\n2 - Modify Veny\r\n"
                            + "\r\n0 - Cancel\n"+ANSI_RESET);
                    try {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice < 0 || choice > 2) {
                            err.println("Not valid option.\n");
                        }
                    } catch (InputMismatchException e) {
                        err.println("Not valid option.\n");
                        scanner.nextLine();
                    }
                }
            }
        }
        err.println("There is no pill with this name.\n");
    }

    public static  void ModId(ArrayList<Pill> pills,String name){
        for (Pill pill : pills) {
            if (pill.getName().equals(name)) {
                String id = IdletezikBe(pills);
                pills.set(pills.indexOf(pill), new Pill(name,id, pill.getVeny()));
            }
        }
        System.out.println(ANSI_GREEN+"Pill is successfully modified id.\n"+ANSI_RESET);
        return;
    }

    public static  void ModVeny(ArrayList<Pill> pills,String name){
        for (Pill pill : pills) {
            if (pill.getName().equals(name)) {

                while(true){
                    System.out.print("Enter veny (1 if veny 0 if not veny): ");
                    int veny =0;
                    try{
                        veny = scanner.nextInt();
                        if(veny!=0 && veny !=1){
                            throw new HibasVeny();
                        }
                        pills.set(pills.indexOf(pill), new Pill(name,pill.getId(), veny));
                        break;
                    }catch (HibasVeny hv){
                        err.println("Hibás veny érték");
                    }catch (Exception e){
                        err.println("nem szám Veny érték");
                        scanner.nextLine();
                    }
                }
            }
        }
        System.out.println(ANSI_GREEN+"Pill is successfully modified veny.\n"+ANSI_RESET);
        return;
    }

    private static void deletePill(ArrayList<Pill> pills) {
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> DelName(pills);
                case 2 -> DelId(pills);
            }
            System.out.println(ANSI_BLACK_BACK+ANSI_BLUE+"1 - Delete about Name\r\n2 - Delete about Id\r\n"
                    + "\r\n0 - Cancel\n"+ANSI_RESET);
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 2) {
                    err.println("Not valid option.\n");
                }
            } catch (InputMismatchException e) {
                err.println("Not valid option.\n");
                scanner.nextLine();
            }
        }
    }
    public static void DelName(ArrayList<Pill> pills){
        System.out.print("Enter name of pill what you want to delete: ");
        String name = scanner.nextLine();
        for (Pill pill : pills) {
            if (pill.getName().equals(name)) {
                pills.remove(pill);
                System.out.println(ANSI_GREEN+"Pill is successfully deleted.\n"+ANSI_RESET);
                return;
            }
        }
        err.println("There is no pill with this name.\n");
    }
    public static void DelId(ArrayList<Pill> pills){
        System.out.print("Enter id of pill what you want to delete: ");
        String id = scanner.nextLine();
        for (Pill pill : pills) {
            if (pill.getId().equals(id)) {
                pills.remove(pill);
                System.out.println(ANSI_GREEN+"Pill is successfully deleted.\n"+ANSI_RESET);
                return;
            }
        }
        err.println("There is no pill with this id.\n");
    }

    private static void addNewPill(ArrayList<Pill> pills) {

        String name ="";
        while(true){

            System.out.print("Enter name of new pill: ");
            try{
                name = scanner.nextLine();
                for (int i = 0; i < pills.size(); i++) {
                    if(name.equals(pills.get(i).getName())){
                        throw new HibasName();
                    }
                }
                break;
            }catch (HibasName hn){
                err.println("Már létezik ilyen nevü pill,\n elöbb törölje a már meglévőt és kezdje ujra a folyamatot\n");
            }

        }


        String id = IdletezikBe(pills);

        int veny =0;
        while(true){

            System.out.print("Enter veny of new pill (1 if veny 0 if not veny): ");
            veny =0;
            try{
                veny = scanner.nextInt();
                scanner.nextLine();
                if(veny!=0 && veny !=1){
                    throw new HibasVeny();
                }
                break;
            }catch (HibasVeny hv){
                err.println("Hibás veny érték\n");
            }catch (InputMismatchException e){
                err.println("nem szám Veny érték\n");
                scanner.nextLine();
            }

        }


        pills.add(new Pill(name,id,veny));
    }



    private static void listPill(ArrayList<Pill> pills) {
        for(Pill pill:pills){
            if(pill.getVeny()==1){
                System.out.println(ANSI_BLACK_BACK+ANSI_YELLOW+pill.getName()+"\t"+pill.getId()+"\tVenyre kapható"+ANSI_RESET);
            }
            else if(pill.getVeny()==0){
                System.out.println(ANSI_BLACK_BACK+ANSI_CYAN+pill.getName()+"\t"+pill.getId()+"\tVeny nelkül kapható"+ANSI_RESET);
            }

        }
        System.out.println("\n");

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
                createChildElement(document, pillElement, "id", pill.getId());
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

    public static String IdletezikBe(ArrayList<Pill> pills ){
        String id ="";
        while(true){

            System.out.print("Enter id (max 10 karakter): ");
            try{
                id = scanner.nextLine();
                for (int i = 0; i < pills.size(); i++) {
                    if(id.equals(pills.get(i).getId())){
                        throw new LetezoId();
                    }
                    if(id.length()>10) {
                        throw new HibasId();
                    }
                }
                break;
            }catch (HibasId hi){
                err.println("Túl hosszu id érték\n");
            }catch (LetezoId hi){
                err.println("Már Létezik ilyen idval rendelkező Pill\n");
            }

        }
        return id;
    }

    public static final  String ANSI_BLUE = "\u001B[34m";
    public static final  String ANSI_GREEN = "\u001B[32m";
    public static final  String ANSI_YELLOW = "\u001B[33m";
    public static final  String ANSI_CYAN = "\u001B[36m";

    public static final  String ANSI_BLACK_BACK = "\u001B[40m";

    public static final  String ANSI_RESET = "\u001B[0m";

}

class HibasVeny extends Exception{

        }

class HibasId extends Exception{

}

class HibasName extends Exception{

}
class LetezoId extends Exception{

}


