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
        keyboard.add(firstRow);
        
        this.setKeyboard(keyboard);
    }

}
