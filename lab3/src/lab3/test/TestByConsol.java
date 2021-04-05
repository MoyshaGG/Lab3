package lab3.test;
import lab3.model.*;
import lab3.store.ProductStore;
import lab3.store.WoodDirectory;

import java.io.*;
import java.util.Date;
import java.util.Scanner;


public class TestByConsol {
    private WoodDirectory UserDirecotry = new WoodDirectory();
    private ProductStore UserStore = new ProductStore();
    Scanner scan = new Scanner (System.in);
    private final BufferedWriter bw = new BufferedWriter(new FileWriter("Log.TXT"));
    /////////////////////////////
    boolean doing = true;

    public TestByConsol() throws IOException {
    }
    ////////////////////////////

    public void startAppConsole() throws Exception {
        Name();

        System.out.println();
        UserDirecotry.add(new Wood(0, "Сосна",0.4f));
        UserDirecotry.add(new Wood(1,"Соснодуб",0.3f));
        UserDirecotry.add(new Wood(2,"Ялинка",0.15f));
        
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
                bw.close();
            }catch (IOException e){
                e.printStackTrace();

        }
    }

    private void addWaste() throws Exception {
        System.out.println("Write quantity of waste");
        float weight = scan.nextFloat();
        UserStore.add(new Waste(weight));
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
            UserStore.add(new Cylinder(UserDirecotry.get(id),length,diameter));
            System.out.println(UserStore);
            id = -1;

    }

    private void addWood()
    {
        System.out.println(UserDirecotry);
        System.out.println(" Write type wood");
        String name = scan.nextLine();
        int id = UserDirecotry.getArr().length;
        System.out.println("Write density wood");
        float density = scan.nextFloat();
        Wood newWood = new Wood ( id, name, density);
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
        UserStore.add(new Timber(UserDirecotry.get(id),length,height,width));

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
        File f = new File("Report.txt");
        if (f != null){
            System.out.println(f.getAbsolutePath());
        }
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(UserDirecotry.toString());
            writer.newLine();
            writer.write(UserStore.toString());
            writer.close();
            System.out.println("File Saved");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
