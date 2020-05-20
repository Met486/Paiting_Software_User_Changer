package UserChanger;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

public class Controller implements Initializable
{

    tool Tool = new tool();

    public static ObservableList<UserClass> data;
    private ObjectProperty<UserClass> selectedData;
    BooleanProperty blnBindBidirectional = new SimpleBooleanProperty();

    @FXML
    Label nowUser;
    @FXML
    Label lastSave;
    @FXML
    ComboBox<UserClass> user;


    @FXML
    Button userChanege;
    @FXML
    Button createUser;
    @FXML
    Button SaveSetting;
    @FXML
    Menu menu1;
    @FXML
    public MenuItem MenuSettings;

    Properties SettingProp = new Properties();

    public void initialize(URL location, ResourceBundle resources)
    {
        user.editableProperty().bind(blnBindBidirectional);
        data = FXCollections.observableArrayList();
        try
        {
            File dataText = new File("./UserList.txt");
            BufferedReader br = new BufferedReader(new FileReader(dataText));
            String str = br.readLine();

            while(str!=null)
            {
                System.out.println(str);
                data.add(new UserClass(str));
                str=br.readLine();

            }
            br.close();
            user.setItems(data);
        }
        catch(IOException e)
        {
            System.out.println("File Read error");
            File newFile = new File("./UserList.txt");
            try
            {
                newFile.createNewFile();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

            System.out.println("file create is Success");
        }
        catch(Exception e){
            System.out.println("another error");
        }

        this.SettingProp= Main.SettingProp;
        nowUser.setText(SettingProp.getProperty("lastUser"));
        SaveChange();

        if(SettingProp.getProperty("firstBoot").equals("true"))
        {
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("初回起動");
            dialog.setHeaderText("ReadMeを熟読の上、新規登録の前に必ずフォルダの設定を行ってください");
            dialog.setContentText("設定画面を開きますか？");
            Optional<ButtonType> result = dialog.showAndWait();
            if(result.get()==ButtonType.OK)
            {
                try
                {
                    showSettingsWindow();
                    Tool.sendProp("firstBoot","false");
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }

    }



    public static class UserClass
    {
//TODO 次のバージョンでグループ実装予定
 /*      private StringProperty group = new SimpleStringProperty();

        public final String getGroup()
        {
            return this.group.get();
        }

        public final void setGroup(String group)
        {
            this.group.set(group);
        }
  */
        private StringProperty name = new SimpleStringProperty();
        public StringProperty nameProperty(){return name;}
        public final String getName()
        {
            return nameProperty().get();
        }

        public final void setName(String name)
        {
            this.nameProperty().set(name);
        }

        public StringProperty valueProperty()
        {
            return this.name;
        }
/*
        public UserClass(String group,String name)
        {
           this.group.set(group);
            this.name.set(name);
        }
*/
        public UserClass(String name)
        {
            this.name.set(name);
        }

        @Override
        public String toString(){
            return nameProperty().get();
        }
    }

    public static class UserClassStringConverter extends StringConverter<UserClass>
    {
        @Override
        public String toString(UserClass object){
            if(object==null){return "データなし";}
            return object.getName().toString();
        }

        @Override
        public UserClass fromString(String str){
            UserClass userClass = new UserClass(str);
            return userClass;
        }
    }

    @FXML protected  void SaveChangeBt(ActionEvent event)
    {
            SaveChange();
    }

    protected  void SaveChange()
    {
        this.SettingProp = Main.SettingProp;
        System.out.println("SaveChange Check Sample CSP:"+this.SettingProp.getProperty("CSP"));

        if(!SettingProp.getProperty("lastUser").isEmpty()) {
            try{
                String spa = nowUser.getText();
                File OriginalFileC = new File(SettingProp.getProperty("CSP"));
                File UserFileC = new File(SettingProp.getProperty("CopyCSP") + "//" + spa);
                Tool.copyFolder(OriginalFileC, UserFileC);
                File OriginalFileM = new File(SettingProp.getProperty("MP"));
                File UserFileM = new File(SettingProp.getProperty("CopyMP") + "//" + spa);
                Tool.copyFolder(OriginalFileM, UserFileM);
                File OriginalFileF = new File(SettingProp.getProperty("FA"));
                File UserFileF = new File(SettingProp.getProperty("CopyFA") + "//" + spa);
                Tool.copyFolder(OriginalFileF, UserFileF);

                System.out.println("SaveOK");
            } catch (Exception e) {

            }
        }
    }

    @FXML protected void UserChangeBt(ActionEvent event)
    {
        try {
            SaveChange();
            String spa = (user.getValue().toString());
            Date tempDate = new Date();
            nowUser.setText(spa);
            lastSave.setText(tempDate.toString());
            this.SettingProp = Main.SettingProp;

            try{
                if(!SettingProp.getProperty("CSP").isEmpty()) {
                    File OriginalFileC = new File(SettingProp.getProperty("CSP"));
                    File UserFileC = new File(SettingProp.getProperty("CopyCSP") + "//" + spa);
                    Tool.copyFolder(UserFileC,OriginalFileC);
                }
                if(!SettingProp.getProperty("MP").isEmpty()) {

                    File OriginalFileM = new File(SettingProp.getProperty("MP"));
                    File UserFileM = new File(SettingProp.getProperty("CopyMP") + "//" + spa);
                    Tool.copyFolder(UserFileM, OriginalFileM);
                }
                if(!SettingProp.getProperty("FA").isEmpty()) {
                    File OriginalFileF = new File(SettingProp.getProperty("FA"));
                    File UserFileF = new File(SettingProp.getProperty("CopyFA") + "//" + spa);
                    Tool.copyFolder(UserFileF, OriginalFileF);//
                }

            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.out.println("copy NOT success");
            }

            String str ;
            str = nowUser.getText();
            System.out.println("Change User to "+str);//check
            Tool.sendProp("lastUser",str);
        }
        catch(Exception e) {}
    }

    @FXML
    protected void ChangeSettings(ActionEvent event)
    {
        try {
           showSettingsWindow();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void CreateUserView(ActionEvent event)
    {
       try
       {
            showCreateUserWindow();
       }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
    }

    void showCreateUserWindow()throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }

    void showSettingsWindow()throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
        TabPane root = (TabPane)loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();

    }


}

