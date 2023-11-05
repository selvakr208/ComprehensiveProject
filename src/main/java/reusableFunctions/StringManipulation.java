package reusableFunctions;

public class StringManipulation {

    public String getStringWithIndex(String str, int startIndex, int endIndex){
        String str_1= str.substring(startIndex,endIndex);
        System.out.println(str_1);
        return str_1;

    }
    public String getStringWithIndex(String str, int startIndex){
        String str_1= str.substring(startIndex);
        System.out.println(str_1);
        return str_1;

    }
    
    public String getStringWithoutLastfewChar(String str, int numberOfLastFewCharToRemove) {
    	
    	int strLength= str.length();
    	String str_1 = str.substring(0,strLength-numberOfLastFewCharToRemove);
        System.out.println(str_1);
        return str_1;
    	
    }
    
 public String getStringWithoutSpace(String str) {
    		 
    	int strLength= str.length();
    	String str_1 = str.replaceAll("\\s+","");
        System.out.println(str_1);
        return str_1;
    	
    }
 
}
