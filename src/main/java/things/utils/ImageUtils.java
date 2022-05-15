package things.utils;

import javax.microedition.lcdui.Graphics;

public final class ImageUtils {
    public static int alignX(int imageWidth, int x, int anchor) {
        int alignedX = x;
        if ((anchor & Graphics.RIGHT) != 0) {
            alignedX -= imageWidth;
        }
        else if ((anchor & Graphics.HCENTER) != 0) {
            alignedX -= imageWidth / 2;
        }
        return alignedX;
    }

    public static int alignY(int imageHeight, int y, int anchor) {
        int alignedY = y;
        if ((anchor & Graphics.BOTTOM) != 0) {
            alignedY -= imageHeight;
        }
        else if ((anchor & Graphics.VCENTER) != 0) {
            alignedY -= imageHeight / 2;
        }
        return alignedY;
    }
}