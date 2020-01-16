import org.testng.annotations.Test;

public class Day2 {

/*
Printing even numbers from 1 to 10.
For odd numbers we can change the condition either to:
  i%2 == 1 OR
  i%2 != 0
Both would produce same result

Remember to check the requirements and plan ahead!
Also, pseudocode is a great tool for that.
*/

    @Test
    public void testForLoop() {
        int count = 0;

        System.out.println("5: _for_ loop which prints out even numbers from 1 to 10");
        for (int i = 1; i <= 10; i++) {
            // We can use _for_ loop to output our even numbers
            // Also, let's count the total while we're at it
            // Taking care to increment count _before_ printing it out!
            if (i % 2 == 0) System.out.println("   --> Even number: " + i + "\tPrinted total: " + ++count);

        }
    }

    @Test
    public void testWhileLoop() {
        int count = 0, i = 0;

        System.out.println("6: _do .. while_ loop which prints out even numbers from 1 to 10");
        do {
            // We also can use _do .. while_ loop and check the condition after each loop
            // Could be useful for the cases when we have to check for other conditions as well
            // Said conditions may be changed by the code within the loop's body
            // Using _while_ loop would be the same as _for_ loop
            i++;
            if (i % 2 == 0) System.out.println("   --> Even number: " + i + "\tPrinted total: " + ++count);

        } while (i < 10);

    }


    @Test
    public void testArraySwap() {
        // Create test array first. If there's no array there's no elements to swap, innit?
        // We'll need to swap elements 1 and 2 as well as 5 and 6
        char[] pogoArray = {'p', 'g', 'o', 'o', ' ', 't', 's', 'i', 'c', 'k'};

        System.out.print("Array was: ");
        System.out.println(pogoArray);

        swapArray(pogoArray, 1, 2);
        System.out.print("Swapping elements 1 and 2: ");
        System.out.println(pogoArray);

        swapArray(pogoArray, 5, 6);
        System.out.print("Swapping elements 5 and 6: ");
        System.out.println(pogoArray);

        System.out.println("And now we check it with wrong indexes:");
        swapArray(pogoArray, -5, 6);



    }

    private void swapArray(char[] arr, int idx1, int idx2){
        char temp = '\n'; // Just a placeholder

/*
        Version without boundary checking:

        temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;

        It's always a good idea to check whether data is sane.
        We have to make sure:
        1. idx1 and idx2 are within the array's length and
        2. They're not the same (optional, but a good idea)
*/

        if(idx1!=idx2)
        {
            int l = arr.length; // Function calls are expensive, let's reduce those
            if((0 <= idx1) && (idx1 < l) && (0 <= idx2) && (idx2 < l)) // ALMOST forgot to check for negatives!
            {
                temp = arr[idx1];
                arr[idx1] = arr[idx2];
                arr[idx2] = temp;
            } else {
                // Should be proper error handling routine here
                System.out.println("Index(es) are out of bounds: ");
                System.out.println("--> array length: "+ l);
                System.out.println("--> idx1: " + idx1);
                System.out.println("--> idx2: " + idx2);
            }
        }

    }

    @Test
    public void testEnhancedForSyntax() {
        String sillyWords[] = {"Sycamore", "Putty", "Potty", "Colander", "Batrachomyomachia"};
        int n = 0; // Let's count the words as well. This variable holds the word count.

        for(String sillyWord : sillyWords) // _for_ loop... _for each_ in other languages
        {
            System.out.println("Silly word #" + ++n + " is: " + sillyWord);
        }
        System.out.println("Total: " + n + " words. Should be: " + sillyWords.length);
        // Of course both numbers must be equal. Otherwise... get a new computer.

    }

    @Test
    public void testLetterCounter() {
        String inputString = "Buffalo buffalo buffalo buffalo ... repeated about 16 times. The phrase is quite famous for being a complete grammatically correct sentence having a real meaning all the while containing only the word 'buffalo'.";
        // Let's count letters 'b' and 'e' in this string... because why not?
        // Create an array of char...
        // Let's assume we need to count all the instances of those letters regardless of the case...

        // Turning the string to lowercase letters and converting it to the array of char...
        char inputArrayLowers[] =  inputString.toLowerCase().toCharArray();
        // Could've used .toUpperCase() – no difference, AFAIK
        // But lowercase looks better in the _switch_ below.

        // And this one will be used to demonstrate the difference
        char inputArrayRaw[] = inputString.toCharArray();

        // Now let's initialize our counters...
        int countBAllLower = 0, countBRawString = 0; // Count our 'B's...
        int countEAllLower = 0, countERawString = 0; // ... and our 'C's

        // Counting the characters with all characters converted to lowercase...
        // We're using _switch_ and not _if_ mostly for aesthetic reasons and readability
        System.out.println("String with all lowercase letters...");
        for(char c : inputArrayLowers) {
            switch (c){
                case 'b':
                    countBAllLower++;
                    System.out.print('+');
                    break;
                case 'e':
                    countEAllLower++;
                    System.out.print('+');
                    break;
                default:
                    System.out.print('.');
            }
        }
        System.out.println("");

        // Counting the characters without conversion to lowercase...
        System.out.println("And now for the source string...");
        for(char c : inputArrayRaw) {
            switch (c){
                case 'b':
                    countBRawString++;
                    System.out.print('+');
                    break;
                case 'e':
                    countERawString++;
                    System.out.print('+');
                    break;
                default:
                    System.out.print('.');
            }
        }
        System.out.println("");
        
        System.out.println("Found " + countBAllLower + " lower-case Bs vs " + countBRawString + " Bs in the raw string");
        System.out.println("Found " + countEAllLower + " lower-case Es vs " + countERawString + " Es in the raw string");
    }

    @Test
    public void testPalindromes() {
        // Testing simple boolean function... which worked.
        if (isPalindrome("I was Eve saw I"))
            System.out.println("Palindrome here!");
        else
            System.out.println("Ain't no palindrome here!");
    }

    private boolean isPalindrome(String suspectedPalindrome) {
        int strLen = suspectedPalindrome.length(); // String length
        int firstHalf = 0; // Half the length – not counting the character in the middle if strLen is an odd number

        String s = suspectedPalindrome.toLowerCase(); // We'll have to compare characters... and 'C' != 'c'

        if(strLen % 2 == 0)
            firstHalf = (strLen / 2) - 1;
         else
            firstHalf = ((strLen + 1) / 2) - 2;

        for(int i = 0; i < firstHalf; i++) {
            if ( s.charAt(i) != s.charAt(strLen-1-i)) return false; // Ain't no palindrome!
        }

        return true;

    }

    @Test
    public void testPerson() {
        // Instead of an Actor class I'll use Person. More... generic, I guess?
        Person p1 = new Person("John", "Doe"); // Should fire off a message to the console
        Person p2 = new Person("Zhang", "San", true); // Should fire off a message to the console
        Person p3 = new Person("Aram", "Mara");

        System.out.printf("Person 1: %s\n", p1.fullName());
        System.out.printf("Person 2: %s\n", p2.fullName());

        // Let's perform some method calls...
        p1.eat();p1.drink();
        p2.eat();p2.drink();

        // Now... eat, drink and be merry! Yay, polymorphism!
        p1.eat("kebabs"); p1.drink("beer");p1.beMerry();
        p2.eat("wonton soup"); p2.drink("Baijiu"); p2.beMerry();

        if(isPalindrome(p3.fullName()))
            System.out.println(p3.fullName() + " is a palindrome!");
        else
            System.out.println("Data corrupted somehow!");
    }
}