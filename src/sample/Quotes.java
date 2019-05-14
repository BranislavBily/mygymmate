package sample;


import java.util.ArrayList;

public class Quotes {



    private final ArrayList<String> quotes=new ArrayList<>();

    public Quotes() {

        quotes.add("“Do something today that your future self will thank you for.”");

        quotes.add("“No matter how slow you go, you are still lapping everybody on the couch.”");
        quotes.add("“Sweat is fat crying.”");
        quotes.add("“You miss 100% of the shots you don’t take.” —Wayne Gretzky");
        quotes.add("“To give anything less than your best is to sacrifice the gift.” —Steve Prefontaine");
        quotes.add("“You’re only one workout away from a good mood.”");
        quotes.add("“Nothing will work unless you do.” —Maya Angelou");
        quotes.add("“Nothing will work unless you do.” —Maya Angelou");
        quotes.add("“The difference between try and triumph is a little ‘umph’.”");
        quotes.add("“Don’t count the days, make the days count.” —Muhammad Ali");
        quotes.add("“Making excuses burns zero calories per hour.”");
        quotes.add("“If you’re tired of starting over, stop giving up.”");
        quotes.add("“When you feel like quitting, think about why you started.”");
        quotes.add("“Do what you have to do until you can do what you want to do.” —Oprah Winfrey");
        quotes.add("“Create healthy habits, not restrictions.”");
        quotes.add("“Don’t let the scale define you. Be active, be healthy, be happy.”");
        quotes.add("“It’s going to be a journey. It’s not a sprint to get in shape.” —Kerri Walsh Jennings");
        quotes.add("“Strive for progress, not perfection.”");
        quotes.add("“Strive for progress, not perfection.”");
        quotes.add("“We cannot start over. But we can begin now and make a new ending.” —Zig Ziglar");



    }


    public String getRandomQuote(){


        return quotes.get((int) (Math.random()*quotes.size()));
    }
}
