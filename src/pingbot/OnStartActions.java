package pingbot;

import keyboards.MainMenu;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import utilities.LocalisationService;


public class OnStartActions {
    Message message;
    int USER_ID = 0;
    
    public OnStartActions(Message msg){
        this.message=msg;
        this.USER_ID=msg.getFrom().getId();
    }
    
    public SendMessage messageStart(){
        SendMessage msgRequest = new SendMessage();
        msgRequest.setChatId(message.getChatId().toString());
        msgRequest.enableMarkdown(true);
        msgRequest.setText(LocalisationService.getInstance().getString("onStart"));
        msgRequest.setReplyMarkup(new MainMenu());
        return msgRequest;
    }
   
}
