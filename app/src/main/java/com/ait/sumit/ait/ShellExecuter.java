package com.ait.sumit.ait;

/**
 * Created by sumit on 16/6/16.
 */
import java.io.BufferedReader;
        import java.io.InputStreamReader;

public class ShellExecuter {
    public ShellExecuter() {

        }

    public String Executer(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

           String line = "";
            while ((line = reader.readLine())!= null) {
               output.append(line + "n");
                }

            } catch (Exception e) {
            e.printStackTrace();
           }
        String response = output.toString();
        return response;

        }
}