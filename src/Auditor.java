import Services.GetSystemInfoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;

public class Auditor {
    public static void main(String[] args) {
        String[] commands = new String[]{"CMD", "/C", "WMIC COMPUTERSYSTEM GET Model"};
        System.out.println(GetSystemInfoService.runCmds(commands));
String data="";
        try {
            Process process = Runtime.getRuntime().exec("powershell -command \"Get-ItemProperty HKLM:\\Software\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\* | Select-Object DisplayName, DisplayVersion, Publisher, InstallDate | Format-Table –AutoSize\"");
            process.getOutputStream().close();

            //Reading sucessful output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                data += s;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(data);




    }

}
