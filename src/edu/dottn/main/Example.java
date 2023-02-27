import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
 
import java.net.URI; 
import java.math.BigDecimal; 
 
public class Example { 
    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "AC38ca3baee5a2f1a059109ca5439c80be"; 
    public static final String AUTH_TOKEN = "6e61c854fafc4ebc206ce3016c792abb"; 
 
    public static void main(String[] args) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("+21629603440"),  
                "MGb941d12093a8477951ec4cfa5075bcd8", 
                "EYAAAA")      
            .create(); 
 
        System.out.println(message.getSid()); 
    } 
}