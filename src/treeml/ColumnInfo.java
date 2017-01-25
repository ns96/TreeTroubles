package treeml;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nathan
 */
public class ColumnInfo {
    private String name;
    private ArrayList<String> keyList = new ArrayList<String>();
    private ArrayList<String> valueList = new ArrayList<String>();
    private int length = 0;
    private boolean binaryLevels = false;
    private double min = 0;
    private double max = 0;
    
    /**
     * Store the column info
     * @param name
     * @param keys
     * @param values 
     */
    public ColumnInfo(String name, String[] keys, String[] values) {
        this.name = name;
        this.length = keys.length;
        
        for(int i  = 0; i < keys.length; i++) {
            keyList.add(keys[i]);
            valueList.add(values[i]);
        }
        
        // check to see if this is only binary levels or continues
        if(valueList.size() == 6) {
            if(valueList.get(0).equals("0.0") && valueList.get(5).equals("1.0")) {
                binaryLevels = true;
            } else {
                min = Double.parseDouble(valueList.get(0));
                max = Double.parseDouble(valueList.get(5));
            }
        }
    }
    
    /**
     * get the color value
     */
    public int[] getColorValue(String key) {
        int[] colorValue = new int[3];
        
        if(!name.equals("sidewalk")) {
            if(binaryLevels) {
                int gray =  (int)Double.parseDouble(key)*255;
                
                if(name.contains("spc_latin")) {
                    colorValue[0] = gray; // yellow
                    colorValue[1] = 0; // yellow
                    colorValue[2] = gray; // make this pixel blue
                } else {
                    colorValue[0] = colorValue[1] = colorValue[2] = gray;
                }
            } else if(name.equals("boro_name")) {
                int index = keyList.indexOf(key) + 1;
                float f = index/(float)length;
                int gray = (int)(f*255);
                colorValue[0] = colorValue[1] = colorValue[2] = gray;
            } else {
                double value = Double.parseDouble(key);
                int gray = -1;
                
                double d = (value - min)/(max - min);
                gray = (int)(d*255);
                
                if(gray < 0) {
                    gray = 0;
                } else if(gray > 255) {
                    gray = 255;
                }
                
                colorValue[0] = colorValue[1] = colorValue[2] = gray;
            }
        } else {
            if (key.equals("NoDamage")) {
                colorValue[0] = 0;
                colorValue[1] = 255; // green
                colorValue[2] = 0;
            } else {
                colorValue[0] = 255; // red
                colorValue[1] = 0;
                colorValue[2] = 0;
            }
        }
        
        return colorValue;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
