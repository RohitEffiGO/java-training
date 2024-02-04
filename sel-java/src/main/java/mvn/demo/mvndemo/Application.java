package mvn.demo.mvndemo;

import org.apache.commons.lang3.StringUtils;

public class Application{
    public int countWords(String s)
    {
        String [] s_arr = StringUtils.split(s," ");
        return (s_arr!= null) ? s_arr.length:0;
    }
    public static void main(String [] args)
    {
    	
    }
}
