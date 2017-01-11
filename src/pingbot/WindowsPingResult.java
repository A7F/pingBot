package pingbot;

import java.util.ArrayList;

/**
 *
 * @author Luca
 */
public class WindowsPingResult {
    private String address;
    private int ping_bytes;
    private ArrayList<PingResponse> pingResponse = new ArrayList<>();
    private int packet_sent;
    private int packet_received;
    private int packet_lost;
    private int loss_percentage;
    private int min_rtt;
    private int max_rtt;
    private int avg_rtt;
    
    public void insertPingResponse(PingResponse response){
        pingResponse.add(response);
    }

    public String getAddress() {
        return address;
    }

    public int getPing_bytes() {
        return ping_bytes;
    }

    public ArrayList<PingResponse> getPingResponse() {
        return pingResponse;
    }

    public int getPacket_sent() {
        return packet_sent;
    }

    public int getPacket_received() {
        return packet_received;
    }

    public int getPacket_lost() {
        return packet_lost;
    }

    public int getLoss_percentage() {
        return loss_percentage;
    }

    public int getMin_rtt() {
        return min_rtt;
    }

    public int getMax_rtt() {
        return max_rtt;
    }

    public int getAvg_rtt() {
        return avg_rtt;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPing_bytes(int ping_bytes) {
        this.ping_bytes = ping_bytes;
    }

    public void setPingResponse(ArrayList<PingResponse> pingResponse) {
        this.pingResponse = pingResponse;
    }

    public void setPacket_sent(int packet_sent) {
        this.packet_sent = packet_sent;
    }

    public void setPacket_received(int packet_received) {
        this.packet_received = packet_received;
    }

    public void setPacket_lost(int packet_lost) {
        this.packet_lost = packet_lost;
    }

    public void setLoss_percentage(int loss_percentage) {
        this.loss_percentage = loss_percentage;
    }

    public void setMin_rtt(int min_rtt) {
        this.min_rtt = min_rtt;
    }

    public void setMax_rtt(int max_rtt) {
        this.max_rtt = max_rtt;
    }

    public void setAvg_rtt(int avg_rtt) {
        this.avg_rtt = avg_rtt;
    }
    
    
}
