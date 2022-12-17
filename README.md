# Image Processing Program (CS3500: Object-Oriented Design)
***
## What is the Image Processing Program?
This is **an Image Processing Application** (for CS3500 class at Northeastern)
that takes in some commands from the user to modify image file. 
This Program was written in Java while following MVC Design and core Object-Oriented 
Design principles such as Inheritance, Encapsulation, Command Design Pattern, and more. 
The current version of the Image Processor supports PPM, PNG, JPG (JPEG), and more image files.

***
## How do the users interact with the program?
There are `three` ways the users can interact with the program.
* Copy and paste the following command into your terminal to run the program. (Inputting commands 
manually).
  * `java -jar res/assignment-6-real.jar -text`
* Direction (script) file: copy and paste the following command into your terminal to run 
the commands in the script file. 
  * `java -jar res/assignment-6-real.jar -file res/script.txt`
* Interact with the program through Graphical User Interface.
  * `java -jar res/assignment-6-real.jar`
***
## What are the commands? 
### ** Must be typed accordingly to the guide here. **
####  The program will not work properly if the commands are typed wrong or in different order.
* To quit: `q`, `Q`, `Quit`, or `quit`. This command is not case-sensitive.
* Load an image: `load (Image-path) (Image-name)` 
  * `Image-path`: The file path for the image in user's computer.
  * `Image-name`: The image name the user wants their image to save as.
* Flip the image vertically: `vertical-flip (Image-name) (New-image-name)`
  * `Image-name`: The image name the image is saved as.
  * `New-image-name`: The image name the user wants their modified image to save as.
* Flip the image horizontally: `horizontal-flip (Image-name) (New-image-name)`
  * `Image-name`: The image name the image is saved as.
  * `New-image-name`: The image name the user wants their modified image to save as.
* Brighten the image: `brighten (Positive-integer) (Image-name) (New-image-name)`
  * `Positive-integer`: The amount to brighten the image (positive integer)
  * `Image-name`: The image name the image is saved as.
  * `New-image-name`: The image name the user wants their modified image to save as.
* Darken the image: `brighten (Negative-integer) (Image-name) (New-image-name)`
    * `Negative-integer`: The amount to darken the image (positive integer)
    * `Image-name`: The image name the image is saved as.
    * `New-image-name`: The image name the user wants their modified image to save as.
* Greyscale the image:
  * Red-Component Greyscale: `red-component (Image-name) (New-image-name)`
    * `Image-name`: The image name the image is saved as.
    * `New-image-name`: The image name the user wants their modified image to save as.
  * Green-Component Greyscale: `green-component (Image-name) (New-image-name)`
      * `Image-name`: The image name the image is saved as.
      * `New-image-name`: The image name the user wants their modified image to save as.
  * Blue-Component Greyscale: `blue-component (Image-name) (New-image-name)`
      * `Image-name`: The image name the image is saved as.
      * `New-image-name`: The image name the user wants their modified image to save as.
  * Value-Component Greyscale: `value-component (Image-name) (New-image-name)`
      * Sets `the maximum value` in RGB for all the RGB values in the pixel.
      * `Image-name`: The image name the image is saved as.
      * `New-image-name`: The image name the user wants their modified image to save as.
  * Intensity-Component Greyscale: `intensity-component (Image-name) (New-image-name)`
      * Sets `the mean value` in RGB for all the RGB values in the pixel.
      * `Image-name`: The image name the image is saved as.
      * `New-image-name`: The image name the user wants their modified image to save as.
  * Luma-Component Greyscale: `luma-component (Image-name) (New-image-name)`
      * Uses the formula: `N = 0.2126(R) + 0.7152(G) + 0.0722(B)`, Sets N as the value for all 
    RGB values in the pixel.
      * `Image-name`: The image name the image is saved as.
      * `New-image-name`: The image name the user wants their modified image to save as.
  * Blur the image: `blur (Image-name) (New-image-name)`
    * `Image-name`: The image name the image is saved as.
    * `New-image-name`: The image name the user wants their modified image to save as.
  * Sharpen the image: `Sharpen (Image-name) (New-image-name)`
    * `Image-name`: The image name the image is saved as.
    * `New-image-name`: The image name the user wants their modified image to save as.
  * Transform the color of image using sepia: `sepia (Image-name) (New-image-name)`
    * `Image-name`: The image name the image is saved as.
    * `New-image-name`: The image name the user wants their modified image to save as.
  * Transform the color of image using greyscale: `greyscale (Image-name) (New-image-name)`
    * `Image-name`: The image name the image is saved as.
    * `New-image-name`: The image name the user wants their modified image to save as.
