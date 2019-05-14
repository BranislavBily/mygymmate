package sample;


import java.util.ArrayList;

public class Quotes {



    private final ArrayList<String> quotes=new ArrayList<>();

    public Quotes() {
        quotes.add("“Today I will do what others won’t, so tomorrow I can accomplish what others can’t.” —Jerry Rice");
        quotes.add("“Do something today that your future self will thank you for.” —Unknown");
        quotes.add("“We are what we repeatedly do. Excellence then is not an act but a habit.” —Aristotle");
        quotes.add("“No matter how slow you go, you are still lapping everybody on the couch.” —Unknown");
        quotes.add("“Sweat is fat crying.” —Unknown");
        quotes.add("“You miss 100% of the shots you don’t take.” —Wayne Gretzky");
        quotes.add("“The difference between the impossible and the possible lies in a person’s determination.” —Tommy Lasorda");
        quotes.add("“If you want something you’ve never had, you must be willing to do something you’ve never done.” —Thomas Jefferson");
        quotes.add("“To give anything less than your best is to sacrifice the gift.” —Steve Prefontaine");
        quotes.add("“You’re only one workout away from a good mood.” —Unknown");
        quotes.add("“Nothing will work unless you do.” —Maya Angelou");
        quotes.add("“Nothing will work unless you do.” —Maya Angelou");
        quotes.add("“Strength does not come from physical capacity. It comes from an indomitable will.” —Mahatma Gandhi");
        quotes.add("“The difference between try and triumph is a little ‘umph’.” —Unknown");
        quotes.add("“Don’t count the days, make the days count.” —Muhammad Ali");
        quotes.add("“Making excuses burns zero calories per hour.” —Unknown");
        quotes.add("“If you’re tired of starting over, stop giving up.” —Unknown");
        quotes.add("“When you feel like quitting, think about why you started.” —Unknown");
        quotes.add("“Do what you have to do until you can do what you want to do.” —Oprah Winfrey");
        quotes.add("“Create healthy habits, not restrictions.” —Unknown");
        quotes.add("“Don’t let the scale define you. Be active, be healthy, be happy.” —Unknown");
        quotes.add("“It’s going to be a journey. It’s not a sprint to get in shape.” —Kerri Walsh Jennings");
        quotes.add("“Just believe in yourself. Even if you don’t pretend that you do and, and some point, you will.” —Venus Williams");
        quotes.add("“Strive for progress, not perfection.” —Unknown");
        quotes.add("“Strive for progress, not perfection.” —Unknown");
        quotes.add("“We cannot start over. But we can begin now and make a new ending.” —Zig Ziglar");



    }


    public String getRandomQuote(){


        return quotes.get((int) (Math.random()*quotes.size()));
    }
}
