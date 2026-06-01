package modelo;

public class Alergenos {
    // Atributos de la clase Alergeno
    private int id;
    private String grupo;

    // Constructor de la clase
    public Alergenos(int id, String grupo) {
        this.id = id;
        this.grupo = grupo;
    }

    //Getters y Setters de los atributos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    // ToString editado para que coincida con los métodos del objeto
    @Override
    public String toString() {
        return "Alergenos{" +
                "id=" + id +
                ", grupo='" + grupo + '\'' +
                '}';
    }
}
