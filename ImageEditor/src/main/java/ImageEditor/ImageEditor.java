package ImageEditor;
import java.io.IOException;
import java.lang.*;


public class ImageEditor {
    public static void main(String[] args){
        PPM myPPM;
        try {
            String infile = args[0];
            String outfile = args[1];
            String type = args[2];
            //int blurLength = Integer.parseInt(args[3]);

            if (type.equals("motionblur")) {
                int blurLength = Integer.parseInt(args[3]);
                if(blurLength < 1){
                    System.out.println("Please enter a valid Blur Length\nUSAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
                }
                else {
                    myPPM = new PPM(infile, outfile, type, blurLength);
                    myPPM.writeToFile(myPPM.motionblur(myPPM.toPixelList(myPPM.toList())));
                }
            }
            else if (type.equals("invert")){
                myPPM = new PPM(infile, outfile, type);
                myPPM.writeToFile(myPPM.invert(myPPM.toList()));
            }
            else if(type.equals("emboss")){
                myPPM = new PPM(infile, outfile, type);
                myPPM.writeToFile(myPPM.emboss(myPPM.toPixelList(myPPM.toList())));
            }
            else if(type.equals("grayscale")){
                myPPM = new PPM(infile, outfile, type);
                myPPM.writeToFile(myPPM.grayscale(myPPM.toList()));
            }
            else{
                myPPM = new PPM();
                System.out.println("That is an invalid Type\nUSAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
            }
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            System.out.println("USAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
        }



    }
}
