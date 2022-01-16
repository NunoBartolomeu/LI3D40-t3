package Classes;

import java.util.Scanner;  // Import the Scanner class

public class DefaultClassMaker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter table name: ");
        String tableName = input.nextLine();
        
        System.out.print("Enter number of atributes: ");
        int numberOfAtributes = input.nextInt();
        //Removes "\n" left in input after "nextInt()";
        input.nextLine();

        String [] atributeTypes = new String[numberOfAtributes];
        String [] atributeNames = new String[numberOfAtributes];

        String atribute;
        String [] split;

        for (int i = 0; i < numberOfAtributes; i++) {
            System.out.print("Enter type and name of atribute " + i + ": ");
            atribute = input.nextLine();
            split = atribute.split(" ");
            atributeTypes[i] = split[0];
            atributeNames[i] = split[1];
        }

        //Output

        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////");
        //Classe
        System.out.println("public class " + tableName + " {\n");
        
        //Atributos
        System.out.println("//Atributos");
        for (int i = 0; i < numberOfAtributes; i++) {
            System.out.println("private " + atributeTypes[i] + " " + atributeNames[i] + ";");
        }

        //Constutores
        System.out.println("\n//Constutores");
        //Parametos do construtor
        System.out.print("public " + tableName + " (");
        for (int i = 0; i < numberOfAtributes; i++) {
            System.out.print(atributeTypes[i] + " " + atributeNames[i] + ", ");
        }
        System.out.println(") {");
        //This
        for (int i = 0; i < numberOfAtributes; i++) {
            System.out.println("this." + atributeNames[i] + " = " + atributeNames[i] + ";");
        }
        System.out.println("}\n");

        //Geters
        System.out.println("//Geters");
        for (int i = 0; i < numberOfAtributes; i++) {
            System.out.println("public " + atributeTypes[i] + " get_" + atributeNames[i] + " () {return " + atributeNames[i] + ";}");
        }

        //Seters
        System.out.println("\n//Seters");
        for (int i = 0; i < numberOfAtributes; i++) {
            System.out.println("public void set_" + atributeNames[i] + " (" + atributeTypes[i] + " " + atributeNames[i] + ") {this." + atributeNames[i] + " = " + atributeNames[i] + ";}");
        }
        /*
        //Main
        System.out.println("\npublic static void main(String[] args) {");
        System.out.println(tableName + " obj = new " + tableName + "(\"<Fill this>\");");
        for (int i = 0; i < numberOfAtributes; i++) {
            System.out.println("\n//Test " + atributeNames[i]);
            System.out.println("System.out.println(\"was: \" + obj.get_" + atributeNames[i] + "());");
            System.out.println("obj.set_" + atributeNames[i] + "(\"<Fill this>\");");
            System.out.println("System.out.println(\"is: \" + obj.get_" + atributeNames[i] + "());\n");
        }
        System.out.println("}\n}");
        */
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////");

        input.close();
    }
}

