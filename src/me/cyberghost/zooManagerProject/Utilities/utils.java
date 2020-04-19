package me.cyberghost.zooManagerProject.Utilities;

import me.cyberghost.zooManagerProject.Gui.GuiPanel;
import me.cyberghost.zooManagerProject.Managers.Animal;
import me.cyberghost.zooManagerProject.zooManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;

public class utils {
    /**
     * Function that serialize all the animals!
     * This will help in de-serialization and will
     * tbe easy for us to use hem again
     * for feature purposes!
     *
     * This function will triggered at the end
     * of our program so it will save all our
     * data into the .ser file
     *
     * @param map
     */
    public static void serializeHashMap(HashMap<Integer, Animal> map ){
        try {
            FileOutputStream fos = new FileOutputStream("./animals.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
            System.out.println("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Function that de-serialize all the animals
     * from the .ser file!
     *
     * This function will triggered at the start
     * of our program so it will be easy for us
     * to manipulate the data inside the hashmap!
     *
     * @return  Returns our saved Hashmap
     */
    public static HashMap<Integer,Animal> deSerializeHashMap(){
        HashMap<Integer, Animal> map = null;
        try {
            FileInputStream fis = new FileInputStream("./animals.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap<Integer, Animal>) ois.readObject();
            ois.close();
            fis.close();
        } catch (EOFException e){
            serializeHashMap(zooManager.animals);
        } catch(IOException ioe){

            ioe.printStackTrace();
        }catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        System.out.println("Map De-Serialized Successfully!");
        return map;
    }

    /**
     * This function deletes the animal
     * with the specific password
     * if there isnt any animal with the
     * specific password it returns error
     * message!
     *
     * @param password
     */
    public static void deleteAnimalByPassword(Integer password){
        for(Animal tmp : zooManager.animals.values()){
            if(tmp.getPassword().equals(password)){
                zooManager.animals.remove(getValuesKey(tmp));
                System.out.println("Animal with password "+password+" Removed Successfully!");
                return;
            }

        }
        System.out.println("Animal with password "+password+" not found!");

    }

    /**
     * Simple function
     * that retrieves specific animal
     * by its password
     * @param password
     * @return
     */
    public static Animal getAnimalByPassword(Integer password){
        for(Animal tmp : zooManager.animals.values()){
            if(tmp.getPassword().equals(password)){
                return tmp;
            }
        }
        return null;
    }
    /**
     * Simple function
     * that retrieves all animals
     * by a specific name
     * @param name
     * @return
     */
    public static ArrayList<Animal> getAnimalByName(String name){
        ArrayList<Animal> allAnimals = new ArrayList<>();
        for(Animal tmp : zooManager.animals.values()){
            if(tmp.getName().equals(name)){
                allAnimals.add(tmp);
            }
        }
        return allAnimals;
    }

    /**
     * This function takes
     * an Animal that already exists
     * and Manipulate its Arguments
     * @param animal
     */
    public static void editAnimalSection(Animal animal){
        System.out.println("----------------------------------");
        System.out.println("Welcome to animal Edit Section\nHere you can edit all animal values\nLeave the value blank if you want\nthe value to be the same!");
        System.out.println("----------------------------------");
        Scanner myObj = new Scanner(System.in);

        //Name Edit Section
        System.out.println("UserName: ");
        String name = myObj.nextLine();

        //Password edit Section
        System.out.println("Password: ");
        String password = myObj.nextLine();
        password= passwordInput(password,"The password that you enter is either not valid either used by another animal!",true);
        //AnimalClass Edit section
        System.out.println("AnimalClass: ");
        String animalClass = myObj.nextLine();
        while(!isAnimalClassValid(animalClass) && animalClass.length()>1){
            System.out.println("This is not a valid Animal Class");
            animalClass = myObj.nextLine();
        }

        //Weight edit Section
        System.out.println("Weight: ");
        String weight = myObj.nextLine();
        weight = userInput(weight,"Weight is not valid",true);

        //Age edit Section
        System.out.println("Age: ");
        String age = myObj.nextLine();
        age = userInput(age,"Age is not valid",true);

        //Validate the blank inputs
        name = name.length()>0 ? name : animal.getName();
        animalClass = animalClass.length()>0 ? animalClass : animal.getAnimalClass();
        weight = weight.length()>0 ? weight : animal.getWeight().toString();
        age = age.length()>0 ? age : animal.getMaxAge().toString();
        password = password.length()>0 ? password : animal.getPassword().toString();
        //Confirm Section
        System.out.println("----------------------------------");
        System.out.println("Are you sure you want to Save Edits?");
        System.out.println("Animal Name: "+name );
        System.out.println("Animal Class: "+animalClass );
        System.out.println("Animal Weight: "+weight );
        System.out.println("Animal Age: "+age );
        System.out.println("Animal Password: "+password );
        System.out.println("----------------------------------");
        System.out.println("Confirm Changes(yes/no): ");
        String confirm = myObj.nextLine();
        if(confirm.equalsIgnoreCase("yes")){
            animal.setName(name);
            animal.setAnimalClass(animalClass);
            animal.setWeight(Double.parseDouble(weight));
            animal.setMaxAge(Double.parseDouble(age));
            animal.setPassword(Integer.parseInt(password));
            System.out.println("Changes Saved Successfully");
        }else{
            System.out.println("Edit Canceled Successfully");
        }

    }
    public static  void createAnimal(){
        System.out.println("----------------------------------");
        System.out.println("Welcome to Create Section\nHere you can add new animals into the Database!");
        System.out.println("----------------------------------");
        Scanner myObj = new Scanner(System.in);
        System.out.println("UserName: ");
        String name = myObj.nextLine();

        System.out.println("Password: ");
        String password = myObj.nextLine();
        password= passwordInput(password,"The password that you enter is either not valid either used by another animal!",false);

        System.out.println("AnimalClass: ");
        String animalClass = myObj.nextLine();
        while(!isAnimalClassValid(animalClass)){
            System.out.println("This is not a valid Animal Class");
            animalClass = myObj.nextLine();
        }
        //Weight edit Section
        System.out.println("Weight: ");
        String weight = myObj.nextLine();
        weight = userInput(weight,"Weight is not valid",false);

        //Age edit Section
        System.out.println("Age: ");
        String age = myObj.nextLine();
        age = userInput(age,"Age is not valid",false);
        Animal newAnimal  = new Animal(Integer.parseInt(password),name,animalClass,Double.parseDouble(weight),Double.parseDouble(age));
        zooManager.animals.put(zooManager.animalUUID++,newAnimal);
        System.out.println("New Animal Created!");
        showAnimalData(newAnimal);

    }

    // Some functions for specific checks

    public static boolean isAnimalClassValid(String animalClass){
        if (animalClass.toLowerCase().matches("mammals|birds|fish|reptiles|amphibians|arthropods")) {
            return true;
        }
        return false;
    }

    /**
     * This function checks if
     * there is any animal with the specified password
     *
     * @param password
     * @return
     */
    public static boolean isPasswordUsed(String password){
        try{
            Integer newpassword = Integer.parseInt(password);
            if(getAnimalByPassword(newpassword) ==null) { //Checks if there is any animal with same password
                return false;
            }else{
                return true;
            }

        }catch (NumberFormatException test){
            return false;
        }
    }

    public static boolean isDouble(String doublenum){
        try{
            Double.parseDouble(doublenum);
            return true;
        }catch (NumberFormatException test){
            return false;
        }
    }

    public static boolean isInteger(String integer){
        try{
            Integer.parseInt(integer);
            return true;
        }catch (NumberFormatException test){
            return false;
        }
    }

    /**
     * Simple Function that Draws animals data
     * @param animal
     */
    public static void showAnimalData(Animal animal){
        if(zooManager.animals.isEmpty()){
            System.out.println("There are not animals available");
            return;
        }
        System.out.println("   Name: "+animal.getName());
        System.out.println("   Password: "+animal.getPassword());
        System.out.println("   AnimalClass: "+animal.getAnimalClass());
        System.out.println("   Weight: "+animal.getWeight());
        System.out.println("   MaxAge: "+animal.getMaxAge());
    }

    /**
     * This function iterates through
     * the entire hashmap
     * and displays all available
     * Animals ants its values
     * @param map
     */
    public static void showAllAvailableAnimals(HashMap<Integer,Animal> map){
        if(zooManager.animals.isEmpty()){
            System.out.println("There are not animals available");
            return;
        }
        System.out.println("----------------------------------");
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Animal tmp = (Animal) pair.getValue();
            System.out.println("Animal: "+pair.getKey());
            showAnimalData(tmp); //Calls The show Specific Animal data function
        }
        System.out.println("----------------------------------");
    }
    /**
     * Draw main menu
     */
    public static void drawMenu(){
            System.out.println("----------------------------------");
            System.out.println("            ZooManager");
            System.out.println("1) Show all available Animals");
            System.out.println("2) Add New Animal");
            System.out.println("3) Search by Name");
            System.out.println("4) Search by Password");
            System.out.println("5) Edit by Password");
            System.out.println("6) Delete animal by password");
            System.out.println("7) Exit and Save");
            System.out.println("----------------------------------");
    }

    /**
     * Function that returns the key value of
     * Specific animal
     * @param animal
     * @return
     */
    private  static Integer getValuesKey(Animal animal){
        for (Map.Entry<Integer, Animal> entry : zooManager.animals.entrySet()) {
            if (entry.getValue().equals(animal)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Function that handles the user input (integers)
     * for edit and the create section
     * alloBlank bollean is for the edit section
     * @param input
     * @param errorMesage
     * @param allowblank
     * @return
     */
    private static String userInput(String input,String errorMesage,Boolean allowblank){
        Scanner myObj = new Scanner(System.in);
        if(allowblank) {
            while ((!isDouble(input) && input.length() >= 1)) { //Checks if input isnot and integer or if password is used  if password is blank continue
                System.out.println(errorMesage);
                input = myObj.nextLine();
            }
        }else{
            while (!isDouble(input)) {
                System.out.println(errorMesage);
                input = myObj.nextLine();
            }

        }
        return input;
    }
    private static String passwordInput(String input,String errorMesage,Boolean allowblank){
        Scanner myObj = new Scanner(System.in);
        if(allowblank) {
            while ((!isInteger(input) && input.length() >= 1) || isPasswordUsed(input)) { //Checks if input isnot and integer or if password is used  if password is blank continue
                System.out.println(errorMesage);
                input = myObj.nextLine();
            }
        }else{
            while (!isInteger(input) || isPasswordUsed(input)) {
                System.out.println(errorMesage);
                input = myObj.nextLine();
            }

        }
        return input;
    }

    /**
     * A simple Gui implimentation
     * for Animal Create Section
     */
    public static void GUIcreate(){
        JFrame frame = new JFrame ("ZooManager");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Do you want to save progress?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if ( confirm == JOptionPane.YES_NO_OPTION){
                    utils.serializeHashMap(zooManager.animals);
                }else if(confirm == -1){
                    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    return;
                }
                frame.dispose();

            }
        });
        frame.getContentPane().add (new GuiPanel());
        frame.setBackground(Color.DARK_GRAY);
        frame.pack();
        frame.setVisible (true);
    }

}
