import java.io.*;


public class FileManagementSystem {


    public static TreeTable retrieveTreeTable() {
        TreeTable treeTable = new TreeTable();
        ObjectInputStream in;
        try {


            in = new ObjectInputStream(new FileInputStream("treeTable.dat"));
            Object obj;
            while ((obj = in.readObject()) !=  null) {
                treeTable = (TreeTable) obj;
            }
            in.close();
        } catch (ClassNotFoundException | IOException ioException) {}
        return treeTable;
    }





    public static void saveFile(TreeTable treeTable) {
        ObjectOutputStream treeTableFile;

        try {
            treeTableFile = new ObjectOutputStream(new FileOutputStream("treeTable.dat"));
            treeTableFile.writeObject(treeTable);
            treeTableFile.close();

        } catch (IOException  e) {
            throw new RuntimeException(e);
        }

    }


}