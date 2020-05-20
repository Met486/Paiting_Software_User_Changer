package UserChanger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;

public class Main extends Application {


    public static Properties SettingProp = new Properties();

    static String strpass = "./file.properties";


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            InputStream Istream = new FileInputStream(strpass);
            SettingProp.load(Istream);
            System.out.println(SettingProp.getProperty("CSP"));
        } catch(IOException e) {
            e.printStackTrace();
            File newfile = new File("./file.properties");
            newfile.createNewFile();
            System.out.println("file create is Success");
        }
        Parent root = FXMLLoader.load(getClass().getResource("Top.fxml"));
        primaryStage.setTitle("ユーザ切り替え用");
        primaryStage.setScene(new Scene(root, 480, 300));
        primaryStage.setResizable(false);
        primaryStage.show();



        /*
        primaryStage.showingProperty().addListener((observable) -> {
            try{

            }

            catch(Exception e){}
        });
        */
    }

    public static  void setSettingProp(String key,String value)
    {
        try{
        SettingProp.setProperty(key,value);
        SettingProp.store(new FileOutputStream(strpass),"Comment");
        System.out.println("setProperty is success");
        }
        catch(Exception e){}
    }

    public static String getSettingProp(String key)
    {
        return SettingProp.getProperty(key);
    }

    public static void main(String[] args) {
        launch(args);
    }

}