import Tree.Tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class TreeTable implements Serializable {

    private Tree<Student>[] treeTable;


    public TreeTable() {
        treeTable = new Tree[25];
        for (int i = 0; i < 25; i++) {
            treeTable[i] = new Tree();
        }
    }

    public int hashFunc(int key) {
        key = Integer.parseInt(String.valueOf(key).substring(0, 4)) % 25;
        return key;
    }

    public void insert(Student student) {
        int index = hashFunc(student.id);
        if (getStudent(student.id) == null)
            treeTable[index].insert(student.id, student);
        else System.out.println("A student with id " + student.id + " already exists");
    }

    public Student find(int id) {
        int index = hashFunc(id);
        return treeTable[index].search(id);
    }


    public Student update(int id) {
        int index = hashFunc(id);
        if (getStudent(id) == null) return null;
        else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the new student Name: ");
            getStudent(id).setName(scanner.nextLine());
            System.out.println("Enter the new student address: ");
            getStudent(id).setAddress(scanner.nextLine());
            System.out.println("Enter the new student GPA: ");
            getStudent(id).setGPA(scanner.nextDouble());
            return treeTable[index].search(id);
        }
    }


    public boolean remove(int id) {
        int index = hashFunc(id);
        if (getStudent(id) == null) return false;
        else {
            treeTable[index].delete(id);
            return true;
        }
    }

    public ArrayList<Student> studentWithGPA(double gpa) {
        ArrayList<Student> newStudents = new ArrayList<Student>();
        for (Tree<Student> tree : treeTable) {
            ArrayList<Student> students = tree.getAllStudents();
            for (Student student : students) {
                if (student.getGPA() < gpa) newStudents.add(student);
            }

        }
        if(!newStudents.isEmpty()) return newStudents;
        else return null;
    }

    public Student highestGPA() {
        Student highestStudent = new Student();
        for (Tree<Student> tree : treeTable) {
            ArrayList<Student> students = tree.getAllStudents();
            for (Student student : students) {
                if (student.getGPA() > highestStudent.getGPA()) highestStudent = student;
            }

        }
        if(highestStudent.getId() != 0) return highestStudent;
        else return null;
    }

    public Student highestGPA(int year) {
        Student highestStudent = new Student();
        int index = hashFunc(year);
        ArrayList<Student> students = treeTable[index].getAllStudents();
        for (Student student : students) {
            if (student.getGPA() > highestStudent.getGPA()) highestStudent = student;
            ;
        }
        if(highestStudent.getId() != 0) return highestStudent;
        else return null;
    }


    public void printStudent(int id) {
        int index = hashFunc(id);
        if (treeTable[index].getAllStudents().isEmpty()) System.out.println("There are no students in this year.");
        else {
            for(Student student : treeTable[index].getAllStudents()) {
                System.out.println(student);
            }
        };
    }

    public void printAll() {
        for (int i = 2023; i >= 1999; i--) {
            int index = hashFunc(i);
            treeTable[index].traverse(1);
        }
    }

    public int numberStudents() {
        int count = 0;
        for (int i = 2023; i >= 1999; i--) {
            int index = hashFunc(i);
            count += treeTable[index].count();
        }
        return count;
    }

    public int numberStudents(int id) {
        int index = hashFunc(id);
        return treeTable[index].count();
    }
    public Student getStudent(int id) {
        int index = hashFunc(id);
        return treeTable[index].search(id);
    }


    public void main() {

        String choice = "0";
        Scanner input = new Scanner(System.in);
        while (!choice.equalsIgnoreCase("-1")) {

            System.out.println("----------------------------------------------"
                    + "\nChoose your choice:\n"
                    + "1- Add a student\n"
                    + "2- Find a student\n"
                    + "3- Update a student\n"
                    + "4- Remove a Student\n"
                    + "5- Print a student in a specific year\n"
                    + "6- Print all students\n"
                    + "7- Print students with GPA lower than\n"
                    + "8- Prints the student with the highest GPA\n"
                    + "9- Prints the student with the highest GPA within a specific year\n"
                    + "10- Counts the number of all students\n"
                    + "11- Counts the number of students in a specific year\n"
                    + "----------------------------------------------");

            choice = input.next();

            if (choice.equalsIgnoreCase("1")) {
                System.out.println("Please enter the details of the student");
                try {
                    System.out.println("ID: ");
                    int id = input.nextInt();
                    System.out.println("Name: ");
                    String name = input.next();
                    System.out.println("Address");
                    String address = input.next();
                    System.out.println("GPA: ");
                    double gpa = input.nextDouble();
                    if(gpa >= 1 && gpa <= 4 && hashFunc(id) >= 0 && hashFunc(id) < 25) {
                        insert(new Student(id, name, address, gpa));
                        System.out.println("Student has been added.");
                    }
                    else System.out.println("Gpa must be between 1 and 4 and id must start between 1999 and 2023");

                } catch (InputMismatchException e) {
                    System.out.println("ID must be integer and GPA must be double");

                }
            } else if (choice.equalsIgnoreCase("2")) {
                try {
                    System.out.println("Please enter the id of the student");
                    int id = input.nextInt();
                    if(hashFunc(id) >= 0 && hashFunc(id) < 25)
                    System.out.println(find(id));
                    else System.out.println("id must start between 1999 and 2023");
                } catch (InputMismatchException e) {
                    System.out.println("ID must be integer");
                }

            } else if (choice.equalsIgnoreCase("3")) {
                try {
                    System.out.println("Please enter the id of the student");
                    int id = input.nextInt();
                    if(hashFunc(id) >= 0 && hashFunc(id) < 25)
                    System.out.println(update(id));
                    else System.out.println("id must start between 1999 and 2023");

                } catch (InputMismatchException e) {
                    System.out.println("ID must be integer");
                }
            } else if (choice.equalsIgnoreCase("4")) {
                try {
                    System.out.println("Please enter the id of the student");
                    int id = input.nextInt();
                    if(hashFunc(id) >= 0 && hashFunc(id) < 25)
                        System.out.println(remove(id));
                    else System.out.println("id must start between 1999 and 2023");
                } catch (InputMismatchException e) {
                    System.out.println("ID must be integer");
                }
            }
            else if (choice.equalsIgnoreCase("5")) {
                try {

                    System.out.println("Please enter the year");
                    int year = input.nextInt();
                    if(year >= 1999 &&  year <= 2023)
                        printStudent(year);
                    else
                        System.out.println("Year must be between 1999 and 2023");
                } catch (InputMismatchException e) {
                    System.out.println("year must be integer");
                }
            }
            else if (choice.equalsIgnoreCase("6")) {
                printAll();
            }
            else if (choice.equalsIgnoreCase("7")) {
                try {

                    System.out.println("Please enter the GPA");
                    double GPA = input.nextDouble();
                    if(GPA >=0 && GPA <=4) {
                        ArrayList<Student> students = studentWithGPA(GPA);
                        if (students != null) {
                            for (Student student : students) {
                                System.out.println(student);
                            }
                        } else {
                            System.out.println("There are no students lower than GPA " + GPA);
                        }
                    } else{
                        System.out.println("GPA must be between 0 and 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("gpa must be double");
                }
            }
            else if (choice.equalsIgnoreCase("8")) {
                if (highestGPA() != null)
                    System.out.println(highestGPA());
                else
                    System.out.println("There are no students in the TreeTable");
            }
            else if (choice.equalsIgnoreCase("9")) {
                try {
                    System.out.println("Please enter the year");
                    int year = input.nextInt();
                    if(year >= 1999 &&  year <= 2023)
                        if (highestGPA(year) != null)
                            System.out.println(highestGPA(year));
                        else
                            System.out.println("There are no students in that year");
                    else
                        System.out.println("The year must be between 1999 and 2023");
                } catch (InputMismatchException e) {
                    System.out.println("year must be integer");
                }
            }
            else if (choice.equalsIgnoreCase("10")) {
                System.out.println(numberStudents());
            }
            else if (choice.equalsIgnoreCase("11")) {
                try {
                    System.out.println("Please enter the year");
                    int year = input.nextInt();
                    if(year >= 1999 &&  year <= 2023)
                        System.out.println(numberStudents(year));
                    else
                        System.out.println("The year must be between 1999 and 2023");
                } catch (InputMismatchException e) {
                    System.out.println("Year must be integer");
                }
            }else if(choice.equalsIgnoreCase("-1")){
                break;
            }else {
                System.out.println("Wrong choice, please choose one of the following");
            }
        }
    }
}