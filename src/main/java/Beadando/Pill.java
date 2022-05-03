package Beadando;

public class Pill {
    private String name;
    private int id;
    private int veny; //if 1 is veny, if 0 is not veny
    /*
    public Pill(String name, int id, int veny) {
        this(name, id, veny);
    }

    public Pill(String name, int id,int veny) {
        this.name = name;
        this.id = id;
        this.veny = veny;

    }*/

    public Pill(String name, int id, int veny) {
        this.name = name;
        this.id = id;
        this.veny = veny;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVeny() {
        return veny;
    }

    public void setVeny(int veny) {
        this.veny = veny;
    }

}
