package Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetSystemInfoService {
    //        String[] commands = new String[] { "CMD", "/C", "WMIC cpu get Name"};
    //        String[] commands = new String[] { "CMD", "/C", "WMIC diskdrive get Model"};
    //        String[] commands = new String[] { "CMD", "/C", "WMIC diskdrive get Size"};
    //        String[] commands = new String[] { "CMD", "/C", "WMIC COMPUTERSYSTEM GET Model"};
    //        String[] commands = new String[] { "CMD", "/C", "WMIC COMPUTERSYSTEM GET Manufacturer"};

    public static String runCmds(String[] command) {
        String data = "";
        try {
            String[] com = command;
            Process process = Runtime.getRuntime().exec(com);
            //Closing output stream of the process
            process.getOutputStream().close();

            //Reading sucessful output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                data += s;
            }
            //data = reader.lines().collect(Collectors.joining());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  trimOutput(data.trim().replaceAll(" +", " "));
    }

    public static String trimOutput(String text){
        return text.substring(text.indexOf(" ")+1);
    }
}