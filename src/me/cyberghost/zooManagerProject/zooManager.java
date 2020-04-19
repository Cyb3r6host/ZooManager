package me.cyberghost.zooManagerProject;

import me.cyberghost.zooManagerProject.Gui.GuiPanel;
import me.cyberghost.zooManagerProject.Managers.Animal;
import me.cyberghost.zooManagerProject.Utilities.utils;

import javax.sql.rowset.serial.SerialException;
import javax.swing.*;
import java.awt.*;
import java.io.EOFException;
import java.util.*;

public class zooManager {
    public static HashMap<Integer,Animal> animals = new HashMap<>();
    public static Integer animalUUID = 0;
    private static boolean EnableGui = false;

    public static void main(String[] args){
        //Try to deSerialize Hashmap if fails Serilize the map

        animals = utils.deSerializeHashMap();
        try{
            animalUUID = animals.size();
        }catch (NullPointerException e){
            //Catch if animals hashmap is not generated!
        }
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Do you want to replace console create animal section with GUI?(yes/no): ");
        String gui = Scanner.nextLine();
        if(gui.equalsIgnoreCase("yes")){
            EnableGui=true;
            System.out.println("Create Section Replaced with GUI");
        }
        Boolean run =true;
        while (run){
            utils.drawMenu();
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter Your Option: ");
            Integer option = null;
            try {
                option = myObj.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please use a numerical value");
                continue;
            }
            Animal animal = null;
            switch (option) {
                case 1:
                    utils.showAllAvailableAnimals(animals);
                    break;
                case 2:
                    if(EnableGui){
                        utils.GUIcreate();
                    }else {
                        utils.createAnimal();
                    }
                    break;
                case 3:
                    Scanner nameScanner = new Scanner(System.in);
                    System.out.println("Enter Animals Name: ");
                    String name = nameScanner.nextLine();
                    ArrayList<Animal> allAnimals = utils.getAnimalByName(name);
                    if(!allAnimals.isEmpty()){
                        System.out.println("Found "+allAnimals.size()+ (allAnimals.size()>1 ? " Animals" : " Animal")+" with name "+name);
                        for(Animal tmp : allAnimals){
                            System.out.println("Animal:");
                            utils.showAnimalData(tmp);
                        }
                    }else{
                        System.out.println("Animal with name "+name +" not found!");
                    }
                    break;
                case 4:
                    Scanner passwordScanner = new Scanner(System.in);
                    System.out.println("Enter Animals Password: ");
                    String password = passwordScanner.nextLine();
                    if(utils.isPasswordUsed(password)) {
                        animal = utils.getAnimalByPassword(Integer.parseInt(password));
                        System.out.println("Animal:");
                        utils.showAnimalData(animal);
                    }else{
                        System.out.println("The password is invalid!");
                    }
                    break;
                case 5:
                    Scanner passwordScanner3 = new Scanner(System.in);
                    System.out.println("Enter Animals Password: ");
                    String password3 = passwordScanner3.nextLine();
                    if(utils.isPasswordUsed(password3)) {
                        animal = utils.getAnimalByPassword(Integer.parseInt(password3));
                        utils.editAnimalSection(animal);
                    }else{
                        System.out.println("The password is invalid!");
                    }

                    break;
                case 6:
                    Scanner passwordScanner2 = new Scanner(System.in);
                    System.out.println("Enter Animals Password: ");
                    String password2 = passwordScanner2.nextLine();
                    if(utils.isPasswordUsed(password2)) {
                     utils.deleteAnimalByPassword(Integer.parseInt(password2));
                    }else{
                        System.out.println("The password is invalid!");
                    }
                    break;
                case 7:
                    utils.serializeHashMap(animals);
                    run = false;
                    break;
            }
        }

    }
}
