package pingbot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import utilities.LocalisationService;


public class OnHelpCommand {
    Message message;
    int USER_ID = 0;
    
    public OnHelpCommand(Message msg){
        this.message=msg;
        this.USER_ID=msg.getFrom().getId();
    }
    
    public SendMessage dynamicHelp(){
        SendMessage msgRequest = new SendMessage();
        msgRequest.setChatId(message.getChatId().toString());
        msgRequest.enableMarkdown(true);
        msgRequest.setText(LocalisationService.getInstance().getString("onHelp"));
        return msgRequest;
    }
}
