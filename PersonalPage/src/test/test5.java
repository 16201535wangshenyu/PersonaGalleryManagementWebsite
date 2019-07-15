public class test5 {
    public static void main(String[] args) {
        String Filename="1.jpg";
        java.io.File iof =
                new java.io.File("src\\main\\webapp\\PersonalPage\\img/"+Filename);
        System.out.println(iof.getName());
    }
}
