# ecom-optimus
It’s a simple eCommerce site, where you can add products to cart, search for a product etc.

## Stories completed
1. Searching and adding a product to cart
2. Increasing the product quantity and validating the price.
3. Adding a product from the featured collection.
4. Adding a products with multiple sizes.

## Features
Supports multiple browsers<br/>
Runs tests in parallel<br/>
Capture screenshots on failure<br/>
Running parallel test using Docker and Selenium Grid

## Steps to clone and execute the tests
```$xslt
// Clone the repository
$ git clone https://github.com/bhoomikag203/ecom-optimus
$ cd ecom-optimus
```

To run test without docker, set "runInDocker" property to "false" in config.properties file
```$xslt
// Execute below commands in your project directory
$ gradle build
$ gradle runTests
```
To run test in docker, set "runInDocker" property to "true" in config.properties file<br>
Make sure you have docker installed on your system
```$xslt
// Execute in your project directory
// Below command creates and starts the containers in the background specified in docker-compose.yml file
$ docker-compose up -d

// visit "http://localhost:4444/grid/console" url to see the selenium grid

$ gradle runTests

// revist "http://localhost:4444/grid/console" url to check if the nodes are alotted for tests

// Once tests are executed, run below command to stop and remove containers
$ docker-compose down
```

To run test in mobile view, set "runInDocker" property to "false" and "platformName" to "Android" in config.properties file.<br>
Make sure your mobile is connected to system and USB debugging is enabled<br>
```$xslt
// Start the appium server either in desktop app or from terminal
$ appium
// Since there is only one device connected to system, change the thread count = 1 in build.gradle file
$ gradle runTests
```

NOTE : To run test in specific browser, set "browser" property to "chrome" or "firefox" in cofig.properties file