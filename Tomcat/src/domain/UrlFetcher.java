package domain;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author thomas
 */
public class UrlFetcher {

    private final URL url;

    public UrlFetcher(String urlString) {
        try {
            url = new URL(urlString);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public String getContent() {
    	StringBuilder result = new StringBuilder();
    	String temp = "";
    	try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
    		while (null != (temp = br.readLine())) {
    			result.append(temp);
    		}
    	} catch (IOException ex) {
    		throw new RuntimeException(ex);
    	}
    	return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(
                new UrlFetcher
                ("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=63925&limit=2").getContent());
        
    }
}
