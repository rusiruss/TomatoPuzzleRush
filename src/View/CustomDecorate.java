package View;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * The CustomDecorate class provides utility methods for customizing the appearance of Swing components.
 */
public class CustomDecorate {

    private static CustomDecorate instance;

    /**
     * Private constructor to prevent external instantiation.
     */
    private CustomDecorate() {
        // Private constructor to prevent instantiation.
    }

    /**
     * Returns the singleton instance of CustomDecorate.
     *
     * @return The singleton instance of CustomDecorate.
     */
    public static synchronized CustomDecorate getInstance() {
        if (instance == null) {
            instance = new CustomDecorate();
        }
        return instance;
    }

    /**
     * Sets a custom cursor for a given component.
     *
     * @param component The component for which the cursor is to be set.
     * @return The CustomDecorate instance for method chaining.
     */
    public CustomDecorate setCustomCursor(Component component) {
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image cursorImage = toolkit.getImage(CustomDecorate.class.getResource("/assest/cursor.png"));
            Cursor customCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "CustomCursor");
            component.setCursor(customCursor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the icon for the JFrame.
     *
     * @param frame The JFrame for which the icon is to be set.
     * @return The CustomDecorate instance for method chaining.
     */
    public CustomDecorate setIcon(JFrame frame) {
        try {
            String iconPath = "E:/Code/CIS/Assest/icon.png";
            ImageIcon icon = new ImageIcon(iconPath);
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Centers the JFrame on the screen.
     *
     * @param frame The JFrame to be centered.
     * @return The CustomDecorate instance for method chaining.
     */
    public CustomDecorate setFrametoCenter(javax.swing.JFrame frame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setLocation(screenSize.width / 2 - frame.getWidth() / 2, screenSize.height / 2 - frame.getHeight() / 2);
        return this;
    }
}
