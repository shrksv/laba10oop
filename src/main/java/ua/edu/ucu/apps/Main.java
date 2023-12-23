package ua.edu.ucu.apps;
public class Main {
    public static void main(String[] args) {
        SmartDocument document1 = new SmartDocument("2023.png");
        CashedDocument document2 = new CashedDocument(document1);
        System.out.println(document2.find());
    }
}