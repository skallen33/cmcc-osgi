public class TestLoadLib {
    public static void main(String[] args){
        System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary("bundle");
        System.out.println("end");
    }
}
