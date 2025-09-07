package ru.otus.java.basic.homeworks.homework4;

public class Homework4 {
    public static void main(String[] args) {
        User[] usersArray = new User[10];
        usersArray[0] = new User("Vorontsov", "Dmitry", "Arsentievich", 1967, "pruhohoixiquoi-1262@yopmail.com");
        usersArray[1] = new User("Popova", "Sofya", "Egorovna", 2003, "jeiddeullaucrayo-8173@yopmail.com");
        usersArray[2] = new User("Sergeev", "Yaroslav", "Denisovich", 2001, "jabaceullugi-3007@yopmail.com");
        usersArray[3] = new User("Samoilov", "Tikhon", "Andreevich", 1999, "fajoprateiyou-5369@yopmail.com");
        usersArray[4] = new User("Nikolaeva", "Maria", "Andreevna", 1966, "bonafutregrei-8304@yopmail.com");
        usersArray[5] = new User("Fomicheva", "Anna", "Serafimovna", 2013, "zeimoinnoubrimi-3142@yopmail.com");
        usersArray[6] = new User("Zaitseva", "Darya", "Andreevna", 1977, "wacayupeunni-2979@yopmail.com");
        usersArray[7] = new User("Sorokin", "Maxim", "Konstantinovich", 1993, "siffeimmipreddau-9594@yopmail.com");
        usersArray[8] = new User("Golovin", "Roman", "Igorevich", 2000, "weiwoivanniho-3588@yopmail.com");
        usersArray[9] = new User("Tarasov", "Ilya", "Borisovich", 2004, "cemmigroimmoimmei-4772@yopmail.com");

        for (int i = 0; i < usersArray.length; i++) {
            if (java.time.LocalDate.now().getYear() - usersArray[i].getBirthday() > 40){
                usersArray[i].printInfo();
            }
        }

        Box box1 = new Box("Black", 150, 150, 50);
        box1.printInfo();

        box1.setColor("White");

        box1.printInfo();

        box1.open();
        box1.putItem("Шарик");
        box1.putItem("Гвозди");
        box1.removeItem();
        box1.removeItem();

    }
}

