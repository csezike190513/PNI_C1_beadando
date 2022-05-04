package Beadando;

public class Pill {
    private String name;
    private String id;
    private int veny; //if 1 is veny, if 0 is not veny

    public Pill(String name, String id, int veny) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVeny() {
        return veny;
    }

    public void setVeny(int veny) {
        this.veny = veny;
    }

}
