import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class Extractor2 {
	static List<String[]> procSteps = new ArrayList<>();
	static List<String> dataSteps = new ArrayList<>();
	static String introComment="";
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
    
    public static String cleanString(String s){
    	String cleanS = "";
    	if(s.substring(0,2).equals("/*")){
    		cleanS+= s.substring(0, s.indexOf("*/")+2);
        	introComment = cleanS;
    	}
    	while(s.contains("/*")){
    		cleanS+=s.substring(0, s.indexOf("/*"));
    		s=s.substring(s.indexOf("*/")+2);
    	}
		cleanS+=s;
    	return cleanS;
    }
    
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
    
    public static String addData(String s){
    	dataSteps.add(s.substring(0, s.indexOf("run;")+4));
    	return s.substring(s.indexOf("run;")+4);
    }
    
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
    	procSteps.add(commands);
    	return s.substring(s.indexOf("quit;")+5);
    }
    
	public static void main(String args[]){
		String s = fisRead();
		s= cleanString(s);
		addList(s);
		System.out.println(introComment+"\n");
		System.out.println("/**********************************************************************/");
		System.out.println("/*****************************"+"data steps*******************************/");
		System.out.println("/**********************************************************************/");
		System.out.println();
		for(String data : dataSteps){
			System.out.println(data+"\n\n\n");
		}
		System.out.println("/**********************************************************************/");
		System.out.println("/*****************************"+"proc steps*******************************/");
		System.out.println("/**********************************************************************/");
		System.out.println();
		for(String[] proc : procSteps){
			for(int i = 0; i< proc.length; i++){
				System.out.println(proc[i]);
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
}
