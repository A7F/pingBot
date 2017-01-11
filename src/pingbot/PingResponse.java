package pingbot;

/**
 *
 * @author Luca
 */
class PingResponse {
    int byte_number;
    int duration;
    int ttl;
    
    public void setByte_number(int byte_number) {
        this.byte_number = byte_number;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getByte_number() {
        return byte_number;
    }

    public int getDuration() {
        return duration;
    }

    public int getTtl() {
        return ttl;
    }
    
    
}
