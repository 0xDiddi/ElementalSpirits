package theultimatehose.elementalspirits.scroll.buttons;

import net.minecraft.client.Minecraft;
import theultimatehose.elementalspirits.scroll.structure.Book;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class UtilButtons {

    private static SmallButton btnReload;
    private static SmallButton btnTwitter;

    public static void init(int guiLeft, int guiTop) {
        btnReload = new SmallButton(0, guiLeft - 18, guiTop, 0, 237, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book.init();
            }
        });

        btnTwitter = new SmallButton(0, guiLeft - 18, guiTop + 18, 32, 237, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://twitter.com/Ultimate_Hose"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void draw(int mouseX, int mouseY) {
        btnReload.drawButtonForegroundLayer(mouseX, mouseY);
        btnTwitter.drawButtonForegroundLayer(mouseX, mouseY);
    }

    public static void checkClick(Minecraft mc, int mouseX, int mouseY) {
        btnReload.mousePressed(mc, mouseX, mouseY);
        btnTwitter.mousePressed(mc, mouseX, mouseY);
    }

}
