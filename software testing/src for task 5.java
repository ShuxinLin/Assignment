import java.util.* ;
import java.io.* ;

public abstract class ClassPathTestCollector implements TestCollector {
   static final int SUFFIX_LENGTH = ".class".length();

   public ClassPathTestCollector() {
   }

   public Enumeration collectTests() {
       String classPath = System.getProperty("java.class.path");
       Hashtable result = this.collectFilesInPath(classPath);
       return result.elements();
   }

   public Hashtable collectFilesInPath(String classPath) {
       Hashtable result = this.collectFilesInRoots(this.splitClassPath(classPath));
       return result;
   }

   Hashtable collectFilesInRoots(Vector roots) {
       Hashtable result = new Hashtable(100);
       Enumeration e = roots.elements();

       while(e.hasMoreElements()) {
           this.gatherFiles(new File((String)e.nextElement()), "", result);
       }

       return result;
   }

   void gatherFiles(File classRoot, String classFileName, Hashtable result) {
       File thisRoot = new File(classRoot, classFileName);
       if(thisRoot.isFile()) {
           if(this.isTestClass(classFileName)) {
               String var7 = this.classNameFromFile(classFileName);
               result.put(var7, var7);
           }

       } else {
           String[] contents = thisRoot.list();
           if(contents != null) {
               for(int i = 0; i < contents.length; ++i) {
                   this.gatherFiles(classRoot, classFileName + File.separatorChar + contents[i], result);
               }
           }

       }
   }

   Vector splitClassPath(String classPath) {
       Vector result = new Vector();
       String separator = System.getProperty("path.separator");
       StringTokenizer tokenizer = new StringTokenizer(classPath, separator);

       while(tokenizer.hasMoreTokens()) {
           result.addElement(tokenizer.nextToken());
       }

       return result;
   }

   protected boolean isTestClass(String classFileName) {
       return classFileName.endsWith(".class") && classFileName.indexOf(36) < 0 && classFileName.indexOf("Test") > 0;
   }

   protected String classNameFromFile(String classFileName) {
       String s = classFileName.substring(0, classFileName.length() - SUFFIX_LENGTH);
       String s2 = s.replace(File.separatorChar, '.');
       return s2.startsWith(".")?s2.substring(1):s2;
   }
}