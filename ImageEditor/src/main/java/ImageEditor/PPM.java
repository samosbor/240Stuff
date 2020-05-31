package ImageEditor;
import java.io.*;
import java.lang.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;


public class PPM{
    String infile;
    String outfile;
    String type;
    int blurLength;
    int width;
    int height;
    int maxColorVal = 255;

    public PPM(){}

    public PPM(String in, String out, String t){
        infile = in;
        outfile = out;
        type = t;
    }
    public PPM(String in, String out, String t, int BL){
        infile = in;
        outfile = out;
        type = t;
        blurLength = BL;
    }

    public ArrayList<String> toList(){
        ArrayList<String> lines = new ArrayList<String>();
        try {
            File file = new File(infile);
            Scanner sc = new Scanner(file).useDelimiter("((#[^\\n]*\\n)|(\\s+))+");

            if(sc.hasNext()){
                String p3 = sc.next();
            }
            if(sc.hasNextInt()){
                width = sc.nextInt();
            }
            if(sc.hasNextInt()){
                height = sc.nextInt();
            }
            if(sc.hasNextInt()){
                int max = sc.nextInt();
            }
            while(sc.hasNextInt()){
                int line = sc.nextInt();
                String strLine = Integer.toString(line);
                lines.add(strLine);
            }


            /*FileReader in = new FileReader(infile);
            BufferedReader br = new BufferedReader(in);
            Pattern dimension = Pattern.compile("^(\\d+) (\\d+)");
            Pattern comment = Pattern.compile("((#[^\\n]*\\n)|(\\s+))+");
            Pattern number = Pattern.compile("^\\d+");
            String line;
            while ((line = br.readLine()) != null) {
                Matcher m1 = dimension.matcher(line);
                Matcher m2 = comment.matcher(line);
                Matcher m3 = number.matcher(line);
                //skip the first line
                if (line.equals("P3")){
                    continue;
                }
                else if (m1.find()){
                    width = Integer.parseInt(m1.group(1));
                    height = Integer.parseInt(m1.group(2));
                    //skip the next line
                    br.readLine();
                }
                else if (m2.find()){
                    continue;
                }
                else if (m3.find()){
                    lines.add(line);
                }
            }*/
        }
        catch (FileNotFoundException ex){
            System.out.println(ex);
        }
        catch (IOException ex){
            System.out.println(ex);
        }
        if(lines.size() != (width*height*3)){
            System.out.println("The amount of pixels does not match up with the height and width");
        }

        return lines;
    }


    public ArrayList<ArrayList<pixel>> toPixelList(ArrayList<String> lines){
        //Convert from list to pixel list grouping every 3rd
        ArrayList<ArrayList<pixel>> pixelList = new ArrayList<ArrayList<pixel>>();
        for (int r = 0; r < height; r++){
            pixelList.add(new ArrayList<pixel>());
            for (int c = (r*(width*3)) ; c < ((r*(width*3)) + (width*3)); c++) {
                //if (c+2 < ((r*(width*3)) + (width*3))){
                int red = Integer.parseInt(lines.get(c));
                int green = Integer.parseInt(lines.get(c+1));
                int blue = Integer.parseInt(lines.get(c+2));

                pixel p = new pixel(red,green,blue);

                pixelList.get(r).add(p);
                c++;
                c++;
                //}
                //else{
                //continue;
                //}
            }
        }
        return pixelList;
    }


    public StringBuilder invert(ArrayList<String> lines){
        StringBuilder sb = new StringBuilder();
        sb.append("P3");
        sb.append("\n");
        sb.append(Integer.toString(width)+" "+Integer.toString(height));
        sb.append("\n");
        sb.append("255");
        sb.append("\n");
        for (String line: lines) {
            int intline = Integer.parseInt(line);
            int outline = java.lang.Math.abs(intline-255);
            sb.append(outline);
            sb.append("\n");
        }
        return sb;
    }



