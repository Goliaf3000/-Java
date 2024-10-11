package Authorizations;

import java.util.HashMap;

public class Autho {
    private HashMap <String,String> account = new HashMap<>();

    public Autho(String email, String password) {
        account.put(email,password);
    }

    public Autho() {

    }

    public HashMap<String, String> getAccount() {
        return account;
    }

    public void setAccount(String str1, String str2) {
        account.put(str1,str2);
    }

    public void authentication(){
        ReadFileAndInputFile readFileAndInputFile = new ReadFileAndInputFile();
        readFileAndInputFile.writerFile(account);
    }

}
