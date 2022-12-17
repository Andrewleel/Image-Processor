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
    *Luma-Component Greyscale: `luma-component (Image-name) (New-image-name)`
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


## You can also the GUI to change the images
* To quit the program, you can click the Red X at on the top left
* Load Image: 
  * To load the image you can click the button at the top that says 'Load Image' 
  * Once you clicked the button, you can go through your files and select an image to upload
* Save Image:
  * To save an image, you first select which image you want to save from second box from the left
  * Click the 'Save Image' button at the top
  * Then you can select the name, the type of image and where to save it
* Histogram:
  * When an image is loaded a histogram shows up at the bottom right
  * It visualizes the distribution of color in an image
* Flip the image vertically: 
    * Go to the box in the bottom left and scroll until you see 'vertical-flip' 
    * Select which image you would like to change in the box on the right
    * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok when done
* Flip the image horizontally:
    * Go to the box in the bottom left and scroll until you see 'horizontal-flip' 
    * Select which image you would like to change in the box on the right
    * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok when done
* Brighten the image:
    * Go to the box in the bottom left and scroll until you see 'brighten' 
    * Select which image you would like to change in the box on the right
    * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok when done
    * Another box will pop up asking you to input a positive number, click Ok when done
* Darken the image:
    * Go to the box in the bottom left and scroll until you see 'darken'
    * Select which image you would like to change in the box on the right
    * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok when done
    * Another box will pop up asking you to input a negative number, click Ok when done
* Greyscale the image:
    * Red-Component Greyscale:
      * Go to the box in the bottom left and scroll until you see 'red-component'
      * Select which image you would like to change in the box on the right
      * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok
    * Green-Component Greyscale:
        * Go to the box in the bottom left and scroll until you see 'green-component'
        * Select which image you would like to change in the box on the right
        * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok
    * Blue-Component Greyscale: 
        * Go to the box in the bottom left and scroll until you see 'blue-component'
        * Select which image you would like to change in the box on the right
        * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok
    * Value-Component Greyscale:
        * Go to the box in the bottom left and scroll until you see 'value-component'
        * Select which image you would like to change in the box on the right
        * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok
    * Intensity-Component Greyscale: 
        * Go to the box in the bottom left and scroll until you see 'intensity-component'
        * Select which image you would like to change in the box on the right
        * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok
    * Luma-Component Greyscale:
        * Go to the box in the bottom left and scroll until you see 'luma-component'
        * Select which image you would like to change in the box on the right
        * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok
* Blur the image:
    * Go to the box in the bottom left and scroll until you see 'blur'
    * Select which image you would like to change in the box on the right
    * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok
* Sharpen the image:
    * Go to the box in the bottom left and scroll until you see 'sharpen'
    * Select which image you would like to change in the box on the right
    * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok
* Transform the color of image using sepia:
    * Go to the box in the bottom left and scroll until you see 'sepia'
    * Select which image you would like to change in the box on the right
    * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok
* Transform the color of image using greyscale:
    * Go to the box in the bottom left and scroll until you see 'greyscale'
    * Select which image you would like to change in the box on the right
    * Click the 'Submit' button. A box will pop up asking you to name your file, click Ok