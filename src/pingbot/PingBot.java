package pingbot;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.logging.BotLogger;

/**
 *
 * @author Luke
 */
public class PingBot{
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        String LOGTAG = "Log >> ";
        
        TelegramBotsApi tgBotApi = new TelegramBotsApi();
        try {
            tgBotApi.registerBot(new BotMainFrame());
        } catch (TelegramApiException ex){
            BotLogger.error(LOGTAG, ex);
        }
    }
    
}
