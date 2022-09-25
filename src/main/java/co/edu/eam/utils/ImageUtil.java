package co.edu.eam.utils;

import java.util.Base64;

public class ImageUtil {
    public String getImgData(String imgString) { return Base64.getDecoder().decode(imgString).toString(); }
}
