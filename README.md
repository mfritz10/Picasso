# Picasso

An application that allows the user to create expressions that
evaluate to colors and then eventually to images.

The given code base is a good start, but it is sparsely documented
(document each method/part after you understand what it's doing) and,
as your application grows, you may need to refactor.

See the specification for Picasso on the course web site.

## Extensions

GUI: 

History - another JFrame pops up on run and documents both error-free equations and which window they are inputted in

Multiple Window Viewing - the giant "+" button on the left side allows users to create new Frame windows on command. mind you that it doesn't allow users to re-edit previous windows.

Generating Expressions Automatically: 

Combining Expressions - using combine(expression1 , expression2) function can be used to combine two expressions together. This can be used with saved variables or new expressions. 

Random Expressions - using rand() will generate a random expression and display that expression to the user. This expression is randomly generated from Picasso's full library and is anywhere from 0-50 expressions long.  

## Project Organization

`src` - the source code for the project

`conf` - the configuration files for the project

The `images` directory contains some sample images generated from Picasso.  Some of the expressions for these images can be found in the `expressions` directory.

## Code Base History

This code base originated as a project in a course at Duke University.  The professors realized that the code could be designed better and refactored.  This code base has some code leftover from the original version.

## Authors

Matthew Fritz

Julie Ham

Grace Owens

Harry Crutcher
