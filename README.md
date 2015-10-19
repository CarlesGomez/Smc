#Smart moto Challenge IoT solution
Smart moto challenge IoT is a solution to make the interaction between an electric motorcycle and a movile device. 

## How it works
![alt tag](http://www.sompartyapp.com/smart_ads/img/tecnologia.jpg)

An arduino with a [Bluefruit LE](https://www.adafruit.com/products/1697) connected to the switchboard of the motorcycle to track the desired data like: speed, brake, acceleration, vibration, etc. And it connects to an android decive. Then the app sends the data to a database to show it on a web (to make a live map of your tour) and on the application itself. The tour ends when you stop the motorcycle.
 
## Demo case for Smart moto challenge competition 2015
You can download the android application to your device with android 4.3+ OS installed and bluetooth 4.0 compatibility.
After that, you connect the arduino with the code you can find in the project uploaded. Then you open your application to sync with your motorcycle and when you start moving, your data will be displayed on the app. When you finish, you can check your tours on the [webapp](http://ec2-54-208-40-212.compute-1.amazonaws.com/witarc/live.html) and the app history (The web app detects when the motorcycle is running and starts tracking live).

![alt tag](http://www.sompartyapp.com/git/smc/1.png)
![alt tag](http://www.sompartyapp.com/git/smc/2.png)

