package keyboards;

import java.util.ArrayList;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

/**
 * This is the main menu keyboard.
 * @author Luca
 */
public class MainMenu extends ReplyKeyboardMarkup{
    
    public MainMenu(){
        this.setOneTimeKeyboad(Boolean.TRUE);
        this.setResizeKeyboard(Boolean.TRUE);
        
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
            firstRow.add("help");
//            firstRow.add("nslookup");
//            firstRow.add("dig");
//        KeyboardRow secondRow = new KeyboardRow();
//            secondRow.add("help");
        keyboard.add(firstRow);
        //keyboard.add(secondRow);
        
        this.setKeyboard(keyboard);
    }

}
