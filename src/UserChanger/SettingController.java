package UserChanger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import static UserChanger.tool.sendProp;

public class SettingController implements Initializable {

    @FXML
    TextField CSPText1;
    @FXML
    TextField MPText1;
    @FXML
    TextField FAText1;
    @FXML
    Button CSPBt1;
    @FXML
    Button MPBt1;
    @FXML
    Button FABt1;

    @FXML
    TextField CSPText2;
    @FXML
    TextField MPText2;
    @FXML
    TextField FAText2;
    @FXML
    Button CSPBt2;
    @FXML
    Button MPBt2;
    @FXML
    Button FABt2;

    Properties SettingProp = new Properties();

    public void initialize (URL url, ResourceBundle rb)
    {
        this.SettingProp = Main.SettingProp;

        CSPText1.setText(SettingProp.getProperty("CSP"));
        MPText1.setText(SettingProp.getProperty("MP"));
        FAText1.setText(SettingProp.getProperty("FA"));
        CSPText2.setText(SettingProp.getProperty("CopyCSP"));
        MPText2.setText(SettingProp.getProperty("CopyMP"));
        FAText2.setText(SettingProp.getProperty("CopyFA"));

    }



    public void OnClickCSPBt1(ActionEvent Event)
    {
        try {

            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("ディレクトリ選択");

            File folder = new File(CSPText1.getText());
            if(folder.exists()){directoryChooser.setInitialDirectory(new File(CSPText1.getText()));}//設定されたディレクトリを開く
            else{ directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));}

            File CSPF = directoryChooser.showDialog(null);
            CSPText1.setText(CSPF.toString());
            sendProp("CSP",CSPF);
        }
        catch(Exception e){

        }
    }

    public void OnClickMPBt1(ActionEvent Event)
    {
        try {

            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("ディレクトリ選択");
            File folder = new File(MPText1.getText());
            if(folder.exists()){directoryChooser.setInitialDirectory(new File(MPText1.getText()));}//設定されたディレクトリを開く
            else{ directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));}

            File MPF = directoryChooser.showDialog(null);
            MPText1.setText(MPF.toString());
            sendProp("MP",MPF);
        } catch (Exception e) { }
    }

    public void OnClickFABt1(ActionEvent Event)
    {

        try {

            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("ディレクトリ選択");
            File folder = new File(FAText1.getText());
            if(folder.exists()){directoryChooser.setInitialDirectory(new File(FAText1.getText()));}//設定されたディレクトリを開く
            else{ directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));}

            File FAF = directoryChooser.showDialog(null);
            FAText1.setText(FAF.toString());
            sendProp("FA",FAF);
        }catch (Exception e) {}
    }


    public void OnClickCSPBt2(ActionEvent Event)
    {
        try {

            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("ディレクトリ選択");
            File folder = new File(CSPText2.getText());
            if(folder.exists()){directoryChooser.setInitialDirectory(new File(CSPText2.getText()));}//設定されたディレクトリを開く
            else{ directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));}

            File CSPF = directoryChooser.showDialog(null);
            CSPText2.setText(CSPF.toString());
            sendProp("CopyCSP",CSPF);
        }
        catch(Exception e){

        }
    }

    public void OnClickMPBt2(ActionEvent Event)
    {
        try {

            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("ディレクトリ選択");
            File folder = new File(MPText2.getText());
            if(folder.exists()){
                directoryChooser.setInitialDirectory(new File(MPText2.getText()));
            }//設定されたディレクトリを開く
            else{ directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));}

            File MPF = directoryChooser.showDialog(null);
            MPText2.setText(MPF.toString());
            sendProp("CopyMP",MPF);
        } catch (Exception e) { }
    }

    public void OnClickFABt2(ActionEvent Event)
    {
        try {

            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("ディレクトリ選択");
            File folder = new File(FAText2.getText());
            if(folder.exists()){directoryChooser.setInitialDirectory(new File(FAText2.getText()));}//設定されたディレクトリを開く
            else{ directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));}

            File FAF = directoryChooser.showDialog(null);
            FAText2.setText(FAF.toString());
            sendProp("CopyFA",FAF);
        }catch (Exception e) {}
    }
}
