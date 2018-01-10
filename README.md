# 1-1800-FLOWERS

Many companies like to list their phone numbers using the letters printed on most
telephones. This makes the number easier to remember for customers. An example may
be 1-800-FLOWERS.

Requirements:
* Show a user possible matches for a list of provided phone numbers.
* Should be a command line app, reads from files specified as command line args or STDIN. 
  A single phone number per line in the file.
* Output all possible word replacements from a dictionary for each phone number.
* Should try to replace all digit or only one digit is allowed to be left.
* Skip the phone number which is impossible to replace.
* Allow users to set a dictionary with the -d command line option, if not, default dictionary should be used.
* All punctuation and whitespace should be ignored.
* Non case sensitive, output should be capital letters, separated with a single dash(-), one possible word per line.

## Instructions
#### Create rules which identify the conversion from a digit to letters.
* Users can create a new one: -rules [digit] [letter1] [letter2] ...
* But they also can use the default rules: -rules default
#### Identify dictionary file - which will store all the possible words.
* Users can choose their own file by move the file to Resource folder: -dict [dictionaryFileName]
* Or they can also use the default file: -dict default
#### Convert single phone number
* Users can convert single phone number: -pnum [phone_number]
* If rules is not available, users can set rules at that time by using given above commands or using default rules.
* If dictionary is not available, users can set dictionary at that time by using given commands 
  or using default dictionary.
#### Convert a file of phone number
* Users can convert multiple phone numbers via a text file located in Resource folder: -file [phoneNumberFileName]
* If rules is not available, users can set rules at that time by using given above commands or using default rules.
* If dictionary is not available, users can set dictionary at that time by using given commands 
  or using default dictionary.
#### Other useful commands
##### -rules verify
Show problems with the current rules such as rules are not completed or some values are duplicated:
##### -rules save [rulesFileName.txt]
Save users' rules in Resource folder for next using time.
##### -rules view
Show the current rules
##### -rules list
Show the list of available rules in Resource folder.
##### -rules [rulesFileName.txt]
Set rules in identified file.  

## Solution Design

* Create the class which will interface with dictionary file and manipulate it.

## Installation and usage

### System requirements

* JDK 8

### Build and Test

Windows:
...
...

### Run

Windows
...
...

## Sample Results

### Sample 1

### Sample 2

### Sample 3