// HelloUser2.java
// Prints greeting to stdout, then prints out some environment information.
class HelloUser2{
   public static void main( String[] args ){
      String userName = System.getProperty("user.name");
      
      System.out.println("Hey, wassup " + userName);
      System.out.println("this is a square:");
      for (int i = 0; i < 6; i++)
         System.out.println("******");
   }
}