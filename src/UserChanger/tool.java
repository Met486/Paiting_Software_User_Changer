package UserChanger;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class tool {
    public static void sendProp(String key,File value)
    {
        UserChanger.Main.setSettingProp(key,value.toString());
    }
    public static String getProp(String key)
    {
        return UserChanger.Main.getSettingProp(key);
    }

    public static void sendProp(String key,String value)
    {
        UserChanger.Main.setSettingProp(key,value);
    }

    public static void copyFolder(File src, File dest) throws IOException
    {

        try
        {
            FileUtils.copyDirectory(src,dest,false);
        }
        catch(IOException e)
        {
            e.printStackTrace();

        }

    }
}