***
## Res Image Folder
`*` **res folder** contains Image: **snail.ppm, snail-brighter-by-50.ppm, snail-darker-by-50.ppm, 
snail-horizontal.ppm , snail-luma-greyscale.ppm, snail-vertical.ppm, city.png, city-blur.png,
city-color-greyscale.png, city-sepia.png, city-sharpen.png.** </br>
`snail.ppm`: Original ppm image file. </br>
`snail-brighter-by-50.ppm`: Snail.ppm brightened by 50.</br>
`snail-darker-by-50.ppm`: Snail.ppm darkened by 50.</br>
`snail-horizontal.ppm`: Snail.ppm flipped horizontally.</br>
`snail-luma-greyscale.ppm`: greyscale Snail.PPM through luma method.</br>
`snail-vertical.ppm`: Snail.ppm flipped vertically.</br>
`city.png`: Original png image file. </br>
`city-blur.png`: city.png blurred image file. </br>
`city-color-greyscale.png`: city.png image file that was color transformed with greyscale. </br>
`city-sepia.png`: city.png image file that was color transformed with sepia. </br>
`city-sharpen.png`: city.png sharpened image file. </br>
`script.txt`: a list of possible commands for the program. </br>
`HW05.jar`: jar file for the program. </br>
#### Image Source For Snail: [Snail.PPM](https://people.sc.fsu.edu/~jburkardt/data/ppma/ppma.html)
#### Image Source For City: [City.PPM](https://images.unsplash.com/photo-1480714378408-67cf0d13bc1b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8Y2l0eXxlbnwwfHwwfHw%3D&w=1000&q=80)
* I converted JPG to PNG using my image processing program and cropped the original image.</br>
`test`: folder that contains the test image for the controller JUnit tests. (The images
were made by me)
* `two.ppm`: 2 by 2 PPM image created by me for testing.
* `two2.ppm`: 2 by 2 PPM image that was created during my test.
* `Square.png`: 2 by 2 PNG image that was added for test. </br>
* `Square2.png`: 2 by 2 PNG image that was created during my test.
* `Square.jpg`: 2 by 2 JPG image that was added for test. </br>
* `Square2.jpg`: 2 by 2 JPG image that was created during my test.
***
## Design
* Model Package:
  * `Image`: Interface for Image (Created interface to deal with different types of images 
  (Just in case))
  * `ImageImpl`: Class that extends IImage interface and represents the image with Pixels array, 
  length value, height value, maxValue value.
  * `Pixel`: Class that represents a pixel that has a value for red, green,and blue.
  * `ImageProcessingModel`: Interface for Model of the program. The model mainly serves as 
  place to save and modify the images.
  * `ImageProcessingModelImpl`: Class that serves as the Model for the program. Uses hashmap 
  to store Images, and the model modifies and uploads the images to the hashmap. 
  * `AdvancedProcessingModel`: Interface for AdvancedModel of the program. The model mainly 
  serves as place to save and modify the images with advanced features.
  * `AdvancedProcessingModelImpl`: Class that serves as the Advanced Model for the program 
  that extends original ImageProcessingModel and implements the AdvancedProcessingModel 
  interface. Uses hashmap to store Images, and the model modifies with advanced features 
  and uploads the images to the hashmap.
  * `GUIProcessingModel`: Interface for GUI model of the program. The model mainly serves as 
  place to save and modify the images with advanced features 
  and the model for gui functionality.
  * `GUIProcessingModelImpl`: Class that serves as the GUI Model for the program that extends 
  original AdvancedImageProcessingModel and implements the GUIProcessingModel interface. 
  Uses hashmap to store Images, and the model modifies 
  with advanced features and uploads the images to the hashmap. This model is 
  final model that covered GUI functionality.
* View Package:
  * `ImageView`: Interface for ImageView that was created for tests to make sure two Images
  with same pixels, height, and length are identified as equal.
  * `ImageViewImpl`: the class for image view, which was created for tests to make sure two Images
  with same pixels, height, length, and maxValue are identified as equal. It uses 
  toString() method to check if two images are equal.
  * `ImageProcessingView`: Interface for View of the program. for that will be implemented by 
  ImageProcessingView that renders the messages.
  * `ImageProcessingViewImpl`: Class that serves as the View for the program. The main purpose 
  of the View is interacting with the controller, rendering messages. Interaction with the Model 
  wasn't necessary for the program. View was not necessary and allow controller to take an 
  appendable to function the program, but kept it for future.
  * `IGUIView`: the GUI view interface that is for view class that builds GUI 
  for the image processor program.
  * `GUIView`: the main GUI view class that builds GUI for the image Processing program.
  * `Histogram`: class that draws histogram as Panel.
* Controller Package:
  * `ImageProcessingController`: Controller interface for our Image Processing that also deals with loading 
  and saving files.
  * `ImageProcessingControllerImpl`: The class that serves as the main controller for our program.
  It executes the command that are initialized in our constructor.
* Strategy Package:
  * `IStrategy`: the interface that is implemented by all the strategies. (The function objects 
  for our commands for the program)
  * `GreyScale`: class for Function Object that convert Images to GreyScale. There are six ways to 
  greyscale the image - **red, green, blue, value, intensity, and luma.**
  * `Flip`: class for Function Object that does horizontal and vertical flips.
  * `Brighten`: class for Function Object that brighten or darken the image in the model.
  * `ColorTransformation`: class for Function Object that transforms the color 
  of the image in the model.
  * `Sharpen`: class for Function Object that sharpens the image in the model.
  * `Blur`: class for Function Object that blurs the image in the model.
  * `AbstractStrategy`: abstract class that is extended by all the strategies (Commands).
* Main class: 
  * `Main`: To run the program. 
    * If a .txt file in the program argument string[] exists, the reader will `read the .txt file `
    for the program.
    * If there is something in the program argument string[], but it's not a file, 
    `InputStreamReader with System.in` will be the readable.
    * If there is nothing in the program argument, `InputStreamReader with System.in`will be
      the readable. </br>
### Design changes
## HW5
* From the previous release, we added more functionalities - `Filtering and ColorTransformation`
* Created more Function objects that represents the commands for our program.
* We implemented new Model that extends from the previous model for adding new advanced features.
* Added support for other types of images.
## HW6
* Implemented new model that extends from the previous model for GUI functionalities.
* Built `GUI features` for the image processor with new View class and new controller class using 
Swing Java library.
* Built line graph that represents the distribution of Pixel RGB values.



    

