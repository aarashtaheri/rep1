/**
 * Your application code goes here
 */

package userclasses;

import generated.StateMachineBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*; 
import com.codename1.ui.events.*;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {
    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }
    
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
	protected void initVars(Resources res){
	}

    @Override
    protected void onMain_ButtonAction(Component c, ActionEvent event) {
        ConnectionRequest req = new ConnectionRequest(){

            @Override
            protected void readResponse(InputStream input) throws IOException {
                //super.readResponse(input);
                
                InputStreamReader reader = new InputStreamReader(input);
                JSONParser parser = new JSONParser();
                @SuppressWarnings("deprecation")
				Hashtable<String, Object> response = parser.parse(reader);
//                String version = (String)response.get("version");
//                Hashtable feed = (Hashtable)response.get("feed");
//                Hashtable title = (Hashtable)feed.get("title");
//                String titleStr = (String)title.get("$t");
                
                // .. Work with content some more
	            System.out.println( response + "");
	            
//	            response.get(")
	            
	            float sumAllExpense = 60;
	            float eachOneHasToPay = 20;
	            float howMuchIpaid = 5;
	            float mybalance = -15;
	            
	            findLabel().setText(response + "");
	            findLblMeinAnteil().setText("Mein Anteil : " + mybalance);
	            
                
            }
            
        };
        
        String domain = "http://localhost:8080/";
        req.setPost(false);
        req.setHttpMethod("GET");
		String url = domain  + "users/test";
		req.setUrl(url );
        
        NetworkManager.getInstance().addToQueue(req);
        
        
        
    }
}
