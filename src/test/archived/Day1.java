import org.testng.annotations.Test;

import java.util.Random;

public class Day1 {
// Funny thing:
// Tests are run out of order, hence numbered lines in the output/

    @Test
    public void testSomethingSmall() {
        System.out.println("1: Pogo!");
    }

    @Test
    public void testPrints() {
        int meMonkeys = 666;

        System.out.println("2: Pogoing monkeys: " + meMonkeys + ", all screeching loudly!"); // And we have type implicit type conversion
    }

    @Test
    public void testBooleans() {
        Boolean toBe = false, toBee = false;
        long i = System.currentTimeMillis()%7; // ad some variations
        toBee = i%2 == 1;
        System.out.println("3: The answer is... " + ((toBe || !toBee)?"To Be!":"Two Bee... What?!")); // Ternary... Ugly when overused
        System.out.println("   --> Number of bee halves: " + (i%2));
        if(toBee) System.out.println("   --> I will name it... Eric!"); // One-liners are not that bad. Monty Python? Good to the last drop.
    }


    @Test
    public void testArrays() {
        char[] arr = {'P', 'o', 'g', 'o', '!'};

        System.out.print("4: Array contains: ");
        System.out.println(arr); // Outputs a string. Will output some garbage if we try to put it into a println() with a string ad concatenate
        System.out.println("   --> This will output garbage: arr[] = " + arr); // What's this exactly?.. A pointer to the object, I think
        // Looks like the line above dumps something that's not a traditional (C/Pascal) pointer... [C@6108b2d7
        // Never changes and obviously is a kind of ID
        System.out.println("   --> Array length: " + arr.length);
        System.out.println("   --> Array elements:");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("      arr[" + i + "] is '" + arr[i] + "'");
        }



    }


}
