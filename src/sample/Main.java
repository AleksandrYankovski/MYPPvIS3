package sample;

import controller.DataController;

import java.applet.Applet;

public class Main extends Applet{




       /* try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }*/
       public void init() {
           new DataController();
       }
}
