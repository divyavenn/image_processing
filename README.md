# image_processing
 
 **controller** package
- *ImgController* interface: establishes the necessary functionalities of all implementing classes.
- *ImgControllerAbstract* class: establishes the necessary class variables and constructor.
- *PPMController* class: creates the functionality specific to a program that processess PPM files.
- *Parameter* enum: has a list of all the possible types of parameters, along with methods for processing the parameters. Its inclusion allows me to add new commands/parameter types without needing to modify my controller.


 **img** package
- *ImageType* enum: Keeps track of all compatible image file types and allows for abstraction of the methods of different classes. Rather than needing to override methods in abstract classes, this allows the abstract class to simply call on a method to make the class instance relevant to the image type the class is dealing with. Each image-type specific class like PPMController, PPMModel stores its ImageType in a class field.
- *Img* abstract class: Has all the necessary variables and functionalities that all images, regardless of type, must have.
- *Pixel* abstract class: Has all the necessary variables and functionalities all images, regardless of type, must have. This allows me to easily add new image types without changing up my existing models too much.
- *PPM* class has PPM-specific functionalities, such as how PPM images are written in a file.
- *PPMPixel* class has PPM-pixel-specific functionalities, such as how pixels are written to a PPM file

**model** package
- *Command* enum: Keeps track of all possible commands, as well as their string representations. Also has a map of Commands to the Parameters they require. It has a method that runs the correct Command on an Img model, given a map of the Parameters + corresponding values given to the model. This allows me to add any commands in the future without changing my model.
- *ImgModel* interface establishes the necessary functionalities of all implementing classes.
- *ImgModelAbstract* abstract class: Has all the necessary variables and functionalities that all ImgModels share. 
- *PPMModel* class: isolates the specific parts of the ImageModel functionality unique to PPM images, such as reading them from files.

 **view** package
- *ImgView* interface establishes the necessary functionalities of all implementing classes.
- *ImgViewAbstract* abstract class establishes the necessary class variables and constructor.
- *TextView* class has methods necessary to write to the console
