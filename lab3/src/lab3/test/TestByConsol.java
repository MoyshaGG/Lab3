package lab3.test;
import lab3.model.*;
import lab3.store.ProductStore;
import lab3.store.WoodDirectory;
import javax.swing.filechooser.FileFilter;
import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;


public class TestByConsol implements java.io.Serializable {
    private WoodDirectory UserDirecotry = new WoodDirectory();
    private ProductStore UserStore = new ProductStore();
    Scanner scan = new Scanner (System.in);
    private final BufferedWriter bw = new BufferedWriter(new FileWriter("Log.TXT ",true));
    /////////////////////////////
    boolean doing = true;    public TestByConsol() throws IOException {
    }
    ////////////////////////////

    public void startAppConsole() throws Exception {
        Name();

        System.out.println();
        UserDirecotry.add(new Wood(0, "Мавподуб",0.4f));
        UserDirecotry.add(new Wood(1,"Мертвий Слоник",0.3f));
        UserDirecotry.add(new Wood(2,"Ялинковий Злодій",0.15f));
        
        while(doing)
        {
            System.out.println("( Add Wood )(End) ( Add Timber ( Calculate ) ( Add Cylinder ) (Add Waste )( Save ) ( ExportToTxt )");
            switch(scan.nextLine())
            {
                case "Add Wood":
                    addWood();
                    break;
                case "Add Timber":
                    addTimber();
                    break;
                case "Calculate":
                    addCalc();
                    break;
                case "End":
                end();
                bw.close();
                    break;
                case "Add Cylinder":
                    addCylinder();
                    break;
                case "Add Waste":
                    addWaste();
                    break;
                case "Save":
                    Serialization();
                    Deserialization();
                    break;
                case "ExportToTxt":
                    ToTXTFile();
                    break;

            }

        }
    }

    private void Name() {
            System.out.println();
            System.out.println("Type your name");
            String s = scan.nextLine();
            try{
                bw.write((new Date())+ " " + s + " has been here");
                bw.newLine();
            }
            catch (IOException e){
                e.printStackTrace();
            }
   }

    private void addWaste() throws Exception {
        System.out.println("Write quantity of waste");
        float weight = scan.nextFloat();
        try {
            Waste w = new Waste(weight);
            UserStore.add(w);
            WriteString(w.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ввід продуктів", JOptionPane.ERROR_MESSAGE);
            addWaste();
        }
        System.out.println(UserStore);
    }

    private void addCylinder() throws Exception {
        System.out.println(UserDirecotry);
        System.out.println("Write id wood:");
            int id = scan.nextInt();
            System.out.println("Write length cylinder");
            float length = scan.nextFloat();
            System.out.println("Write diameter cylinder");
            float diameter = scan.nextFloat();
        try {
            Cylinder c = new Cylinder(UserDirecotry.get(id), length, diameter);
            UserStore.add(c);
            WriteString(c.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Введення продуктів", JOptionPane.ERROR_MESSAGE);
            addCylinder();
        }
            System.out.println(UserStore);
            id = -1;

    }

    private void addWood(  )
    {

        System.out.println(UserDirecotry);
        System.out.println(" Write type wood");

        String name = scan.nextLine();
        int id = UserDirecotry.getArr().length;
        System.out.println("Write density wood");
        float density = scan.nextFloat();
        Wood newWood = new Wood(id,name,density);
        if (UserDirecotry.add(newWood)) {
            WriteString(newWood.toString());
        }
        else{
            System.out.println(newWood + " id вже існує\n");
        }

        UserDirecotry.add(newWood);

    }

    private void addTimber() throws Exception {
        System.out.println(UserDirecotry);
        System.out.println("Write id wood ");
        int id = scan.nextInt();
        System.out.println("Write length Timber ");
        float length = scan.nextFloat();
        System.out.println("Write high Timber");
        float height = scan.nextFloat();
        System.out.println("Write width Timber");
        float width = scan.nextFloat();
        try {
            Timber t = new Timber(UserDirecotry.get(id), length, height, width);
            UserStore.add(t);
            WriteString(t.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Введення продуктів", JOptionPane.ERROR_MESSAGE);
            addTimber();
        }

        System.out.println(UserStore);
        id =-1;
    }

    private void addCalc()
    {
     float fullWeight = 0;
     for (Object timber : UserStore.getArr())
     {
         fullWeight+=((IWeight)timber).weight();
     }
     System.out.println(fullWeight);

    }
    private void end(){
        doing = false;
    }
    private void Serialization (){
        //Збереження WoodDirectory у файлі
        File f = new File("wd.object");
        try{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(UserDirecotry);
            oos.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
        //Збереження ProductStore у файлі
        File ff = new File("ps.object");
        try{
            FileOutputStream fos1 = new FileOutputStream(ff);
            ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
            oos1.writeObject(UserStore);
            oos1.close();
        }catch (Exception e ) {
            e.printStackTrace();
        }

    }
    private void Deserialization () {
        //Відновлення WoodDirecroty з файлу
        File f = new File("wd.object");
        try{
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            UserDirecotry = (WoodDirectory) ois.readObject();
            ois.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        //Виведення WoodDirecroty на консоль
        if (UserDirecotry != null){
            for (Object w: UserDirecotry.getArr())
                System.out.println(w.toString());
        }

        //Відновлення ProductStore з файлу
        File ff = new File("ps.object");
        try{
            FileInputStream fis1 = new FileInputStream(ff);
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            UserStore = (ProductStore) ois1.readObject();
            ois1.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        //Виведення ProductStore на консоль
        if (UserStore != null){
            for (Object w: UserStore.getArr())
                System.out.println(w.toString());
        }
    }
    private void ToTXTFile(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFileChooser dialog = new JFileChooser();
        dialog.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f!= null){
                    //Відображати усі папки та файли .txt
                    return f.isDirectory() || f.toString().endsWith(".txt");
                }
                return false;
            }

            @Override
            public String getDescription() {
                return " files type .txt";
            }
        });
        dialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        dialog.setDialogTitle("Choose your files and folders");
        dialog.setApproveButtonText("Open");
        dialog.setMultiSelectionEnabled(true);
        dialog.showSaveDialog(null);
        File [] ff= dialog.getSelectedFiles();
        if(ff!=null) {
            for (File f : ff) {
                System.out.println(f.getAbsolutePath());
                //формування текстового файлу з каталогом деревини та виробів
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                    writer.write(UserDirecotry.toString());
                    writer.newLine();
                    writer.write(UserStore.toString());
                    writer.close();
                    System.out.println("File Saved");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
         }
     }

    private void WriteString(String s){
        try {
            bw.write((new Date()) + " added " + s);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }














































}
