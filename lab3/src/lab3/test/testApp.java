package lab3.test;

import lab3.model.*;
import lab3.store.ProductStore;
import lab3.store.WoodDirectory;

import javax.swing.*;

public class testApp {

    private WoodDirectory wd = new WoodDirectory();
    //Каталог для брусів
    private ProductStore ps = new ProductStore();

    public void startApp() throws Exception {

        wd.add(new Wood(0,"Мавпобаобаб",0.3f));
        wd.add(new Wood(1,"Куркодерево",0.6f));
        wd.add(new Wood(2,"Птаходуб",0.9f));

      try {
    ps.add(new Timber(wd.get(0), 5f, 0.5f, 0.4f));
     }
      catch (Exception e)
     {
    JOptionPane.showMessageDialog(null, e.getMessage(),
            "Введення продуктiв" , JOptionPane.ERROR_MESSAGE);
     }
        try {
            ps.add(new Timber(wd.get(1),10f,0.6f,0.7f ));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Введення продуктiв" , JOptionPane.ERROR_MESSAGE);
        }
        try {
            ps.add(new Cylinder(wd.get(2),3f, 0.3f));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Введення продуктiв" , JOptionPane.ERROR_MESSAGE);
        }
        try {
            ps.add(new Waste(15f));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Введення продуктiв" , JOptionPane.ERROR_MESSAGE);
        }



        //Друкуємо перелік продуктів
        System.out.println(wd);
        System.out.println(ps);
        //Обчислюємо вагу продуктів
        System.out.printf("Загальна вага: %1.3f", calcWeight());
    }

    private float calcWeight(){
        float fullWeight = 0;
        for (Object timber : ps.getArr()){
            fullWeight+=((IWeight)timber).weight();
        }
        return fullWeight;
    }

}
