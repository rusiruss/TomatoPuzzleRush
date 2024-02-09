package Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import javax.imageio.ImageIO;

import Model.Game;

/**
 * The Server class is responsible for interacting with a web API to retrieve
 * random game data.
 */
public class Server {
	private int Solution;

    /**
     * Reads the content from the specified URL and returns it as a string.
     *
     * @param urlString the URL to read
     * @return the content of the URL as a string, or null if an error occurs
     */
    private static String readUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            InputStream inputStream = url.openStream();

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        } catch (Exception e) {
            // Proper exception handling when URL cannot be read.
            System.out.println("An Error occurred: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves a random game from a web API and returns it as a Game object.
     *
     * @return a random game or null if a game cannot be found
     */
    public Game getRandomGame() {
        String tomatoapi = "https://marcconrad.com/uob/tomato/api.php?out=csv&base64=yes";
        String dataraw = readUrl(tomatoapi);
        String[] data = dataraw.split(",");
        byte[] decodeImg = Base64.getDecoder().decode(data[0]);
        ByteArrayInputStream quest = new ByteArrayInputStream(decodeImg);
        Solution = Integer.parseInt(data[1]);       
        BufferedImage img = null;
        System.out.println("Correct Solution is : "+Solution);
        try {
            img = ImageIO.read(quest);            
            return new Game(img, Solution);           
        } catch (IOException e1) {
            // Proper exception handling.
            e1.printStackTrace();
            return null;
            
        }
        
        
    }
    
    public int Solution() {
    	return Solution;
    }
}
