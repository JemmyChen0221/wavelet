import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {
        ArrayList<String> list = new ArrayList<String>();
        if (url.getPath().contains("/add")) {
            

            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                list.add(parameters[1]);
                return list.get(0);

            }
            return "Not Added";

        }else if(url.getPath().contains("/show")){
            String word = "";
            return word;
            /*for(int i = 0; i < list.size();i ++){
                word = word + "," + list.get(i);
            }
            return word;*/

        }
        else {
            if (url.getPath().contains("/search")) {
                String[] para = url.getQuery().split("=");
                if (para[0].equals("s")) {
                    String check = para[1];
                    String output = "";
                    for(int i = 0; i < list.size(); i++){
                        if(list.get(i).contains(check)){
                            return "found";
                        }
                    }

                    
                    //return String.valueOf(count);
                }
            } else{
                return "Try to Add or Search Something!";
            }
            return "Welcome";
            
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
