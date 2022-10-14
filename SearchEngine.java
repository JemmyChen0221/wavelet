import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String> list = new ArrayList<String>();
    public String handleRequest(URI url) {
        
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                list.add(parameters[1]);
                return "added";
            } else{
                return "not added";
            }
        }
        else {
            if (url.getPath().contains("/search")) {
                String output = "";
                String[] para = url.getQuery().split("=");
                if (para[0].equals("s")) {
                    String check = para[1];
                    for(int i = 0; i < list.size(); i++){
                        if(list.get(i).contains(check)){
                            output = output + ", " + list.get(i);
                        }
                    }
                }
                if(output != ""){
                    return output.substring(1).strip();
                
                } else{
                    return "Nothing Found";
                }
            } else{
                return "Try to Add or Search Something!";
            }
            
        }
    }
}

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
