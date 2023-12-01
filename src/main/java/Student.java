import java.io.Serializable;

public class Student implements Serializable {
    int id;
    String name;
    String address;
    double GPA;

    public Student(int id, String name, String address, double GPA) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.GPA = GPA;
    }

    public Student() {
        this.id = 0;
        this.name = "";
        this.address = "";
        this.GPA = 0;
    }

    public String toString(){
        return "Name: " + this.name + ", Address: " + this.address + ", GPA: " + this.GPA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }
}
