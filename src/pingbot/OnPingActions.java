package pingbot;

import com.googlecode.jpingy.Ping;
import com.googlecode.jpingy.Ping.Backend;
import com.googlecode.jpingy.PingArguments;
import com.googlecode.jpingy.PingRequest;
import com.googlecode.jpingy.PingResult;
import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultArticle;
import utilities.Emoji;
import utilities.LocalisationService;


public class OnPingActions{
    String result;
    String request;
    ArrayList<String> pingResults = new ArrayList<>();
    WindowsPingResult ping = new WindowsPingResult();
        
    public SendMessage messageSend(String request){
        SendMessage msgRequest = new SendMessage();
        msgRequest.enableMarkdown(true);
        this.runBigUnixPingCommand(request);
        msgRequest.setText(result);
        return msgRequest;
    }
    
    public SendMessage messageSendInvalid(){
        SendMessage msgRequest = new SendMessage();
        msgRequest.enableMarkdown(true);
        msgRequest.setText("Seems you inserted an invalid host...");
        return msgRequest;
    }
    
    public void runUnixPingCommand(String address){
        PingArguments arguments = new PingArguments.Builder().url(address).timeout(1000).count(2).bytes(32).stop(3).build();
        PingResult results;
        StringBuilder build = new StringBuilder();
        
        results = Ping.ping(arguments, Backend.UNIX);
        if(results!=null){
            build.append(Emoji.GlobeWithMeridians).append("Ping results for ").append(address).append("\n");
            for(int i=0; i<results.getRequests().size();i++){
                PingRequest r = results.getRequests().get(i);
                build.append(Emoji.Package).append(" byte ").append(r.bytes()).append("  ").append(Emoji.Clock230).append(r.time()).append("   TTL: ").append(r.ttl());
                build.append("\n");
            }
            build.append("\n");
            build.append(Emoji.BarChart).append(" Ping Stats for ").append(results.address()).append("\n");
            build.append(Emoji.InboxTray).append(results.received()).append("  ");
            build.append(Emoji.OutboxTray).append(results.transmitted()).append("  ");
            build.append(Emoji.NegativeSquaredCrossMark).append(results.transmitted()-results.received()).append("\n");
            build.append(Emoji.AlarmClock).append("min: ").append(results.rtt_min()).append("\n").append(Emoji.AlarmClock).append("max: ").append(results.rtt_max()).append("\n").append(Emoji.AlarmClock).append("avg: ").append(results.rtt_avg());
        }else{
            build.append(LocalisationService.getInstance().getString("pingFailed"));
        }
       
        result=build.toString();
    }
    
    public void runBigUnixPingCommand(String address){
        PingArguments arguments = new PingArguments.Builder().url(address).timeout(1000).count(3).bytes(32).stop(3).build();
        PingResult results;
        StringBuilder build = new StringBuilder();
        
        results = Ping.ping(arguments, Backend.UNIX);
        if(results!=null){
            build.append(Emoji.GlobeWithMeridians).append("Ping results for ").append(address).append("\n");
            for(int i=0; i<results.getRequests().size();i++){
                PingRequest r = results.getRequests().get(i);
                build.append(Emoji.Package).append(" byte ").append(r.bytes()).append("  ").append(Emoji.Clock230).append(r.time()).append("   TTL: ").append(r.ttl());
                build.append("\n");
            }
            build.append("\n");
            build.append(Emoji.BarChart).append(" Ping Stats for ").append(results.address()).append("\n");
            build.append(Emoji.InboxTray).append(results.received()).append("  ");
            build.append(Emoji.OutboxTray).append(results.transmitted()).append("  ");
            build.append(Emoji.NegativeSquaredCrossMark).append(results.transmitted()-results.received()).append("\n");
            build.append(Emoji.AlarmClock).append("min: ").append(results.rtt_min()).append("\n").append(Emoji.AlarmClock).append("max: ").append(results.rtt_max()).append("\n").append(Emoji.AlarmClock).append("avg: ").append(results.rtt_avg());
        }else{
            build.append(LocalisationService.getInstance().getString("pingFailed"));
        }
       
        result=build.toString();
    }
    
    public List<InlineQueryResult> fastPing(String addr){
        String address = elaboratePingRequest(addr);
        List<InlineQueryResult> ilr = new ArrayList<>();
        PingArguments arguments = new PingArguments.Builder().url(address).timeout(1000).count(1).bytes(32).stop(1).build();
        InlineQueryResultArticle r = new InlineQueryResultArticle();
        PingResult results = Ping.ping(arguments, Backend.UNIX);
        r.setTitle("Ping result");
        r.setId("0");
        InputTextMessageContent messageContent = new InputTextMessageContent();
        
        if(results!=null){
            runUnixPingCommand(address);
            r.setDescription("Host is up "+Emoji.WhiteCheckMark);
            messageContent.setMessageText(result);
            messageContent.disableWebPagePreview();
            messageContent.enableMarkdown(true);
            r.setInputMessageContent(messageContent);
        }else{
            r.setDescription("Host not reachable "+Emoji.NegativeSquaredCrossMark);
            messageContent.setMessageText(LocalisationService.getInstance().getString("fastPingFailed"));
            r.setInputMessageContent(messageContent);
        }
        
        ilr.add(r);
        return ilr;
    }
    
    public String elaboratePingRequest(String request){
        StringBuilder build = new StringBuilder();
        String address = request.replace("/ping ", "");
        address = address.replace(" ", "");
        if(address.toLowerCase().equals("localhost")){
            build.append("");
        }else if(address.equals("")){
            build.append("Empty");
        }else{
            build.append(address);
        }
        this.request=build.toString();
        return build.toString();
    }
   
}
