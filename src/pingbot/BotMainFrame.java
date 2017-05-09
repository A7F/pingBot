package pingbot;

import java.util.ArrayList;
import keyboards.MainMenu;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import utilities.LocalisationService;

/**
 * this class handles incoming updates and dispatch commands received to the right use-case class.
 * @author Luke
 */
public class BotMainFrame extends TelegramLongPollingBot{

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
    
    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();
            String messageText = message.getText();
            
            //use case: command /start received
            if(messageText.equals("/start")){
                SendMessage msg = new SendMessage();
                msg.setReplyMarkup(new MainMenu());
                msg.setText(LocalisationService.getInstance().getString("onStart"));
                msg.setChatId(message.getChatId().toString());
                try {
                    sendMessage(msg);
                } catch (TelegramApiException ex) {
                    ex.toString();
                }
            }
            
            //use case: command /ping [address]
            if(messageText.toLowerCase().startsWith("/ping") || messageText.toLowerCase().startsWith("ping")){
                String request;
                SendMessage msg;
                OnPingActions pingAction = new OnPingActions();
                request = pingAction.elaboratePingRequest(message.getText());
                if(request.equals("")){
                    msg = pingAction.messageSendInvalid();
                    msg.setChatId(message.getChatId().toString());
                }else{
                    msg = pingAction.messageSend(request);
                    msg.setChatId(message.getChatId().toString());
                }
                try {
                    System.out.println(request+"\t"+message.getFrom().getId()+"\t"+message.getChatId().toString());
                    sendMessage(msg);
                } catch (TelegramApiException ex) {
                    ex.toString();
                }
            }
            
            //use case: user need help
            if(message.getText().equals("/help") || message.getText().equals("help")){
                OnHelpCommand com = new OnHelpCommand(message);
                SendMessage send = com.dynamicHelp();
                try {
                    sendMessage(send);
                } catch (TelegramApiException ex) {
                    ex.toString();
                }
            }
            
        }
        
        //inline query are handled here
        if(update.hasInlineQuery()){
            InlineQuery inlineQuery = update.getInlineQuery();
            AnswerInlineQuery answer = new AnswerInlineQuery();
            answer.setInlineQueryId(inlineQuery.getId());
            
            if(!inlineQuery.getQuery().isEmpty()){
                OnPingActions pingAction = new OnPingActions();
                answer.setCacheTime(100);
                answer.setResults(pingAction.fastPing(inlineQuery.getQuery()));
            }else{
                answer.setResults(new ArrayList<>());
            }
            try {
                answerInlineQuery(answer);
            } catch (TelegramApiException ex) {
                ex.toString();
            }
        }
    }
}
