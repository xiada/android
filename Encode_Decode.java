import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Encode_Decode {
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
'A', 'B', 'C', 'D', 'E', 'F' };
    static List<String> paths = new ArrayList<String>();;

    public static void main(String[] args)
    {
    	File localFile;    	
    	File[] arrayOfFile = new File("F:/rom/国威创新新机型D6/app/").listFiles();
    	for (int i=0;i<arrayOfFile.length;i++){
    		localFile = arrayOfFile[i];
    		if(localFile.getName().contains("apk")){
    			paths.add(md5sum(localFile.getPath()));
    			System.out.println(arrayOfFile[i].getName());
    		}
    		
    		
    		
    	}
    	
    	System.out.println(checkMd5());
       
    }

    public static String checkMd5()
    {
      String str = new String("00000000000000000000000000000000");
      for (int i = 0; i < paths.size(); i++)
        str = StringAddFunction(str, (String)paths.get(i));
      return str;
    }
    
    
    private static String StringAddFunction(String paramString1, String paramString2) {		
    	StringBuilder localStringBuilder = new StringBuilder(2 * paramString2.length());
    	for (int i=0;i<paramString2.length();i++){
    		int j;
    		int k;
    		if(paramString1.charAt(i)>='A'){
    			j = 10 + (paramString1.charAt(i) - 'A');
    		}
    		else{
    			j = paramString1.charAt(i);
    		}
    		if(paramString2.charAt(i)>='A'){
    			k = 10 + (paramString2.charAt(i) - 'A');
    		}
    		else{
    			k = paramString2.charAt(i);
    		}
    		
    		
    		int m = (k + j) % 16;
    		localStringBuilder.append(HEX_DIGITS[m]);
    	}
		return localStringBuilder.toString();
	}

	public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String md5sum(String filename) {
        InputStream fis;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;
        try{
            fis = new FileInputStream(filename);
            md5 = MessageDigest.getInstance("MD5");
            while((numRead=fis.read(buffer)) > 0) {
                md5.update(buffer,0,numRead);
            }
            fis.close();
            return toHexString(md5.digest());  
        } catch (Exception e) {
            System.out.println("error");
            return null;
        }
    }
}
