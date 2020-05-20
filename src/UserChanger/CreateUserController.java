package UserChanger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable
{

    tool Tool = new tool();


    @FXML
    TextField NewUserName;

    @FXML
    Button CreateUserBt;

    @FXML
    Label CreateCheck ;

    Properties SettingProp = new Properties();


    public void initialize (URL url, ResourceBundle rb)
    {
        CreateCheck.setText("名前を入れてください。");
        this.SettingProp = Main.SettingProp;
    }

    void onCloseAction(ActionEvent event)
    {
        Scene scene = ((Node) event.getSource()).getScene();
        Window window = scene.getWindow();
        window.hide();
    }

    @FXML protected void onCreateUserAction(ActionEvent event)
    {
        {
            try
            {
                String str ;
                str = NewUserName.getText();
                System.out.println(str+" check");//check
                File dataText = new File("./UserList.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(dataText,true));



                File sourceFileC = new File(SettingProp.getProperty("CSP"));
                File targetFileC = new File(SettingProp.getProperty("CopyCSP")+"//"+str);
                File sourceFileM = new File(SettingProp.getProperty("MP"));
                File targetFileM = new File(SettingProp.getProperty("CopyMP")+"//"+str);
                File sourceFileF = new File(SettingProp.getProperty("FA"));
                File targetFileF = new File(SettingProp.getProperty("CopyFA")+"//"+str);
                Tool.copyFolder(sourceFileC,targetFileC);
                Tool.copyFolder(sourceFileM,targetFileM);
                Tool.copyFolder(sourceFileF,targetFileF);
                bw.write(str+"\n");
                bw.close();
                Controller.data.add(new Controller.UserClass(str));

            }

            catch(Exception e)
            {
                System.out.println("Error");
            }

            CreateUserBt.getScene().getWindow().hide();
        }
    }
}
