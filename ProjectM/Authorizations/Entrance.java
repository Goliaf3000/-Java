package Authorizations;

import java.util.Scanner;

public class Entrance {
    public static void autho(){
        System.out.println("Введите пароль и почту для аккаунта, если аккаунт есть в базе, то вы будете авторизованы под ним");
        Scanner scan = new Scanner(System.in);
        Autho autho = new Autho(scan.nextLine(),scan.nextLine());

        autho.authentication();
    }

}
