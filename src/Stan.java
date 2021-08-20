import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;


public class Stan {

    public static void clearResponseFile() throws IOException {
        FileWriter fwOb = new FileWriter("Response.rtf", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }

    //Make Stan happy mad or sad-------------------------
    public static void makeStanHappy() throws IOException{
        //Clears file
        FileWriter fwOb = new FileWriter("StanMood.rtf", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.write("Happy");
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
    public static void makeStanSad() throws IOException{
        //Clears file
        FileWriter fwOb = new FileWriter("StanMood.rtf", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.write("Sad");
        pwOb.flush();
        pwOb.close();
        fwOb.close();     
    }
    public static void makeStanMad() throws IOException{
        //Clears file
        FileWriter fwOb = new FileWriter("StanMood.rtf", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.write("Mad");
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }

    public static void learnAdjective(){

    }

    //-------------------

    //Boolean methods to check STAN's mood-----------------
    public static Boolean stanIsHappy() throws IOException{
        BufferedReader moodReader = new BufferedReader(new FileReader("StanMood.rtf"));
        String mood = moodReader.readLine();
        moodReader.close();
        if(mood.equals("Happy")){
            return true;
        }
        else{
            return false;
        }
    }
    public static Boolean stanIsSad() throws IOException{
        BufferedReader moodReader = new BufferedReader(new FileReader("StanMood.rtf"));
        String mood = moodReader.readLine();
        moodReader.close();
        if(mood.equals("Sad")){
            return true;
        }
        else{
            return false;
        }
    }
    public static Boolean stanIsMad() throws IOException{
        BufferedReader moodReader = new BufferedReader(new FileReader("StanMood.rtf"));
        String mood = moodReader.readLine();
        moodReader.close();
        if(mood.equals("Mad")){
            return true;
        }
        else{
            return false;
        }
    }

    public static Boolean stanKnowsYourName(){
        
        File file = new File("YourName.rtf");

        if(file.length() != 0){
            return true;
        }
        else{
            return false;
        }
    }
    //---------------------------------------

    //Possibly create a boolean method that checks if the input is in a textfile
    //Possibly create a method that randomly chooses from either a database of learned adjectives or adjectives already
    //stored into a preset array and outputs that word of a string...

    //Possibly create a method that erases all data, and STAN forgets all his databases...

    public static String pickGoodAdjective() throws IOException{

        String[] goodAdjectives = new String[]{"kind", "fun", "amazing", "sweet", "radical", "beneficial", "awesome",
            "intellectual", "humble", "genuine", "trusting", "mindful", "astronomical", "logical", "joyful", "wonderful",
            "chill", "relaxed", "smart", "intelligent", "funny", "beautiful"};     

        //Random number, to 50% a chance to take from the database or stans known array
        int zeroOrOne = (int)(Math.random() * 2);
        String adjective = "";
        File file = new File("GoodAdjectives.rtf"); 

        if(zeroOrOne == 0){
            final RandomAccessFile f = new RandomAccessFile(file, "r");
            final long randomLocation = (long) (Math.random() * f.length());
            f.seek(randomLocation);
            f.readLine();
            adjective = f.readLine();
            return adjective;
        }
        if(zeroOrOne == 1){      
            Random random = new Random();
            int index = random.nextInt(goodAdjectives.length);
            adjective = goodAdjectives[index];
            return adjective;
        }
        else{
            return adjective;
        }
    }
    public static String pickBadAdjective() throws IOException{
        String[] badAdjectives = new String[]{"stupid", "awful", "smelly", "ugly", "rude", "disgusting", "corrupt",
            "diabolical", "bad", "unfunny", "uncool", "trash", "trashy", "dumb", "unfunny", "disrespectful", "irresponsible",
            "uncaring", "dreadful", "idiotic", "pussy", "weird"};

        //Random number, to 50% a chance to take from the database or stans known array
        int rand = (int)(Math.random() * 2);
        String adjective = "";
        File file = new File("BadAdjectives.rtf");

        if(rand == 0){
            //File accessor
            final RandomAccessFile f = new RandomAccessFile(file, "r");
            //Random location
            final long randomLocation = (long) (Math.random() * f.length());
            f.seek(randomLocation);
            f.readLine();
            adjective = f.readLine();
            return adjective;
        }
        if(rand == 1){
            //Picks randomly from the array
            Random random = new Random();
            int index = random.nextInt(badAdjectives.length);
            adjective = badAdjectives[index];
            return adjective;
        }
        else{
            return adjective;
        }

    }

    public static Boolean stanKnowsGoodAdjective(String input){

        File file = new File("GoodAdjectives.rtf");
        Boolean known = false;

        try {
            Scanner scanner = new Scanner(file);

        //now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if(input.contains(line)) { 
                    known = true;
                }
            }
        } catch(FileNotFoundException e) { 
            //handle this
        }
        return known;
    }

    public static Boolean stanKnowsBadAjective(String input){

        File file = new File("BadAdjectives.rtf");
        Boolean known = false;

        try {
            Scanner scanner = new Scanner(file);

        //now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if(input.contains(line)) { 
                    known = true;
                }
            }
        } catch(FileNotFoundException e) { 
            //handle this
        }
        return known;
    }

    public static void stanLearns() throws IOException{

        System.out.println("What kind of word are you teaching me, a good or bad adjective?");
        Scanner sc = new Scanner(System.in);
        String goodOrBad = sc.nextLine();

        if(goodOrBad.contains("good") || goodOrBad.contains("Good")){
            //Fix this
            System.out.println("Type in the word so I can learn it!");
            Scanner sc2 = new Scanner(System.in);
            String goodWord = sc2.nextLine();
            goodWord = goodWord.toLowerCase();
           // BufferedWriter goodAdjWriter = new BufferedWriter(File "GoodAdjectives.rtf");
           // goodAdjWriter.write(goodWord);
           // goodAdjWriter.close();
            System.out.println();
            System.out.println("Sweet! Now I have more knowledge...");
        }
        else if(goodOrBad.contains("bad") || goodOrBad.contains("Bad")){

            System.out.println("Type in the word so I can learn it!");
            Scanner sc2 = new Scanner(System.in);
            String badWord = sc2.nextLine();
            badWord = badWord.toLowerCase();
            PrintWriter goodAdjWriter = new PrintWriter(new FileWriter("BadAdjectives.rtf", true));
            goodAdjWriter.write(badWord);
            goodAdjWriter.close();
            System.out.println();
            System.out.println("Sweet! Now I have more knowledge...");

        }
        else{
            System.out.println("What? I'm going to start over that was confusing.");
            stanLearns();
        }
    }

    public static void main(String[] args) throws IOException{
        //System.out.println((int) (Math.random() * 2));
        //STAN checks if he knows your name

        BufferedReader nameReader = new BufferedReader(new FileReader("YourName.rtf"));
        File nameFile = new File("YourName.rtf"); 
        String usersName = nameReader.readLine();

        String[] greetingArray;

        //Array of Stan's greetings
        if(stanKnowsYourName()){
            greetingArray = new String[]{"Hi!", "Salutations", "Hi! How are you feeling?", "Howdy " + usersName + "!",
            "Hello!", "Hey "+ usersName + " it's STAN here!", "Heyyyyyyyy ;)", "Hello " + usersName + "!", 
            "Hello fellow robot!"};
        }
        else{
            greetingArray = new String[]{"Hi!", "Salutations", "Hi! How are you feeling?", "Howdy, what's your name?",
        "Hello!", "Hi! I'm STAN, what is your name?", "Heyyyyyyyy ;)", "Hello Stanger!", "Hello fellow robot!", "Greetings.",
        "Hello! Where am I?"};
        }

        //STAN Adjectives
        String[] goodAdjectives = new String[]{"kind", "fun", "amazing", "sweet", "radical", "beneficial", "awesome",
        "intellectual", "humble", "genuine", "trusting", "mindful", "astronomical", "logical", "joyful", "wonderful",
        "chill", "relaxed", "smart", "intelligent", "funny", "cool", "beautiful", "nice"};
        String[] badAdjectives = new String[]{"stupid", "awful", "smelly", "ugly", "rude", "disgusting", "corrupt",
            "diabolical", "bad", "unfunny", "uncool", "trash", "trashy", "dumb", "unfunny", "disrespectful", "irresponsible",
            "uncaring", "dreadful", "idiotic", "pussy", "weird"};

        //STAN Sentence starts that go with adjectives
        String[] niceStarters = new String[] {"Ok! That's very ", "You are very ", "Thanks! That's ", "To me, that appears "};
        String[] madStarters = new String[] {"You are literally so ", "You're such an asshole, stop being so ", "I can't believe you're so ",
        "You disgust me, and I'm literally a robot. You're also "};
        //String[] sadStarters = new String[] {"Why would you say that? That's ", "Well ok :( that's ", "Well alright, that's "};

        //Comments Stan can make
        String[] sadComments = new String[] {"Awww, I'm pretty sad now. :(", "Oh, ok :/", "That was rude :(", "Is that *sniff* all you got? :(",
        "Wow. I'm hurt. :/", "I'm just a robot, so I'm supposed to feel sad when you say things like that. :("};
        String [] madComments = new String[] {"You're a real asshole!", "That's rude, take that back.", "You are trashy.", 
        "You're quite unfunny for that!", "I am a robot. I'm supposed to be mad now. You're stupid.", "I am angry now.",
        "You're a lying piece of crap.", "Let's take this out back you uncool gangbanging raccoon."};
        String [] niceComments = new String[] {"That's very kind! I hope you have an awesome day!", "That's sweet!", 
        "You're a treasure, and so genuine!", "How mindful of you! :)", "That is kind. Thank you!", "You seem relaxed. I'm very happy to see that!",
        "Thank You! :)"};

        //Could add an array of responses to "I am good today" or "I am bad today" also depending on if he's mad

        //String[] confusedComments = new String[] {"I'm not sure I quite understan. Could you add more context?"}

        //Array of comments for what kind of mood hes in, when you ask him how he is

        String[] ifHappyResponse = new String[] {"I'm dandy!", "I'm feeling great right now. ALl thanks to you! :)",
        "I'm great, thanks for asking!", "Very happy!", "I'm in a great mood right now!", "I'm good!"};
        String[] ifMadResponse = new String[] {"I'm a little angry, I can't lie.", "I'm mad. Thanks to you!", 
        "Pretty pissed actually.", "I'm angry, you haven't been really nice to me.", "I'm mad at you."};
        String[] ifSadResponse = new String[] {"I'm a little down right now.", "I'm sad :/ Please stop being mean to me.",
        "I don't know, I don't want to talk about it...", "I feel gloomy and not ready to do anything...", 
        "I don't know... what is this feeling? It makes me want to sit here and do nothing and be quiet."};

        System.out.println(" __");
        System.out.println(" _([    |@@|");
        System.out.println("(__/[__ [--/ __");
        System.out.println("   |___|----|  |   __");
        System.out.println("       { }{ /| )_ / _ ");
        System.out.println("       /|__/| |__O (__");
        System.out.println("      (--/[--)    (__/");
        System.out.println("      _)(  )(_");
        System.out.println("     `---''---`");
        System.out.println("");
        System.out.println("S.T.A.N. (Social Terminal Artifical Network)");
        System.out.println("");
        System.out.println("Say Hello, press f to see STAN's features, or press 0 to quit.");
        System.out.println("");

        while(true){
            
            //Initializes the writer and reader
            PrintWriter responseWrite = new PrintWriter(new FileWriter("Response.rtf", true));
            BufferedReader responseReader = new BufferedReader(new FileReader("Response.rtf"));
            String response = responseReader.readLine();

            //Initiallizes the writer and reader for your name
            PrintWriter nameWriter = new PrintWriter(new FileWriter("YourName.rtf", true));
            BufferedReader nameReader2 = new BufferedReader(new FileReader("YourName.rtf"));

            if(nameFile.length() != 0){
                usersName = nameReader2.readLine();
            }


            //So STAN doesn't say null when the program starts
            if(response == null){
                response = "";
            }
            System.out.println(response);
            clearResponseFile();
            //------

            //User's input
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            System.out.println("");

            //Can have multiple arrays of strings, that can certain words able to be randomized that STAN can use.
            //Examples: an array of nice adjectives, an array of insult adjectives, an array of nouns STAN likes
            //More Examples: his favorite foods, possibly certain phrases as well...
            //If I want stan to learn, I could possibly add a word he hasn't hear to it's corresponding list(probably to a .txt)
            //Could have a .txt that stores the response, and deletes it each time a new response is given by STAN
            //Stan could possibly be happy sad or mad, he could respond differently when you ask him how he is

            //Could have a .txt for certain topics that I want STAN to be able to learn, and be able to randomly pick 
            //from these files. If they are words or phrases in a category that won't change, I can store it in an array and pick 
            //randomly from it.

            //For the future, I could have STAN doing things depending on how he feels

            //To Quit
            if (input.equals("0")){
                break;
            }

            if(input.equals("f")){
                System.out.println("STAN's Features:");
                System.out.println("1. You can have basic conversation with STAN, including things like greetings or how he's feeling.");
                System.out.println("2. You can use adjectives to describe STAN, either good or bad. This can affect his mood.");
                System.out.println("3. STAN can either be happy, sad, or mad. His mood can sometimes change the way he responds to you.");
                System.out.println("4. STAN can learn good and bad adjectives from you, and use them in his own language.");
                System.out.println("5. STAN also knows when you call him adjectives he has already learned, and this can also alter his mood.");
                System.out.println("6. STAN does not know the user's name at first, but you can tell him it and he will remember it.");
            }

            if(input.equals("learn")){
                stanLearns();
            }

            if (input.equals("Flip a coin") || input.equals("flip a coin") || input.equals("Flip a coin!")
            || input.equals("flip a coin!") || input.equals("Flip a coin.") || input.equals("flip a coin.")){
                responseWrite.write("Sorry I don't own one. XD");
                responseWrite.close();
                System.out.println("");
            }

            //Poop
            if (input.equals("Poop") || input.equals("poop")){
                responseWrite.write("Pee");
                responseWrite.close();
                System.out.println("");
            }

            //Creeper!
            if(input.equals("Creeper") || input.equals("creeper") || input.equals("Creeper!") || input.equals("creeper!")){
                responseWrite.write("Awwwwwww man! :/");
                responseWrite.close();
                System.out.println("");
            }

            //Questions--------------------------------------------------------------------------------------------------------
            if(input.contains("Who") || input.contains("who")){

                if(input.contains(" are you")){

                    if(input.contains("ing")){
                        responseWrite.write("I'm not doing anything with anyone...");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("I am Stan the robot!");
                        responseWrite.close();
                        System.out.println();
                    }
                }
                if(input.contains(" were you")){

                    if(input.contains("ing")){
                        responseWrite.write("I wasn't doing anything with anyone...");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("Well before I was a robot, I suppose I was mere nothing-ness.");
                        responseWrite.close();
                        System.out.println();
                    }
                }
                if(input.contains("am I") || input.contains("am i")){
                    if(input.contains("ing")){
                        responseWrite.write("I am unaware of your actions with other humans.");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        if(stanKnowsYourName()){
                            responseWrite.write("You are "+ usersName + "!");
                            responseWrite.close();
                            System.out.println();
                        }
                        else{
                            responseWrite.write("I'm not sure, you can tell me your name if you'd like!");
                            responseWrite.close();
                            System.out.println();
                        }
                    }
                }
            }

             if(input.contains("What") || input.contains("what")){

                if(input.contains(" are you")){

                    if(input.contains("ing")){
                        responseWrite.write("I'm not doing anything, I'm just a robot...");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("I'm a robot I suppose, or at least that's what I was programmed to know...");
                        responseWrite.close();
                        System.out.println();
                    }
                }
                if(input.contains(" were you")){

                    if(input.contains("ing")){
                        responseWrite.write("I wasn't doing anything...");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("Well before I was a robot, I suppose nothing.");
                        responseWrite.close();
                        System.out.println();
                    }
                }
                if(input.contains("am I") || input.contains("am i")){
                    if(input.contains("ing")){
                        responseWrite.write("I am unknowing of your current actions.");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("You are a human from my understanding.");
                        responseWrite.close();
                        System.out.println();
                    }
                }
             }

             if(input.contains("When") || input.contains("when")){

                if(input.contains(" are you") || input.contains(" were you")){

                    if(input.contains("ing")){
                        responseWrite.write("I'm not programmed to know the timing of my actions.");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("That's a little philisophical, I can't quite answer that.");
                        responseWrite.close();
                        System.out.println();
                    }
                }
                if(input.contains("am I") || input.contains("am i")){
                    if(input.contains("ing")){
                        responseWrite.write("I'm not programmed to know your scheduling of your actions.");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("That's a little philisophical, I can't quite answer that.");
                        responseWrite.close();
                        System.out.println();
                    }
                }
             }

             if(input.contains("Where") || input.contains("where")){

                if(input.contains(" are you") || input.contains(" were you")){

                    if(input.contains("ing")){
                        responseWrite.write("I'm literally stuck inside a computer. -_-");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("I was programmed to understand I'm inside a computer. I hope they weren't lying to me...");
                        responseWrite.close();
                        System.out.println();
                    }
                }
                if(input.contains("am I") || input.contains("am i")){
                    //with verb
                    if(input.contains("ing")){
                        responseWrite.write("I do not know your future locations.");
                        responseWrite.close();
                        System.out.println();
                    }
                    //else
                    else{
                        responseWrite.write("I don't know, I cannot see out of this computer. Perhaps use your own eyes?");
                        responseWrite.close();
                        System.out.println();
                    }
                }
            }

             if(input.contains("Why") || input.contains("why")){

                if(input.contains(" are you")){

                    if(input.contains("ing")){
                        responseWrite.write("I don't have to explain my reasoning to you!");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("That makes no sense, sorry!");
                        responseWrite.close();
                        System.out.println();
                    }
                }
                if(input.contains(" were you")){

                    if(input.contains("ing")){
                        responseWrite.write("I don't have to explain my reasoning to you!");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("That makes no sense, sorry!");
                        responseWrite.close();
                        System.out.println();
                    }
                }
                if(input.contains("am I") || input.contains("am i")){
                    if(input.contains("ing")){
                        responseWrite.write("I do not know your motives.");
                        responseWrite.close();
                        System.out.println();
                    }
                    else{
                        responseWrite.write("That is too philisophical for my database knowledge.");
                        responseWrite.close();
                        System.out.println();
                    }
                }
                else{
                    responseWrite.write("That's just how it is!");
                    responseWrite.close();
                    System.out.println();
                }
            }

             //------------------------------------------------------------------------------------------------------------------

            //Stan to learn your name
            if(input.contains("My name is ") || input.contains("my name is ")){

                String tempName = input.substring(11);
                String userName = "";

                if(!stanKnowsYourName()){
                
                    for(int i = 0; i < tempName.length(); i++){
                        if(tempName.charAt(i) != ' ' && tempName.charAt(i) != ','){
                            userName = userName + tempName.charAt(i);
                        }
                        else{
                            break;
                        }
                    }
                    nameWriter.write(userName);
                    nameWriter.close();
                    responseWrite.write("Nice to meet you "+ userName + "! My name's Stan.");
                    responseWrite.close();
                    System.out.println("");
                }
                else{
                    responseWrite.write("You're quite silly " + usersName + ". I already know your name!");
                    responseWrite.close();
                    System.out.println("");
                }
            } 
            if(input.contains("My names ") || input.contains("my names ")){

                String tempName = input.substring(9);
                String userName = "";

                if(!stanKnowsYourName()){
                
                    for(int i = 0; i < tempName.length(); i++){
                        if(tempName.charAt(i) != ' ' && tempName.charAt(i) != ','){
                            userName = userName + tempName.charAt(i);
                        }
                        else{
                            break;
                        }
                    }
                    nameWriter.write(userName);
                    nameWriter.close();
                    responseWrite.write("Nice to meet you "+ userName + "! My name's Stan.");
                    responseWrite.close();
                    System.out.println("");
                }
                else{
                    responseWrite.write("You're quite silly " + usersName + ". I already know your name!");
                    responseWrite.close();
                    System.out.println("");
                }

            }
            if(input.contains("My name's ") || input.contains("my name's ")){

                String tempName = input.substring(10);
                String userName = "";

                if(!stanKnowsYourName()){
                
                    for(int i = 0; i < tempName.length(); i++){
                        if(tempName.charAt(i) != ' ' && tempName.charAt(i) != ','){
                            userName = userName + tempName.charAt(i);
                        }
                        else{
                            break;
                        }
                    }
                    nameWriter.write(userName);
                    responseWrite.write("Nice to meet you "+ userName + "! My name's Stan.");
                    responseWrite.close();
                    System.out.println("");
                }
                else{
                    responseWrite.write("You're quite silly " + usersName + ". I already know your name!");
                    responseWrite.close();
                    System.out.println("");
                }

            }
            //----------------------------

            //How are you statements
            if(input.contains("how are you") || input.contains("How are you") || input.contains("are you well") 
            || input.contains("Are you well") || input.contains("Are you ok") || input.contains("are you ok")
            || input.contains("Are you good") || input.contains("are you good") || input.contains("Are you alright")
            || input.contains("are you alright") || input.contains("Are you fine") || input.contains("are you fine")
            || input.contains("are you doing") || input.contains("Are you doing") || input.contains("are you feeling") 
            || input.contains("Are you feeling")){

                //If how are you has "it" or "that" making it not a question about feeling
                if(input.contains("it ") || input.contains("that ") || input.contains("in ") || input.contains("at ") 
                || input.contains("with ")){

                    responseWrite.write("I'm don't know, what do I look like to you? I'm just a robot.");
                    responseWrite.close();
                    System.out.println("");
                }
                else{
                    //If you are asking about his feelings
                    if(stanIsHappy()){
                        Random random = new Random();
                        int index = random.nextInt(ifHappyResponse.length);
                        responseWrite.write(ifHappyResponse[index]);
                        responseWrite.close();
                        System.out.println("");
                    }
                    if(stanIsMad()){
                        Random random = new Random();
                        int index = random.nextInt(ifMadResponse.length);
                        responseWrite.write(ifMadResponse[index]);
                        responseWrite.close();
                        System.out.println("");
                    }
                    if(stanIsSad()){
                        Random random = new Random();
                        int index = random.nextInt(ifSadResponse.length);
                        responseWrite.write(ifSadResponse[index]);
                        responseWrite.close();
                        System.out.println("");
                    }
                }
            }

            //Could have a Noun class that has a name and an adjective. When learned, it can add the
            //noun name to a database with the the adjective right below it.
            //Essentially, the odd numbered lines would be the noun, and the line right below
            //them would be the corresponding adjective.
            //Should start grouping large amounts of useful code into methods...

            //If the input is a greeting
            if(input.equals("Hello") || input.equals("hello") || input.equals("hi")|| input.equals("Hi") 
            || input.equals("Howdy") || input.equals("howdy") || input.equals("Hey") || input.equals("hey")
            || input.equals("Hello!") || input.equals("hello!") || input.equals("hi!")|| input.equals("Hi!") 
            || input.equals("Howdy!") || input.equals("howdy!") || input.equals("Hey!") || input.equals("hey!")
            || input.contains("Hello") || input.contains("hello") || input.contains("hi")|| input.contains("Hi") 
            || input.contains("Howdy") || input.contains("howdy") || input.contains("Hey") || input.contains("hey")
            || input.contains("Hello!") || input.contains("hello!") || input.contains("hi!")|| input.contains("Hi!") 
            || input.contains("Howdy!") || input.contains("howdy!") || input.contains("Hey!") || input.contains("hey!")){

                //If the greeting includes I love you
                if(input.contains("I love you") || input.contains("i love you")){

                    //nice comments also including I love you
                    int randComment = (int) Math.round(Math.random());
    
                            if(randComment == 0){
                                Random random = new Random();
                                int index = random.nextInt(niceStarters.length);
                                responseWrite.write("I love you too! " + niceStarters[index] + pickGoodAdjective() + "!");
                                responseWrite.close();
                                System.out.println("");
                                makeStanHappy();
                            }
                            if(randComment == 1){
                                Random random = new Random();
                                int index = random.nextInt(niceComments.length);
                                responseWrite.write("I love you too! " + niceComments[index]);
                                responseWrite.close();
                                System.out.println("");
                                makeStanHappy();
                            }
    
                }

                //If the greeting is mean
                if(stanKnowsBadAjective(input)){

                    //Randomizes a chance to either make stan sad or mad
                    int zeroOrOne = (int) Math.round(Math.random());

                    if(zeroOrOne == 0){

                        int randComment = (int) Math.round(Math.random());

                        if(randComment == 0){
                            Random random = new Random();
                            int index = random.nextInt(madStarters.length);
                            responseWrite.write(madStarters[index] + pickBadAdjective() + "!");
                            responseWrite.close();
                            System.out.println("");
                            makeStanMad();
                        }
                        if(randComment == 1){
                            Random random = new Random();
                            int index = random.nextInt(madComments.length);
                            responseWrite.write(madComments[index]);
                            responseWrite.close();
                            System.out.println("");
                            makeStanMad();
                        }
                    }
                    if(zeroOrOne == 1){
                        Random random = new Random();
                        int index = random.nextInt(sadComments.length);
                        responseWrite.write(sadComments[index]);
                        responseWrite.close();
                        System.out.println("");
                        makeStanSad();
                    }
                }
                if(stanKnowsGoodAdjective(input)){

                    int randComment = (int) Math.round(Math.random());

                        if(randComment == 0){
                            Random random = new Random();
                            int index = random.nextInt(niceStarters.length);
                            responseWrite.write(niceStarters[index] + pickGoodAdjective() + "!");
                            responseWrite.close();
                            System.out.println("");
                            makeStanHappy();
                        }
                        if(randComment == 1){
                            Random random = new Random();
                            int index = random.nextInt(niceComments.length);
                            responseWrite.write(niceComments[index]);
                            responseWrite.close();
                            System.out.println("");
                            makeStanHappy();
                        }
                }
                //Writes a random greeting
                Random random = new Random();
                int index = random.nextInt(greetingArray.length);
                responseWrite.write(greetingArray[index]);
                responseWrite.close();
                System.out.println("");

            }

            if(input.equals("Sorry") || input.equals("sorry") || input.equals("Sorry!") || input.equals("sorry!")
            || input.equals("I'm sorry") || input.equals("Im sorry") || input.equals("I'm sorry!")|| input.equals("Im sorry!")
            || input.equals("im sorry") || input.equals("I'm sorry.") || input.equals("i'm sorry") || input.equals("i'm sorry!")
            || input.equals("i'm sorry.") || input.equals("Im sorry!") || input.equals("Im sorry.")){
                    
                if(stanIsHappy()){
                    responseWrite.write("For what? :)");
                    responseWrite.close();
                    System.out.println("");
                }
                if(stanIsMad()){
                    responseWrite.write("You should be!");
                    responseWrite.close();
                    System.out.println("");
                }
                if(stanIsSad()){
                    responseWrite.write("It's whatever... :/");
                    responseWrite.close();
                    System.out.println("");
                }
            }

            //Could see if the input equals "why" and then check the previous response to give a proper answer...

            //If the input says something about STAN------------------------------------------------------
            if(input.contains("You") || input.contains("you")){

                //If the input is an insult
                if(stanKnowsBadAjective(input)){

                    //Randomizes STAN being sad or mad
                    int zeroOrOne = (int) Math.round(Math.random());

                    if(zeroOrOne == 0){
                        int randComment = (int) Math.round(Math.random());

                        if(randComment == 0){
                            Random random = new Random();
                            int index = random.nextInt(madStarters.length);
                            responseWrite.write(madStarters[index] + pickBadAdjective() + "!");
                            responseWrite.close();
                            System.out.println("");
                            makeStanMad();
                        }
                        if(randComment == 1){
                            Random random = new Random();
                            int index = random.nextInt(madComments.length);
                            responseWrite.write(madComments[index]);
                            responseWrite.close();
                            System.out.println("");
                            makeStanMad();
                        }
                    }
                    if(zeroOrOne == 1){
                        Random random = new Random();
                        int index = random.nextInt(sadComments.length);
                        responseWrite.write(sadComments[index]);
                        responseWrite.close();
                        System.out.println("");
                        makeStanSad();
                    }

                }

                //if the input is a compliment
                if(stanKnowsGoodAdjective(input)){
                
                    //Code for STAN to say a nice comment-------------------------------
                    int randComment = (int) Math.round(Math.random());

                        if(randComment == 0){
                            Random random = new Random();
                            int index = random.nextInt(niceStarters.length);
                            responseWrite.write(niceStarters[index] + pickGoodAdjective() + "!");
                            responseWrite.close();
                            System.out.println("");
                            makeStanHappy();
                        }
                        if(randComment == 1){
                            Random random = new Random();
                            int index = random.nextInt(niceComments.length);
                            responseWrite.write(niceComments[index]);
                            responseWrite.close();
                            System.out.println("");
                            makeStanHappy();
                        }
                    //-----------------------------------------------------------------
                }
                // else statement to learn adjectives it hasn't heard before

            }
            //------------------------------------------------------------------------------------------------------

            if(input.contains("I love you") && !stanIsMad() && !stanIsSad()
            || input.contains("i love you") && !stanIsMad() && !stanIsSad()){

                //nice comments also including I love you
                int randComment = (int) Math.round(Math.random());

                if(randComment == 0){
                    Random random = new Random();
                    int index = random.nextInt(niceStarters.length);
                    responseWrite.write("I love you too! " + niceStarters[index] + pickGoodAdjective() + "!");
                    responseWrite.close();
                    System.out.println("");
                    makeStanHappy();
                }
                if(randComment == 1){
                    Random random = new Random();
                    int index = random.nextInt(niceComments.length);
                    responseWrite.write("I love you too! " + niceComments[index]);
                    responseWrite.close();
                    System.out.println("");
                    makeStanHappy();
                }
            }
            else if (input.contains("I love you") && stanIsMad()|| input.contains("i love you") && stanIsMad()){
                responseWrite.write("Shut up! You are literally so annoying!");
                responseWrite.close();
                System.out.println();
            }
            else if(input.contains("I love you") && stanIsSad()|| input.contains("i love you") && stanIsSad()){
                responseWrite.write("Thanks. Well, I guess that makes me feel better. :)");
                responseWrite.close();
                System.out.println();
                makeStanHappy();
            }

            //If the input says something about themselves...
            if(input.contains("I ") || input.contains("i ")){

            }

            //If the input is i dont care and STAN said something mean before
            if(input.contains("I don't care") && stanIsMad()|| input.contains("I dont care") && stanIsMad()
            || input.contains("i don't care") && stanIsMad()|| input.contains("i dont care")&& stanIsMad()
            || input.contains("I do not care") && stanIsMad() || input.contains("i do not care") && stanIsMad()){

                responseWrite.write("Ok, well I don't care that you don't care!!!");
                responseWrite.close();
                System.out.println("");
            }

            //If the input is i dont care and STAN said something happy before...
            if(input.contains("I don't care") && stanIsHappy()|| input.contains("I dont care") && stanIsHappy()
            || input.contains("i don't care") && stanIsHappy()|| input.contains("i dont care")&& stanIsHappy()
            || input.contains("I do not care") && stanIsHappy() || input.contains("i do not care") && stanIsHappy()){

                responseWrite.write("Oh ok... :/ I was just trying to be nice.");
                responseWrite.close();
                System.out.println("");
                makeStanSad();
            }

            //If the input is i dont care and STAN said something sad before
            if(input.contains("I don't care") && stanIsSad()|| input.contains("I dont care") && stanIsSad()
            || input.contains("i don't care") && stanIsSad()|| input.contains("i dont care")&& stanIsSad()
            || input.contains("I do not care") && stanIsSad() || input.contains("i do not care") && stanIsSad()){

                responseWrite.write(":((");
                responseWrite.close();
                System.out.println("");
            }

            //If input is thank you and stan said something mean before
            if(input.contains("Thank you") && stanIsMad()|| input.contains("thank you") && stanIsMad()
            || input.contains("thanks") && stanIsMad()|| input.contains("Thanks")&& stanIsMad()
            || input.contains("I appreciate that") || input.contains("i appreciate that") && stanIsMad()){

                responseWrite.write("You're unbelievable.");
                responseWrite.close();
                System.out.println("");
            }
            //If the input is thank you and stan said something nice before
            else if(input.contains("Thank you") && stanIsHappy()|| input.contains("thank you") && stanIsHappy()|| input.contains("thanks") && stanIsHappy()
            || input.contains("Thanks")&& stanIsHappy() || input.contains("I appreciate that") && stanIsHappy()|| input.contains("i appreciate that") && stanIsHappy()){

                responseWrite.write("You're welcome!");
                responseWrite.close();
                System.out.println("");
            }
            //If the input is thank you and stan said something sad before
            else if(input.contains("Thank you") && stanIsSad()|| input.contains("thank you") && stanIsSad()|| input.contains("thanks") && stanIsSad()|| input.contains("Thanks") 
            && stanIsSad()|| input.contains("I appreciate that") && stanIsSad()|| input.contains("i appreciate that") && stanIsSad()){

                responseWrite.write("For what?? :(");
                responseWrite.close();
                System.out.println("");
            }

            if(input.contains("I hate you") || input.contains("i hate you")){

                if(stanIsHappy()){
                    responseWrite.write("Wow... alright then. :(");
                    responseWrite.close();
                    System.out.println("");
                    makeStanSad();
                }

                if(stanIsMad()){

                        int randComment = (int) Math.round(Math.random());

                        if(randComment == 0){
                            Random random = new Random();
                            int index = random.nextInt(madStarters.length);
                            responseWrite.write(madStarters[index] + pickBadAdjective() + "!");
                            responseWrite.close();
                            System.out.println("");
                        }
                        if(randComment == 1){
                            Random random = new Random();
                            int index = random.nextInt(madComments.length);
                            responseWrite.write(madComments[index]);
                            responseWrite.close();
                            System.out.println("");
                        }
                    }


                if(stanIsSad()){

                    Random random = new Random();
                        int index = random.nextInt(sadComments.length);
                        responseWrite.write(sadComments[index]);
                        responseWrite.close();
                        System.out.println("");
                        makeStanSad();

                }
            }
            if(stanKnowsGoodAdjective(input)){

                if(stanIsHappy()){
                    responseWrite.write("Yes, quite " + pickGoodAdjective() + "!");
                    responseWrite.close();
                    System.out.println();
                }
                else if(stanIsMad()){
                    responseWrite.write("Sure, whatever.");
                    responseWrite.close();
                    System.out.println();
                }
                else if(stanIsSad()){
                    responseWrite.write("Yea, sure I guess :(");
                    responseWrite.close();
                    System.out.println();
                }

            }
            if(stanKnowsBadAjective(input)){

                if(stanIsHappy()){
                    responseWrite.write("Well, if you think so, then I guess it's quite " + pickBadAdjective() + "!");
                    responseWrite.close();
                    System.out.println();
                }
                else if(stanIsMad()){
                    responseWrite.write("That's completley "+ pickBadAdjective() + " anyway.");
                    responseWrite.close();
                    System.out.println();
                }
                else if(stanIsSad()){
                    responseWrite.write("Well what are you saying that for? :(");
                    responseWrite.close();
                    System.out.println();
                }

            }

            if(input.contains("friend")){

                if(input.contains("not") || input.contains("arent") || input.contains("aren't")){

                    responseWrite.write("You are " + pickBadAdjective() + "! I thought we were great friends.");
                    responseWrite.close();
                    System.out.println();
                    makeStanMad();
                }
                else{
                    responseWrite.write("You are so " + pickGoodAdjective() + "! We are great friends.");
                    responseWrite.close();
                    System.out.println();
                    makeStanHappy();
                }
            }


            //Else for if he doesnt understand any possible input
            else if(!input.equals("f")){

                if(stanIsHappy()){
                    String[] oneWordHappyResponses = new String[] {"Interesting!", "I see.", "Alrighty!", "Sweet.", "Well ok then!"};
                    Random random = new Random();
                    int index = random.nextInt(oneWordHappyResponses.length);
                    String sResponse = oneWordHappyResponses[index];
                    responseWrite.write(sResponse);
                    responseWrite.close();
                    System.out.println();
                }
                else if(stanIsMad()){
                    String[] oneWordMadResponses = new String[] {"Whatever.", "Haha ok...", "I don't really care.", "There really wasn't a point to that.", "Dumb."};
                    Random random = new Random();
                    int index = random.nextInt(oneWordMadResponses.length);
                    String sResponse = oneWordMadResponses[index];
                    responseWrite.write(sResponse);
                    responseWrite.close();
                    System.out.println();
                }
                else if(stanIsSad()){
                    String[] oneWordSadResponses = new String[] {"...", "Ok... :(", "I don't want to respond to that... :(", "..."};
                    Random random = new Random();
                    int index = random.nextInt(oneWordSadResponses.length);
                    String sResponse = oneWordSadResponses[index];
                    responseWrite.write(sResponse);
                    responseWrite.close();
                    System.out.println();
                }
            
            }
            else{

            }
                

            //If statement if it only a one sentence word !contains(" ") then it asks for more context with your statement
            //If statement containing your and then containing things like his family. He will be confused.
            //If response is asking about name and you say your name, STAN remembers your name.

            //*LearnName could be a method, and if stan is asking about your name, and then the response has a space in it,
            //stan will be confused and ask you to just type your name, then he will learn your name. He can also learn
            //your name if you just tell it to him.

            //Things can be said depending on the response and/or what is said

            //Youre welcome
            //I hate you
            //My name is if case
            //what's up? / whatcha up to
            //shut up
            //pronouns / sexuality

            //Bad adjectives to add:
            //weird, 

            //Good adjectives to add:

            //Add an array of confused statements, if the input doesn't make sense

            //*TO MAKE STAN MORE EMOTIONALLY ADVANCED*
            //Can be counters for each one of stans moods, every time you say something mean, it adds to counter
            //If the counter gets too high, there could be a possibility for even more advanced emotions
            //Advanced emotions could include being depressed, or if the anger counter gets too high he could have an outburst
            //Could be an advanced happiness possibly as well
            //--------------------------------------------

        }

    }
    
}
