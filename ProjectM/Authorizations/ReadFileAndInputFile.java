package Authorizations;

import HomeScreen.HomeScreen;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.nio.file.Files.lines;

public class ReadFileAndInputFile extends Autho{
    static HashMap<String, String> userName = new HashMap<>();
    Scanner scan = new Scanner(System.in);

    public ReadFileAndInputFile(String email, String password) {
        super(email, password);
    }

    public ReadFileAndInputFile() {
        super();
    }

    public File createFile() {
        File file = new File("Account.txt");
        if (!Files.exists(file.toPath())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    public void readFile() {
        String line;
        String[] lines;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("Account.txt"));
            while ((line = buffer.readLine()) != null) {
                lines = (line.replace("{", "")).replace("}", "").split("=");
                userName.put(lines[0], lines[1]);
            }
            /*for(Map.Entry<String,String> entry : userName.entrySet()){
                System.out.println(entry.getKey());
            }*/
            buffer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writerFile(HashMap<String, String> account) {
        readFile();
        if (checkAccounts(account)) {
            try {
                FileWriter writer = new FileWriter(createFile(), true);
                writer.write(String.valueOf(account + "\n"));
                writer.close();
                readFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            account.clear();
            System.out.println("У вас не было аккаунта, вы авторизованы");
        } else {
            System.out.println("У вас был аккаунт, вы авторизованы");
        }
    }

    public boolean checkAccounts(HashMap<String, String> account1) {
        boolean booleans1 = false;
        Map.Entry<String, String> entry1 = (Map.Entry<String, String>) account1.entrySet().iterator().next();
        for (Map.Entry<String, String> entry2 : userName.entrySet()) {
            rep:
            if (entry1.getKey().equals(entry2.getKey()) && entry1.getValue().equals(entry2.getValue())) {
                booleans1 = false;
                break;

            } else if ((entry1.getKey()).equals(entry2.getKey()) && !(entry1.getValue()).equals(entry2.getValue())) {
                System.out.println("Пользователь с такой почтой существует, используете другую почту, или укажите верный пароль для этого аккаунта");
                this.setAccount(scan.nextLine(),scan.nextLine());
                break rep;
            } else {
                account1.clear();
                booleans1 = true;
                break;
            }
        }
        return booleans1;
    }
}
