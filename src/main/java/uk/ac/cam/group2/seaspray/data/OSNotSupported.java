package uk.ac.cam.group2.seaspray.data;

public class OSNotSupported extends Exception {
    String os;
    public String getMessage(){
        return "Unsupported OS: "+os;
    }
    public OSNotSupported(){}
    public OSNotSupported(String s){os = s;}
}