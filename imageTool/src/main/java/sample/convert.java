package sample;

import net.coobird.thumbnailator.Thumbnails;
import java.io.IOException;


public class convert {
    private static String convertedImage;
    //setting up the directory of the converted output
    public static void convertImage(String imageDirectory, String name, String type){
        switch (type) {
            case "jpg":
                //Directory for converted image of *.jpg
                convertedImage = System.getProperty("user.dir") + "/file/Output/"
                        + name.substring(0, name.lastIndexOf(".")) + ".jpg";
                break;
            case "png":
                //Directory for converted image of *.png
                convertedImage = System.getProperty("user.dir") + "/file/Output/"
                        + name.substring(0, name.lastIndexOf(".")) + ".png";
                break;
            case "gif":
                //Directory for converted image of *.gif
                convertedImage = System.getProperty("user.dir") + "/file/Output/"
                        + name.substring(0, name.lastIndexOf(".")) + ".gif";
                break;
            case "bmp":
                //Directory for converted image of *.bmp
                convertedImage = System.getProperty("user.dir") + "/file/Output/"
                        + name.substring(0, name.lastIndexOf(".")) + ".bmp";
                break;
            default:
                convertedImage=System.getProperty("user.dir") + "/file/Output/" + convertedImage;
                break;
        }

        //quality and size of converted image set
        // equal to the original image
        //convert the image to selected format
        //convert the file to directory created above
        try {

            Thumbnails.of(imageDirectory)
                    .scale(1.0f)
                    .outputFormat(type)
                    .outputQuality(1.0f)
                    .toFile(convertedImage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
