package keyboards;

import utilities.LocalisationService;
import java.util.ArrayList;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

/**
 * This keyboard will only be displayed on user first start.
 * @author Luca
 */
public class LanguageSelectionMenu extends ReplyKeyboardMarkup{
    
    String reg_code = "";
    
    public LanguageSelectionMenu(String region){
        this.reg_code = region;
        this.setSelective(Boolean.TRUE);
        this.setOneTimeKeyboad(Boolean.TRUE);
        this.setResizeKeyboard(Boolean.TRUE);
        
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
            firstRow.add(LocalisationService.getInstance().getString("LK_english", reg_code));
            firstRow.add(LocalisationService.getInstance().getString("LK_Italian", reg_code));
        keyboard.add(firstRow);
        
        this.setKeyboard(keyboard);
    }
}
