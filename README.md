# Fun with Images!
This project performs a plethora of image and pattern processing operations. Pattern processing operations are malleable in a manner that lets the user define the specifications of it.


### Usage

```
Run from the command line:
1. java -jar Fun_with_Images.jar -interactive
2. java -jar Fun_with_Images.jar -script <script_file>

Run using the GUIController.java class providing appropriate arguments.
```

#### Important guidelines for Readable Object:

- load an image before the first operation. You can load a fresh image after an operation or choose to use the previously loaded image by simply not loading a new one before an operation.

- for script: every specification parameter must be separated by a `:` for example, `width:500` with no space between the _attribute_ and it's _value_

- every Flag has a fixed (accepted) height and width dimensions. Follow the instructions in the GUI

- for script: empty lines and otherwise extra spaces are allowed

- **.png**, **.bmp**, **.jpg** are the only formats allowed to _load_ or _save_


## Image processing operations:
**`BLUR`** - blur the input image

**`SHARPEN`** - sharpen the input image

**`DITHER`** - dither the input image

**`MOSAIC`** - mosaic the input image, parameter - `seeds`: _amount_

**`SEPIA`** - apply sepia filter on the input image

**`GREYSCALE`** - apply greyscale filter on the input image


## Pattern processing operations:

#### Abstract patterns:
**`CHECKERBOARD`** - creates a checkerboard pattern, parameters - individual `square_width`: _amount_, image `width`: _amount_

**`RAINBOW`** - creates a rainbow pattern, parameters - `width`: _amount_, `height`: _amount_, `orientation`: _`horizontal`_ or _`vertical`_

#### Flags:
**`GREECE`** - creates Greece country flag, parameter - flag `width`: _amount_

**`FRANCE`** - creates France country flag, parameter - flag `width`: _amount_

**`SWITZERLAND`** - creates Switzerland country flag, parameter - flag `width`: _amount_


## Citations
All pictures used or created as a result of above operations in this project are photographs taken and owned by Mothil Prabu and Shreyas. Mothil Prabu retains the ownership of these photographs and prohibits any use or modification by others.
