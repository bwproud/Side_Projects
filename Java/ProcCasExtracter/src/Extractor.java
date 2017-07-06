import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 * Reads a file and adds the proc steps, data steps, and intro comment to a list
 * while removing comments
 * @author brprou
 */
public class Extractor {
	/**
	 * list containing the intro comment, the data steps, and the proc steps
	 */
	static List<String[]> steps = new ArrayList<>();
	
	/**
     * Reads the contents of a file into a string
     * @param path The location of the file to be read into a string
     * @param startIndex the index where the FileInputStream begins reading.
     * Everything earlier than this index is discarded
     * @return A String containing the contents of the file found in the location
     * specified by path
     */
    private static String fisRead(){
        File f = new File(System.getProperty("user.dir") + "\\cdtabv.sas");
        FileInputStream fis = null;
        String text="";

        try {
            if (f.exists()) {
                fis = new FileInputStream(f);
                Integer fileLength = (int) (long) f.length();
                byte[] b = new byte[fileLength];
                int read = 0;

                if(read>0)
                    fis.skip(read);

                while (read < fileLength)
                {
                    read += fis.read(b, 0, b.length);
                }
                text = new String(b);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(fis!=null)
                    fis.close();

            }
            catch (Exception e)
            {
                System.out.println("Error closing file stream");
                e.printStackTrace();
            }
        }

        return text;
    }
    
    /**
     * removes comments from the file aside from the intro comment
     */
    public static String cleanString(String s){
    	String cleanS = "";
    	if(s.substring(0,2).equals("/*")){
    		cleanS+= s.substring(0, s.indexOf("*/")+2);
    		String[] commands = new String[1];
        	commands[0]=cleanS;
        	steps.add(commands);
    	}
    	while(s.contains("/*")){
    		cleanS+=s.substring(0, s.indexOf("/*"));
    		s=s.substring(s.indexOf("*/")+2);
    	}
		cleanS+=s;
    	return cleanS;
    }
    
    /**
     * Goes through the file and adds procs and data steps to a list
     */
    public static void addList(String s){
    	while(s.contains("proc")||s.contains("data")){
    		if(s.indexOf("proc")==-1){
    			s = addData(s.substring(s.indexOf("data")));
    		}
    		else if(s.indexOf("data")==-1){
    			s = addProc(s.substring(s.indexOf("proc")));
    		}
    		else if(s.indexOf("proc")<s.indexOf("data")){
    			s = addProc(s.substring(s.indexOf("proc")));
    		}
    		else{
    			s = addData(s.substring(s.indexOf("data")));
    		}  		
    	}
    }
    
    /**
     * Adds data steps to List in the form of a string array with the first entry
     * containing the whole step
     */
    public static String addData(String s){
    	String[] commands = new String[1];
    	commands[0]=s.substring(0, s.indexOf("run;")+4);
    	steps.add(commands);
    	return s.substring(s.indexOf("run;")+4);
    }
    
    /**
     * Adds proc steps to List in the form of a string array with each command
     * being a seperate entry ending in a semicolon
     */
    public static String addProc(String s){
    	ArrayList<String> com = new ArrayList<>();
    	String copy = s.substring(0, s.indexOf("quit;")+5);
    	while(copy.contains(";")){
    		int index = copy.indexOf(";");
    		int count= StringUtils.countMatches(copy.substring(0, index), "\"");
    		if(count%2!=0){
    			copy=copy.substring(0, index)+"B$W$P"+copy.substring(index+1);
    			continue;
    		}
    		com.add(copy.substring(0, index+1));
    		copy=copy.substring(index+1);
    	}
    	String[] command = com.toArray(new String[com.size()]);
    	String[] commands = new String[command.length-3];
    	for(int i =2; i< command.length-1; i++){
    		command[i]=command[i].replace("B$W$P", ";");
    		commands[i-2]=command[i].trim();
    	}
    	steps.add(commands);
    	return s.substring(s.indexOf("quit;")+5);
    }
    
	public static void main(String args[]){
		String s = fisRead();
		s= cleanString(s);
		addList(s);
		for(String[] step : steps){
			System.out.println("/**********************************************************************/");
			System.out.println("/******************************"+step[0].substring(0, step[0].indexOf(" "))+" step*******************************/");
			System.out.println("/**********************************************************************/");
			System.out.println();
			for(int i = 0; i< step.length; i++){
				System.out.println(step[i]);
			}
		}
	}
}
