package pingbot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;


public class OnDigActions {
    Message message;
    String result;
    
    int USER_ID = 0;
    
    public SendMessage messageSend(String request){
        SendMessage msgRequest = new SendMessage();
        msgRequest.enableMarkdown(true);
        this.runDigCommand(request);
        msgRequest.setText(result);
        return msgRequest;
    }
    
    public SendMessage messageSendInvalid(){
        SendMessage msgRequest = new SendMessage();
        msgRequest.enableMarkdown(true);
        msgRequest.setText("Invalid address :)");
        return msgRequest;
    }
    
    public SendMessage messageStart(){
        SendMessage msgRequest = new SendMessage();
        msgRequest.setChatId(message.getChatId().toString());
        msgRequest.enableMarkdown(true);
        
        return msgRequest;
    }
    
    
    public void runDigCommand(String address) {
        String command = "dig "+address;
        StringBuilder str = new StringBuilder();

        try {
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));

                String s = "";
                // reading output stream of the command
                while ((s = inputStream.readLine()) != null) {
                    str.append(s).append("\n");
                    System.out.println(s);
                }
                result=str.toString();

        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    public String elaboratePingRequest(String request){
        StringBuilder build = new StringBuilder();
        String address = request.replace("/dig ", "");
        if(address.toLowerCase().equals("localhost")){
            build.append("");
        }else if(address.equals("")){
            build.append("Empty address");
        }else{
            build.append(address);
        }
        return build.toString();
    }
   
}