    public void writeToFile(StringBuilder output){
        try {
            //Path path = Paths.get(outfile);
            PrintWriter fw = new PrintWriter(outfile);
            fw.print(output);
            //Files.write(path, output.getBytes(Charset.defaultCharset()));
            fw.close();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }


    public StringBuilder grayscale(List<String> lines){
        StringBuilder sb = new StringBuilder();
        sb.append("P3");
        sb.append("\n");
        sb.append(Integer.toString(width)+" "+Integer.toString(height));
        sb.append("\n");
        sb.append("255");
        sb.append("\n");
        for (int i = 0; i <lines.size(); i++) {
            if (i+2 < lines.size()){
                int red = Integer.parseInt(lines.get(i));
                int green = Integer.parseInt(lines.get(i+1));
                int blue = Integer.parseInt(lines.get(i+2));

                int avg = (red+green+blue)/3;

                sb.append(avg);
                sb.append("\n");
                sb.append(avg);
                sb.append("\n");
                sb.append(avg);
                sb.append("\n");
                i++;
                i++;
            }
            else{
                continue;
            }
        }
        return sb;
    }


    public StringBuilder emboss(ArrayList<ArrayList<pixel>> pixelList){
        StringBuilder sb = new StringBuilder();
        sb.append("P3");
        sb.append("\n");
        sb.append(Integer.toString(width)+" "+Integer.toString(height));
        sb.append("\n");
        sb.append("255");
        sb.append("\n");
        int v;
        for (int r = 0; r < height; r++){
            for (int c = 0; c < width; c++) {
                if (c == 0 || r == 0){
                    v = 128;
                }
                else{
                    int redDiff = pixelList.get(r).get(c).getRed() - pixelList.get(r-1).get(c-1).getRed();
                    int greenDiff = pixelList.get(r).get(c).getGreen() - pixelList.get(r-1).get(c-1).getGreen();
                    int blueDiff = pixelList.get(r).get(c).getBlue() - pixelList.get(r-1).get(c-1).getBlue();

                    if (java.lang.Math.abs(redDiff) >= java.lang.Math.abs(greenDiff) && java.lang.Math.abs(redDiff) >= java.lang.Math.abs(blueDiff)){
                        v = redDiff + 128;
                    }
                    else if (java.lang.Math.abs(greenDiff) >= java.lang.Math.abs(redDiff) && java.lang.Math.abs(greenDiff) >= java.lang.Math.abs(blueDiff)){
                        v = greenDiff + 128;
                    }
                    else{
                        v = blueDiff + 128;
                    }
                    if(v<0){
                        v = 0;
                    }
                    else if(v>255){
                        v = 255;
                    }
                }
                sb.append(v);
                sb.append("\n");
                sb.append(v);
                sb.append("\n");
                sb.append(v);
                sb.append("\n");
            }
        }
        return sb;
    }


    public StringBuilder motionblur(ArrayList<ArrayList<pixel>> pixelList){
        StringBuilder sb = new StringBuilder();
        sb.append("P3");
        sb.append("\n");
        sb.append(Integer.toString(width)+" "+Integer.toString(height));
        sb.append("\n");
        sb.append("255");
        sb.append("\n");

        for (int r = 0; r < height; r++){
            for (int c = 0; c < width; c++) {
                int redTotal = 0;
                int greenTotal = 0;
                int blueTotal = 0;
                int finali = 0;

                for (int i = 0; i < blurLength; i++){
                    if(c+i < width){
                        redTotal += pixelList.get(r).get(c+i).getRed();
                        greenTotal += pixelList.get(r).get(c+i).getGreen();
                        blueTotal += pixelList.get(r).get(c+i).getBlue();
                        finali = i+1;
                    }
                }

                int redAvg = redTotal/finali;
                int greenAvg = greenTotal/finali;
                int blueAvg = blueTotal/finali;

                sb.append(redAvg);
                sb.append("\n");
                sb.append(greenAvg);
                sb.append("\n");
                sb.append(blueAvg);
                sb.append("\n");
            }
        }
        return sb;
    }
}
