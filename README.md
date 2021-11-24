# image_processing

**Made all sources images ourselves except koala.ppm which was provided by instructor

 **controller** package
- *ImgController* interface: establishes the necessary functionalities of all implementing classes.
- *ImgControllerImplementation* class: creates the functionality specific to a program that processess image files.
- *Parameter* enum: has a list of all the possible types of parameters, along with methods for processing the parameters. Its inclusion allows me to add new commands/parameter types without needing to modify my controller.


 **img** package
- *FileType* enum: Keeps track of all compatible image file types and allows for abstraction of the methods of different classes. Rather than needing to override methods in multiple image classes, this will perform the correct operations if given the file type.
- *Img* class: Has all the necessary variables and functionalities that all images, regardless of type, must have.
- *Pixel* class: Has all the necessary variables and functionalities all pixels, regardless of type, must have.


**model** package
- *Command* enum: Keeps track of all possible commands, as well as their string representations. Also has a map of Commands to the Parameters they require. It has a method that runs the correct Command on an Img model, given a map of the Parameters + corresponding values given to the model. This allows me to add any commands in the future without changing my model.
- *ImgModel* interface establishes the necessary functionalities of all implementing classes.
- *ImgModelImplmentation* class: Has all the necessary variables and functionalities to perform backend operations on images.

 **view** package
- *ImgView* interface establishes the necessary functionalities of all implementing classes.
- *ImgViewAbstract* abstract class establishes the necessary class variables and constructor.
- *TextView* class has methods necessary to write to the console 
- *IGraphicsView* interface establishes the necessary public functionalities of a GUI view
- *HistogramPanel* a custom JPanel that draws a histogram
- *GraphicsView* a view with a full graphics interface

**util** package
- *Tools* interface contains static methods that are used repeateadly across different classes. Putting them all in one place reduces code duplication.

GUI WRITEUP:
To use GUI, run GraphicsMain. Window will be empty until you load an image. All possible image filtering operations are in the dropdown menus on the top. Trying to brighten an image that has not been loaded, trying to load an incorrect file type, and giving an invalid input when prompted for one will surprisingly not work, but will not cause the program to break. The histogram will self adjust in scale whenever the image is changed so all values always fit in the panel but no graph looks too tiny. Simply close the window to quit the program. All operations required by the assignment are supported.

No changes had to be made to our original design, except the addition of new interfaces and classes and a new controller.

LIST OF COMMANDS THAT WORKS (Text UI):
Run start() method in controller and type, line by line, into console. Alternatively enter as one long string into Readable.

            - load image_processing/res/HappyFace/HappyFace.jpeg square 
            - save square image_processing/res/HappyFace/another_face.ppm
            - brighten 50 square brightSquare
            - save brightSquare image_processing/res/HappyFace/bright_face.ppm
            - brighten -50 square darkSquare 
            - save darkSquare image_processing/res/HappyFace/dark_face.ppm
            - vflip square upsidedownSquare 
            - save upsidedownSquare  image_processing/res/HappyFace/upside_down_face.ppm
            - hflip square mirrorSquare 
            - save mirrorSquare image_processing/res/HappyFace/mirror_face.ppm
            - just-green square greenSquare 
            - save greenSquare  image_processing/res/HappyFace/green_face.ppm
            - just-blue square blueSquare 
            - save blueSquare image_processing/res/HappyFace/blue_face.png
            - just-red square redSquare 
            - save redSquare image_processing/res/HappyFace/red_face.jpeg
            - just-value square valueSquare 
            - save valueSquare image_processing/res/HappyFace/value_face.ppm
            - just-luma square lumaSquare 
            - save lumaSquare image_processing/res/HappyFace/luma_face.ppm
            - just-intensity square intenseSquare 
            - save intenseSquare image_processing/res/HappyFace/intense_face.ppm
            - sepia square sepiaSquare 
            - save sepiaSquare image_processing/res/HappyFace/sepia_face.ppm
            - blur square blurrySquare
            - save blurrySquare image_processing/res/HappyFace/blur_face.ppm
            - hflip brightSquare flippedBrightSquare 
            - save flippedBrightSquare image_processing/res/bigPic/flipped_bright_square.ppm
            - quit



